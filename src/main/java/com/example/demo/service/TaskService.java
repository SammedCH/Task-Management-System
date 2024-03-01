package com.example.demo.service;

import java.util.Date;
import java.util.List;

import com.example.demo.entity.Task;

public interface TaskService {
  List<Task> getAllTasks();
  Task saveTask(Task task);
  Task getTaskById(int id);
  Task updateTask(Task task);
   void deleteTaskById(int id);
void assignTaskToUser(int taskId, int userId);
void markTaskAsComplete(int taskId, boolean completed);
List<Task> getTasksAssignedToUser(String username);
public List<Task> filterByDueDate(Date startDate, Date endDate);
 List<Task> filterByCompletionStatus(Boolean completed);
 public List<Task> searchTasks(String title, String description, String username);

}

