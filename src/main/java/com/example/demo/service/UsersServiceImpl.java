package com.example.demo.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Users;
import com.example.demo.repository.UsersRepository;

import jakarta.transaction.Transactional;
@Service
public class UsersServiceImpl implements UsersService{

	@Autowired
	public UsersRepository repo;
	
	@Override
	@Transactional
	public boolean addUser(Users user) {
		try {
			repo.save(user);
			return true;
		} catch (Exception e) {
			//e.printStackTrace();
			return false;
		}
		
	}
	@Override
	public boolean emailExixts(String email) {
		
		Users byEmail = repo.findByEmail(email);
		System.out.println("byEmail"+ byEmail);
      if(Objects.isNull(byEmail)) {
    	  return true;
      }else
		return false;
	}
	@Override
	public boolean validateUser(String email, String password) {
		Users user=repo.findByEmail(email);
		String db_pass=user.getPassword();
		String db_email=user.getEmail();
		if(password.equals(db_pass)&&email.equals(db_email))
		    return true;
		else
			return false;
	}
	@Override
	public String getRole(String email) {
		Users user=repo.findByEmail(email);
		
		return user.getRole();
	}
	@Override
    public List<Users> getAllUsers() {
        return repo.findAll();
    }
	

}
