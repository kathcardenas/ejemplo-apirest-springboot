package com.ejemplo.apirest.ejemplo_apirest.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ejemplo.apirest.ejemplo_apirest.Models.CategoriaModel;

public interface ICategoriaRepository extends JpaRepository<CategoriaModel, Integer>{

}
