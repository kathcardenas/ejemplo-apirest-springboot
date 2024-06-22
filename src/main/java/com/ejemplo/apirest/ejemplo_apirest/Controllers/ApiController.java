package com.ejemplo.apirest.ejemplo_apirest.Controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ejemplo.apirest.ejemplo_apirest.Models.ExampleModel;

@RestController
@RequestMapping("api/v1")
public class ApiController {

    @GetMapping("/method")
    public String methodGet(){
        return "Método GET";
    }

    @GetMapping("/method/{id}")
    public String paramGet(@PathVariable("id") String id){
        return "método GET con parámetros = "+id;
    }

    @PostMapping("/method")
    public String methodPost(){
        return "Método POST";
    }

    /*{
     "name": "Katherine Cárdenas",
     "mail": "info@mail.com",
     "price": 123,
     "description": "descripción de este ejemplo"
    } */
   @PostMapping("/method-json")
   public String methodPostJson(@RequestBody ExampleModel exampleModel){
    return "Nombre: "+exampleModel.getName()+"\nCorreo: "+exampleModel.getMail()
    + "\nPrecio: "+exampleModel.getPrice()+"\nDescripción: "
    +exampleModel.getDescription();
   }

    @PutMapping("/method")
    public String methodPut(){
        return "Método PUT";
    }

    @DeleteMapping("/method")
    public String methodDelete(){
        return "Método DELETE";
    }
}
