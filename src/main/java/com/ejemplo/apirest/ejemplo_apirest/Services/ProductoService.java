package com.ejemplo.apirest.ejemplo_apirest.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ejemplo.apirest.ejemplo_apirest.Models.ProductoModel;
import com.ejemplo.apirest.ejemplo_apirest.Repositories.IProductoRepository;

@Service
@Primary
public class ProductoService {

    @Autowired
    private IProductoRepository repository;
    
    //Method to List by ID
    public List<ProductoModel> list(){
        return this.repository.findAll(Sort.by("id").descending());
    }

    //Method to save
    public void save(ProductoModel product){
        this.repository.save(product);
    }

    //Method to search by id
    public ProductoModel searchById(Integer id){
        Optional<ProductoModel> optional = repository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    //Method to delete product
    public void delete(Integer id){
        this.repository.deleteById(id);
    }
}
