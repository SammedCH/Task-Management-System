package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Comment;
import com.example.demo.entity.Task;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.TaskRepository;
import com.example.demo.service.CommentService;
import com.example.demo.service.TaskService;
import com.example.demo.service.UsersService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;


@Controller
public class TaskController {

	@Autowired
	private UsersService usersService;
	
	@Autowired
    private CommentService commentService;
	
	@Autowired
    private TaskRepository taskRepository;
	
	@Autowired
    private CommentRepository commentRepository;
	
	private TaskService taskService;
	public TaskController(TaskService taskService) {
		super();
		this.taskService = taskService;
	}
	
	//handler method to handle list students and return mode and view
	@GetMapping("/tasks")
	public String listTasks(Model model) {
		model.addAttribute("tasks",taskService.getAllTasks());
		 model.addAttribute("users", usersService.getAllUsers());
		return "tasks";
	}
	
	@GetMapping("/tasks/new")
	public String createTasks(Model model) {
		//create task object to hold task form data
		Task task=new Task();
		model.addAttribute("task",task);
		return "create_task";
	}
	
	@PostMapping("/task")
	public String saveTask(@ModelAttribute("task") Task task) {
	    taskService.saveTask(task);
	    return "redirect:/tasks";
	}

	
	@GetMapping("/tasks/edit/{id}")
	public String taskForm(@PathVariable int id, Model model) {
		model.addAttribute("task",taskService.getTaskById(id));
		return "edit_task";
	}
	
	@PostMapping("/tasks/{id}")
	public String updateTask(@PathVariable int id,
			@ModelAttribute("task") Task task,
			Model model) {
		//get task from database by id
		Task existingTask=taskService.getTaskById(id);
		existingTask.setId(task.getId());
		existingTask.setTitle(task.getTitle());
		existingTask.setDescription(task.getDescription());
		existingTask.setDueDate(task.getDueDate());
		existingTask.setPriority(task.getPriority());
		
		//save updated task object
		taskService.updateTask(existingTask);
		return "redirect:/tasks";
	}
	
	//handler method to handle delete task request
	@GetMapping("/tasks/{id}")
	public String deleteTask(@PathVariable int id) {
		taskService.deleteTaskById(id);
		return "redirect:/tasks";
	}
	
	 @GetMapping("/tasks/{taskId}/assign")
	    public String showAssignTaskForm(@PathVariable int taskId, Model model) {
	        model.addAttribute("taskId", taskId);
	        model.addAttribute("users", usersService.getAllUsers()); // Assuming you have a method to get all users
	        return "assign_task";
	    }

	    @PostMapping("/tasks/assign")
	    public String assignTaskToUser(@RequestParam int taskId, @RequestParam int userId, RedirectAttributes redirectAttributes) {
	        taskService.assignTaskToUser(taskId, userId);
	        redirectAttributes.addFlashAttribute("message", "Task assigned successfully");
	        return "redirect:/tasks";
	    }
	
	   // Assuming you have a method to get tasks assigned to the logged-in user
	    @GetMapping("/assigned-to-me")
	    public java.util.List<Task> getTasksAssignedToMe() {
	        // Use Spring Security to get the logged-in user's username
	        String username = SecurityContextHolder.getContext().getAuthentication().getName();
	        java.util.List<Task> tasks = taskService.getTasksAssignedToUser(username);
	        System.out.println("Fetched tasks for user: " + username + ", count: " + tasks.size());
	        return tasks;
	    }
     
	    @PostMapping("/{taskId}/complete")
	    public ResponseEntity<?> markTaskAsComplete(@PathVariable int taskId, @RequestBody boolean completed) {
	        taskService.markTaskAsComplete(taskId, completed);
	        return ResponseEntity.ok().build();
	    }
	    
	    @GetMapping("/my-tasks")
	    public String showMyTasks(Model model) {
	        String username = SecurityContextHolder.getContext().getAuthentication().getName();
	        java.util.List<Task> tasks = taskService.getTasksAssignedToUser(username);
	        model.addAttribute("tasks", tasks);
	        return "my-tasks";
	    }
	    
	    @GetMapping("/tasks/search")
	    public java.util.List<Task> searchTasks(@RequestParam(required = false) String title, @RequestParam(required = false) String description, @RequestParam(required = false) String username) {
	        return taskService.searchTasks(title, description, username);
	    }

	    @GetMapping("/tasks/filter")
	    public java.util.List<Task> filterTasks(@RequestParam(required = false) Boolean completed, @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate, @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {
	        if (completed != null) {
	            return taskService.filterByCompletionStatus(completed);
	        }
	        if (startDate != null && endDate != null) {
	            return taskService.filterByDueDate(startDate, endDate);
	        }
	        return Collections.emptyList();
	    }
	    
//	    @GetMapping("/search-results")
//	    public String showSearchResults(@RequestParam String query, Model model) {
//	        List<Task> tasks = taskService.searchTasks(query); // Implement this method in your service
//	        model.addAttribute("tasks", tasks);
//	        return "search_results";
//	    }

	    

//	    @PostMapping("/tasks/{taskId}/comments")
//	    public Comment addComment(@PathVariable Long taskId, @ModelAttribute Comment comment) {
//	        comment.setTask(new Task(taskId)); // Assuming Task has a constructor that sets the ID
//	        return commentService.saveComment(comment);
//	    }
	    
	    @PostMapping("/tasks/{taskId}/comment")
	    public String addComment(@PathVariable int taskId, @RequestParam String content, RedirectAttributes redirectAttributes) {
	        Task task = taskRepository.findById(taskId).orElseThrow(() -> new RuntimeException("Task not found"));
	        Comment comment = new Comment();
	        comment.setTask(task);
	        comment.setContent(content);
	        commentRepository.save(comment);
	        redirectAttributes.addFlashAttribute("message", "Comment added successfully");
	        return "redirect:/tasks/" + taskId;
	    }


	    @GetMapping("/tasks/{taskId}/comments")
	    public java.util.List<Comment> getComments(@PathVariable Long taskId) {
	        return commentService.findCommentsByTask(taskId);
	    }

}
