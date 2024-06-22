package com.ejemplo.apirest.ejemplo_apirest.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ejemplo.apirest.ejemplo_apirest.Models.ExampleModel;
import com.ejemplo.apirest.ejemplo_apirest.utilities.Utilities;

@RestController
@RequestMapping("/api/v1")
public class ResponseEntityController {

    @GetMapping("/response-entity")
    public ResponseEntity<String> methodGet(){
        return ResponseEntity.ok("método GET desde ResponseEntity");
    }

    @GetMapping("/response-entity-personalized")
    public ResponseEntity<Object> methodGetPersonalized(){
        return Utilities.generateResponse(HttpStatus.OK, "Método GET personalizado desde ResponseEntity");
    }

    @GetMapping("/response-entity/{id}")
    public ResponseEntity<String> methodGetById(@PathVariable("id") String id){
        return ResponseEntity.ok("método GET desde ResponseEntity con id: "+id);
    }

    @PostMapping("/response-entity")
    public ResponseEntity<String> methodPost(){
        return ResponseEntity.ok("método POST desde ResponseEntity");
    }

    @PostMapping("/response-entity-json")
    public ResponseEntity<String> methodPostJson(@RequestBody ExampleModel exampleModel){
        return ResponseEntity.ok("Nombre: "+exampleModel.getName()+"\nCorreo: "+exampleModel.getMail()
        + "\nPrecio: "+exampleModel.getPrice()+"\nDescripción: "
        +exampleModel.getDescription());
    }

    @PutMapping("/response-entity")
    public ResponseEntity<String> methodPut(){
        return ResponseEntity.ok("método PUT desde ResponseEntity");
    }

    @DeleteMapping("/response-entity")
    public ResponseEntity<String> methodDelete(){
        return ResponseEntity.ok("método DELETE desde ResponseEntity");
    }
}
