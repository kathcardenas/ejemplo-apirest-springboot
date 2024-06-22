package com.ejemplo.apirest.ejemplo_apirest.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ejemplo.apirest.ejemplo_apirest.Models.UsuarioModel;
import com.ejemplo.apirest.ejemplo_apirest.Repositories.IUsuarioRepository;

@Service
@Primary
public class UsuarioService {
    @Autowired
    private IUsuarioRepository repository;

    //Method to List by ID
    public List<UsuarioModel> list(){
        return this.repository.findAll(Sort.by("id").descending());
    }

    //Method to save
    public void save(UsuarioModel entity){
        this.repository.save(entity);
    }

    //Method to search by id
    public UsuarioModel searchById(Integer id){
        Optional<UsuarioModel> optional = repository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    //Method to search by email
    public UsuarioModel searchByEmail(String mail){
        return this.repository.findByCorreo(mail);
    }

    //Method to delete user
    public void delete(Integer id){
        this.repository.deleteById(id);
    }

}
