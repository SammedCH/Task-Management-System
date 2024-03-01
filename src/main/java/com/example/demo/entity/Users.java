package com.example.demo.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
@Entity
@Table(name="users")
public class Users {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int id;
   String username;
   String email;
   String password;
   String role;
   @OneToMany(mappedBy = "assignedUser")
   private List<Task> tasks;
   @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
   private List<Comment> comments = new ArrayList<>();
   
   public Users() {
		super();
	}
public Users(String username, String email, String password, String role) {
	super();
	this.username = username;
	this.email = email;
	this.password = password;
	this.role = role;
}



public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getRole() {
	return role;
}
public void setRole(String role) {
	this.role = role;
}


public List<Task> getTasks() {
	return tasks;
}
public void setTasks(List<Task> tasks) {
	this.tasks = tasks;
}
public List<Comment> getComments() {
	return comments;
}
public void setComments(List<Comment> comments) {
	this.comments = comments;
}
@Override
public String toString() {
	return "Users [id=" + id + ", username=" + username + ", email=" + email + ", password=" + password + ", role="
			+ role + "]";
}
   
}
