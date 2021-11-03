package com.padillatomas.icons.icons.auth.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.padillatomas.icons.icons.auth.entity.UserEntity;
import com.padillatomas.icons.icons.auth.repository.UserRepository;

@Service
public class UserDetailsCustomService implements UserDetailsService {

	@Autowired
	private UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		// Traemos User
		UserEntity foundUser = userRepo.findByUsername(userName);
		if (foundUser == null) {
			throw new UsernameNotFoundException("Username: " + userName + " -> NOT FOUND");
		}
		return new User(
				foundUser.getUsername(),
				foundUser.getPassword(), 
				Collections.emptyList() // Roles
			);
	}	
	
}
