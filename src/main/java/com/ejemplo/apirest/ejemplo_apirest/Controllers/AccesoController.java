package com.ejemplo.apirest.ejemplo_apirest.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ejemplo.apirest.ejemplo_apirest.Models.UsuarioModel;
import com.ejemplo.apirest.ejemplo_apirest.Services.UsuarioService;
import com.ejemplo.apirest.ejemplo_apirest.jwt.AuthRequest;
import com.ejemplo.apirest.ejemplo_apirest.jwt.AuthResponse;
import com.ejemplo.apirest.ejemplo_apirest.jwt.JwtTokenUtil;

@RestController
@RequestMapping("/api/v1")
public class AccesoController {
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private JwtTokenUtil jwtUtil;
	
	@Autowired
	private UsuarioService usuarioService;
	
	/*{"correo":"info@tamila.cl", "password":"123456"}*/
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody AuthRequest request)
	{
		try {
			
			Authentication authentication = this.authManager.authenticate(new 
			UsernamePasswordAuthenticationToken(request.getCorreo(), request.getPassword()));
			System.out.println(authentication);
			
			UsuarioModel user = this.usuarioService.searchByEmail(request.getCorreo());
			String accessToken = this.jwtUtil.generarToken(user);
			
			AuthResponse response = new AuthResponse(request.getCorreo(), accessToken);
			
			return ResponseEntity.ok(response);
			
		} catch (BadCredentialsException e) {
			
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}
}

