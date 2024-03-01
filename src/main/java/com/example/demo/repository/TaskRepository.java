package com.example.demo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Task;

public interface TaskRepository extends JpaRepository<Task,Integer>{

	List<Task> findByAssignedUserUsername(String username);
	 List<Task> findByTitleContainingOrDescriptionContainingOrAssignedUserUsernameContaining(String title, String description, String username);
	    List<Task> findByCompleted(boolean completed);
	    List<Task> findByDueDateBetween(Date startDate, Date endDate);
}
