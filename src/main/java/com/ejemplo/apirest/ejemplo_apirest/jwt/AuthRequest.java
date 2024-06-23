package com.ejemplo.apirest.ejemplo_apirest.jwt;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthRequest {

    private String correo;
    private String password;

}
