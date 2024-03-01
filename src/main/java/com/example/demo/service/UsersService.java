package com.example.demo.service;

import com.example.demo.entity.Users;

public interface UsersService {
   public boolean addUser(Users user);

   public boolean emailExixts(String email);

public boolean validateUser(String email, String password);

public String getRole(String email);

public Object getAllUsers();


}
