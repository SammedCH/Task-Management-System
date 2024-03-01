package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.demo.entity.Users;
import com.example.demo.repository.UsersRepository;



public class CustomUserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsersRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		//Fetching user from database
		Users user = userRepository.getUserByUsername(username);
		
		if (user==null) {
			throw new UsernameNotFoundException("Could not found username "+username);
		}
		
		CustomeUserDetails userDetails = new CustomeUserDetails(user);
		
		
//		UserDetails userDetails = userRepository.findByEmail(username)
//					  .map(CustomeUserDetails::new)
//					  .orElseThrow(() -> new UsernameNotFoundException("Could not found username "+username));
		
		return userDetails;
	}

}
