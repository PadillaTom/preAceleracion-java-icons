package com.padillatomas.icons.icons.auth.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.padillatomas.icons.icons.auth.dto.AuthenticationRequest;
import com.padillatomas.icons.icons.auth.dto.AuthenticationResponse;
import com.padillatomas.icons.icons.auth.dto.UserDTO;
import com.padillatomas.icons.icons.auth.service.JwtUtils;
import com.padillatomas.icons.icons.auth.service.UserDetailsCustomService;

@RestController
@RequestMapping("/auth")
public class UserAuthController {

	@Autowired
	private UserDetailsCustomService userServ;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	private AuthenticationManager authManager;
	
	// Signup
	@PostMapping("/signup")
	public ResponseEntity<AuthenticationResponse> signUp(@Valid @RequestBody UserDTO userToCreate) throws Exception {
		userServ.signupUser(userToCreate);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	// SignIn
	@PostMapping("/signin")
	public ResponseEntity<AuthenticationResponse> signIn(@RequestBody AuthenticationRequest authReq) throws Exception {
		// *** 
		// TODO ESTO "DEBERIA" IR EN UN 
		// SIGNIN SERVICE
		// ***
		UserDetails userDetails;
		try {
			// Seteamos un AUTH, 
			// hace todo lo que tiene que hacer, con nuestros UserDetailsCustomService.
			// para verificar que todo sea correcto.
			// Devuelve AUTH
			Authentication auth = authManager.authenticate(
						new UsernamePasswordAuthenticationToken(
								authReq.getUsername(),
								authReq.getPassword()
								)						
					);	
			// Dicho AUTH, previamente verificado, con nuestro CUSTOM SERVICE
			// se lo asignamos a userDetails.
			userDetails = (UserDetails) auth.getPrincipal();
			
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);			
		} 
		
		// Una vez obtenido el userDetails, verificado en Auth (Con nuestro CUSTOM SERVICE)
		// Generamos un TOKEN a partir de dicho USER.
		final String jwt = jwtUtils.generateToken(userDetails);
		
		// Mandamos la JWT como AUTHENTICATION RESPONSE
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}
	
	// PROCESO COMPLETO:
	// SIGNUP
	// 1) Mandamos POST (user y pass)
	// 2) Cae al FILTERS, (/auth/ no hace nada, porque PermitAll() )
	// 3) Controller
	// 4) Service
	//  ** PISA MISMO USERNAME **
	//
	// SIGNIN
	//
	//  TIRA NULL
	//
	//
	//
	//
	//
	//

	
}
