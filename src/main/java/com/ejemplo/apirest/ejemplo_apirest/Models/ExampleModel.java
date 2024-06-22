package com.ejemplo.apirest.ejemplo_apirest.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ExampleModel {

    private String name;
    private String mail;
    private Integer price;
    private String description;

}
