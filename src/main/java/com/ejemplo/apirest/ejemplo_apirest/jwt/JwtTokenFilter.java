package com.ejemplo.apirest.ejemplo_apirest.jwt;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ejemplo.apirest.ejemplo_apirest.Models.UsuarioModel;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {
	
	@Autowired
	private JwtTokenUtil jwtUtil;

	@SuppressWarnings("null")
	@Override
	    protected void doFilterInternal(HttpServletRequest request,
	                HttpServletResponse response, FilterChain filterChain)
	            throws ServletException, IOException {
	 
	        if (!hasAuthorizationBearer(request)) {
	            filterChain.doFilter(request, response);
	            return;
	        }
	 
	        String token = getAccessToken(request);
	 
	        if (!jwtUtil.validateAccessToken(token)) {
	            filterChain.doFilter(request, response);
	            return;
	        }
	 
	        setAuthenticationContext(token, request);
	        filterChain.doFilter(request, response);
	    }
	 //Bearer sdfsDFSDFDSFSDF.fsdfdsfdsfsdf.sdfsdfds
	 private boolean hasAuthorizationBearer(HttpServletRequest request) {
	        String header = request.getHeader("Authorization");
	        if (ObjectUtils.isEmpty(header) || !header.startsWith("Bearer")) {
	            return false;
	        }
	 
	        return true;
	    }
	 
	    private String getAccessToken(HttpServletRequest request) {
	        String header = request.getHeader("Authorization");
	        String token = header.split(" ")[1].trim();
	        return token;
	    }
	 
	    private void setAuthenticationContext(String token, HttpServletRequest request) {
	    	UsuarioModel userDetails = getUserDetails(token);
	 
	        UsernamePasswordAuthenticationToken
	            authentication = new UsernamePasswordAuthenticationToken(userDetails, null, null);
	 
	        authentication.setDetails(
	                new WebAuthenticationDetailsSource().buildDetails(request));
	 
	        SecurityContextHolder.getContext().setAuthentication(authentication);
	    }
	 
	    private UsuarioModel getUserDetails(String token) {
	        UsuarioModel userDetails = new UsuarioModel();
	        String[] jwtSubject = jwtUtil.getSubject(token).split(",");
	 
	        userDetails.setId(Integer.parseInt(jwtSubject[0]));
	        userDetails.setCorreo(jwtSubject[1]);
	 
	        return   userDetails;
	    }
}
