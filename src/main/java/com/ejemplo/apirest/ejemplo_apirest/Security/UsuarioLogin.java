package com.ejemplo.apirest.ejemplo_apirest.Security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ejemplo.apirest.ejemplo_apirest.Models.UsuarioModel;
import com.ejemplo.apirest.ejemplo_apirest.Services.UsuarioService;

@Component
public class UsuarioLogin implements UserDetailsService{
	
	@Autowired
	private UsuarioService usuarioService;

	@Override
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UsuarioModel usuario = this.usuarioService.searchByEmail(username);
		if(usuario == null) { 
        	throw new UsernameNotFoundException("Username: " + username + " no existe en el sistema!");
        }
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		return new User(usuario.getCorreo(), usuario.getPassword(), true, true, true, true, authorities);
	}
}

