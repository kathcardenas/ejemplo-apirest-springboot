package com.ejemplo.apirest.ejemplo_apirest.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ejemplo.apirest.ejemplo_apirest.Models.CategoriaModel;
import com.ejemplo.apirest.ejemplo_apirest.Repositories.ICategoriaRepository;

@Service
@Primary
public class CategoriaService {
    @Autowired
    private ICategoriaRepository repository;

    //Method to List by ID
    public List<CategoriaModel> list(){
        return this.repository.findAll(Sort.by("id").descending());
    }

    //Method to save
    public void save(CategoriaModel category){
        this.repository.save(category);
    }

    //Method to search by id
    public CategoriaModel searchById(Integer id){
        Optional<CategoriaModel> optional = repository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    //Method to delete category
    public void delete(Integer id){
        this.repository.deleteById(id);
    }
}
