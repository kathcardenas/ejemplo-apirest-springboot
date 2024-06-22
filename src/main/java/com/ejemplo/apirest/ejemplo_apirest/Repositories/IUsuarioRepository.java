package com.ejemplo.apirest.ejemplo_apirest.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ejemplo.apirest.ejemplo_apirest.Models.UsuarioModel;

public interface IUsuarioRepository extends JpaRepository<UsuarioModel, Integer>{
    public UsuarioModel findByCorreo(String correo);

}
