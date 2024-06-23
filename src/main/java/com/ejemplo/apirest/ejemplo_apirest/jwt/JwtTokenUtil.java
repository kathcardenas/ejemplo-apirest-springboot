package com.ejemplo.apirest.ejemplo_apirest.jwt;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import com.ejemplo.apirest.ejemplo_apirest.Models.UsuarioModel;
import com.ejemplo.apirest.ejemplo_apirest.utilities.Constants;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenUtil {

	private static final long EXPIRE_DURATION =24*60*60*1000;//24 HORAS
	
	public boolean validateAccessToken(String token) 
	{
		try {
            Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(token).getPayload();
            return true;
        } catch (ExpiredJwtException ex) {
        	System.out.println("JWT expirado"+ ex.getMessage());
        } catch (IllegalArgumentException ex) {
        	System.out.println("Token es null, está vacío o contiene espacios "+ ex.getMessage());
        } catch (MalformedJwtException ex) {
        	System.out.println("JWT es inválido"+ ex);
        } catch (UnsupportedJwtException ex) {
        	System.out.println("JWT no soportado"+ ex);
        } catch (SecurityException ex) {
        	System.out.println("Validación de firma errónea");
        }
         
        return false;
	}

	private SecretKey getSigningKey(){
		byte[] keyBytes = Decoders.BASE64.decode(Constants.SIGN);
		return Keys.hmacShaKeyFor(keyBytes);
	}
	
	public String generarToken(UsuarioModel usuarioModel) 
	{
		return Jwts.builder()
				.subject(String.format("%s,%s", usuarioModel.getId(), usuarioModel.getCorreo()))
				.issuer("Ejemplo api-rest")
				.issuedAt(new Date())
				.expiration(new Date((new Date()).getTime() + EXPIRE_DURATION))
				.signWith(getSigningKey())
				.compact();
	}
	
	public String getSubject(String token) 
	{
		return parseClaims(token).getSubject();
	}
	
	private Claims parseClaims(String token) 
	{
		return Jwts.parser()
				.verifyWith(getSigningKey())
				.build()
				.parseSignedClaims(token)
				.getPayload();
	}
}
