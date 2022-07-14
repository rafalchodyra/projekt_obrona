package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.demo.dao.UserRepository;
import com.example.demo.entities.User;

public class UserDetailsServiceImpl implements UserDetailsService{
    @Autowired
	private UserRepository userRepository;

	@Autowired
	private UserAuthenticationInfoService service;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//fetching user from db
		
		User user =userRepository.getUserByUserName(username);
		System.out.println("loadUserByUsername1");
		if(user==null) {
			throw new UsernameNotFoundException("Could not found user");
		}
		System.out.println("loadUserByUsername2");

		CustomerUserDetails customerUserDetails = new CustomerUserDetails(user);
		
		return customerUserDetails;
	}

}
