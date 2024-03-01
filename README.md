Task Management System 📝
1.A web application designed to help users organize and track their tasks effectively. 
2.It provides a user-friendly interface for managing tasks, including features for adding, editing, and deleting tasks. 
3.The system also includes authentication and login functionality to ensure secure access to user-specific task lists.

Technologies Used 💻
1.Spring Boot for backend development
2.Thymeleaf for frontend templating
3.MySQL for database management
4.Spring Security for authentication and authorization

Project Structure
1.src/main/java/Task.java: Defines the Task class representing a task with properties like id, title, description, and dueDate.
2.src/main/java/TaskManager.java: Implements the TaskManager class for managing tasks, including methods for creating, retrieving, updating, and deleting tasks.
3.database/create_table.sql: SQL script to create the necessary tasks table in a MySQL database.

Setup and Usage
1.Database Setup
   1.Set up a MySQL database named task_manager.
   2.Execute the SQL script create_table.sql to create the required tasks table.
2.Configure Database Connection
   1.Update the JDBC connection details in TaskManager.java to match your MySQL server, username, and password.
3.Run the Application
   1.Compile and run the TaskManager class to interact with the Task Management System.

Usage Instructions
 1.Sign up for a new account by providing the required details, including username, email, phone number, and password. Make sure to follow the validation rules provided.
 2.Log in with your registered email and password.
 3.Once logged in, you will be redirected to the task dashboard.
 4.On the task dashboard, you can view, add, edit, and delete tasks.
 5.You can search and filter the tasks based on your requirement.
 6.Admin can add comment to task which are available to user.
 7.After user log in they can view the task and mark the task as complete or incomplete and search the task based on the requirement.

Enhancements and Customization
 1.The system can be further customized by adding features such as  email notifications for task assignments, updates, and approaching due dates to keep users informed.
 2.For developers interested in contributing or extending the system, please review the project structure and the provided SQL scripts for task management.
 
![Screenshot (3)](https://github.com/SammedCH/Task-Management-System/assets/147800459/b3862d10-1da2-4264-b6b0-055978ccd466)
![Screenshot (4)](https://github.com/SammedCH/Task-Management-System/assets/147800459/7fc86172-e2be-43ea-a3f4-97f33d92e21a)
![Screenshot (5)](https://github.com/SammedCH/Task-Management-System/assets/147800459/6ef05e28-edfc-4505-9cf6-a184867f3a0d)
![Screenshot (1)](https://github.com/SammedCH/Task-Management-System/assets/147800459/09db8695-ed91-4924-bab0-b9a2aad6601e)
![Screenshot (2)](https://github.com/SammedCH/Task-Management-System/assets/147800459/6836ec4f-1804-4020-8488-222236bc0ac0)

