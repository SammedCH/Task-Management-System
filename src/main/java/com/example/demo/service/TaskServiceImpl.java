package com.example.demo.service;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entity.Task;
import com.example.demo.entity.Users;
import com.example.demo.repository.TaskRepository;
import com.example.demo.repository.UsersRepository;

@Service
public class TaskServiceImpl implements TaskService{

	private TaskRepository taskRepo;
	@Autowired
	private UsersRepository userRepo;
	
	public TaskServiceImpl(TaskRepository taskRepo) {
		super();
		this.taskRepo = taskRepo;
	}


	@Override
	public List<Task> getAllTasks() {
		return taskRepo.findAll();
	}


	@Override
	public Task saveTask(Task task) {
		
		return taskRepo.save(task);
	}


	@Override
	public Task getTaskById(int id) {
		return taskRepo.findById(id).get();
	}


	@Override
	public Task updateTask(Task task) {
		return taskRepo.save(task);
	}


	@Override
	public void deleteTaskById(int id) {
      taskRepo.deleteById(id);		
	}
   
	public void assignTaskToUser(int taskId, int userId) {
	    Task task = taskRepo.findById(taskId).orElseThrow(() -> new RuntimeException("Task not found"));
	    Users user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
	    task.setAssignedUser(user);
	    taskRepo.save(task);
	}

	 public List<Task> getTasksAssignedToUser(String username) {
		  System.out.println(username);
	        return taskRepo.findByAssignedUserUsername(username);
	    }

	    public void markTaskAsComplete(int taskId, boolean completed) {
	        Task task = taskRepo.findById(taskId).orElseThrow(() -> new RuntimeException("Task not found"));
	        task.setCompleted(completed);
	        taskRepo.save(task);
	    }
	    
	    public List<Task> searchTasks(String title, String description, String username) {
	        return taskRepo.findByTitleContainingOrDescriptionContainingOrAssignedUserUsernameContaining(title, description, username);
	    }

	    @Override
	    public List<Task> filterByCompletionStatus(Boolean completed) {
	        return taskRepo.findByCompleted(completed);
	    }

	    public List<Task> filterByDueDate(Date startDate, Date endDate) {
	        return taskRepo.findByDueDateBetween(startDate, endDate);
	    }


		
}
