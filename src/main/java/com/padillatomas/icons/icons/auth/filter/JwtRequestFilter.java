package com.padillatomas.icons.icons.auth.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.padillatomas.icons.icons.auth.service.JwtUtils;
import com.padillatomas.icons.icons.auth.service.UserDetailsCustomService;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	@Autowired
	private UserDetailsCustomService userDetailsCS;
	
	@Autowired
	private JwtUtils jwtUtils; // To EXTRACT and VALIDATE.
	
	private AuthenticationManager authManager;
	
	//
	// Ejecutado Cada vez que llegue un Request:	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		// Get Headers:
		final String authHeader = request.getHeader("Authorization");
		
		String username = null;
		String jwt = null;
		
		// Extraemos del TOKEN del HEADER:
		if (authHeader != null && authHeader.startsWith("Bearer ")) {
			
			jwt = authHeader.substring(7); // 7 char before Token.
			username = jwtUtils.extractUsername(jwt); // Obtenemos Username.
		}
		
		// De dicho TOKEN Obtenemos USERNAME
		// && If dicho USERNAME is NOT AUTHENTICATED YET:
		if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			
			// Obtenemos UserDetails: de dicho Username.
			UserDetails userDetails = userDetailsCS.loadUserByUsername(username);
			
			// Validamos:
			// IF TRUE -> Creamos un AUTH y la seteamos al CONTEXT de nuestra app.
			if(jwtUtils.validateToken(jwt, userDetails)) {
				// Creamos la AUTH con user y pass.
				UsernamePasswordAuthenticationToken authReq =
						new UsernamePasswordAuthenticationToken(
								userDetails.getUsername(),
								userDetails.getPassword()								
								);				
				Authentication auth = authManager.authenticate(authReq);
				
				// Seteamos la AUTH al CONTEXT
				SecurityContextHolder.getContext().setAuthentication(auth);
			}
		}
		
		// Pasamos todo lo anterior:
		filterChain.doFilter(request, response);		
	}

}
