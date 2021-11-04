package com.padillatomas.icons.icons.auth.service;

import java.util.Collections;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.padillatomas.icons.icons.auth.dto.UserDTO;
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

	// SIGNUP
	public boolean signupUser(@Valid UserDTO userToCreate) {
		UserEntity newUser = new UserEntity();
		UserEntity matchingUser = userRepo.findByUsername(userToCreate.getUsername());			
		newUser.setUsername(userToCreate.getUsername());
		newUser.setPassword(userToCreate.getPassword());		
		if(matchingUser != null && (matchingUser.getUsername().equals(newUser.getUsername()))) {
			// NO LO CREA, PERO NO ENVIA "Already Exists"
			// En Controller verificamos TRUE o FALSE
			// Mandamos ResponseEntity segun corresponda
			return false;
		}		
		newUser = userRepo.save(newUser);
		
		//Email Stuff
		
		return newUser != null;		
	}	
	
}
