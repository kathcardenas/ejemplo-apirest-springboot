package com.ejemplo.apirest.ejemplo_apirest.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ejemplo.apirest.ejemplo_apirest.Models.ProductoModel;

public interface IProductoRepository extends JpaRepository<ProductoModel, Integer>{

}
