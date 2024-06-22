package com.ejemplo.apirest.ejemplo_apirest.Configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ejemplo.apirest.ejemplo_apirest.utilities.Constants;

@Configuration
public class Configure implements WebMvcConfigurer{

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // TODO Auto-generated method stub
        registry.addResourceHandler("/uploads/**").addResourceLocations("file: "+
        Constants.ROUTE_UPLOAD);
    }

    

}
