package com.ejemplo.apirest.ejemplo_apirest.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AuthResponse {

    private String correo;
    private String accesToken;

}
