package com.ejemplo.apirest.ejemplo_apirest.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ejemplo.apirest.ejemplo_apirest.Models.CategoriaModel;
import com.ejemplo.apirest.ejemplo_apirest.Models.ProductoModel;
import com.ejemplo.apirest.ejemplo_apirest.Services.CategoriaService;
import com.ejemplo.apirest.ejemplo_apirest.Services.ProductoService;
import com.ejemplo.apirest.ejemplo_apirest.utilities.Constants;
import com.ejemplo.apirest.ejemplo_apirest.utilities.Utilities;

@RestController
@RequestMapping("/api/v1")
public class BdController {

    //// CATEGORIES
    @Autowired
    private CategoriaService categoryService;

    @GetMapping("/categorias")
    public List<CategoriaModel> categories(){
        return this.categoryService.list();
    }

    @GetMapping("/categorias/{id}")
    public CategoriaModel categoriesById(@PathVariable("id") Integer id){
        return this.categoryService.searchById(id);
    }

    @PostMapping("/categorias")
    public ResponseEntity<Object> categoriesPost(@RequestBody CategoriaModel request){
        request.setSlug(Utilities.getSlug(request.getNombre()));
        this.categoryService.save(request);
        return Utilities.generateResponse(HttpStatus.CREATED, "Se creó el registro de forma exitosa");
    }

    @PutMapping("/categorias/{id}")
    public ResponseEntity<Object> categoriesUpdate(@PathVariable("id") Integer id,@RequestBody CategoriaModel request){
        CategoriaModel category = this.categoryService.searchById(id);
        category.setNombre((request.getNombre()));
        category.setSlug(Utilities.getSlug(request.getNombre()));

        this.categoryService.save(category);

        return Utilities.generateResponse(HttpStatus.OK, "Se editó el registro de forma éxitosa");
    }

    @DeleteMapping("/categorias/{id}")
    public ResponseEntity<Object> categoriesDelete(@PathVariable("id") Integer id){
        try {
            this.categoryService.delete(id);
            return Utilities.generateResponse(HttpStatus.OK, "Se eliminó el registro de forma éxitosa");            
        } catch (Exception e) {
            // TODO: handle exception
            return Utilities.generateResponse(HttpStatus.BAD_REQUEST, "Falló eliminar el registro, intente más tarde");
        }
    }

    /// PRODUCTS
    @Autowired
    private ProductoService productService;
    
    @GetMapping("/productos")
    public List<ProductoModel> products(){
        return this.productService.list();
    }

    @GetMapping("/productos/{id}")
    public ProductoModel productsById(@PathVariable("id") Integer id){
        return this.productService.searchById(id);
    }

    @PostMapping("/productos")
    public ResponseEntity<Object> productsPost(ProductoModel request, @RequestParam("File") MultipartFile file){
        HttpStatus status = HttpStatus.OK;
        String mensaje = "";
        if (!file.isEmpty()) {
            String nombreImagen = Utilities.saveField(file, Constants.ROUTE_UPLOAD+"producto2/");
            if (nombreImagen=="NO") {
                status = HttpStatus.BAD_REQUEST;
                mensaje="La foto enviada no es válida, debe ser JPEG, JPG O PNG";
            }else{
                if (nombreImagen!=null) {
                    request.setFoto(nombreImagen);
                    request.setSlug(Utilities.getSlug(request.getNombre()));

                    this.productService.save(request);

                    status=HttpStatus.CREATED;
                    mensaje="Se creó el registro de forma exitosa";
                }
            }
        } else{
            status = HttpStatus.BAD_REQUEST;
            mensaje="La foto enviada no es válida, debe ser JPEG, JPG O PNG";
        }
        return Utilities.generateResponse(status, mensaje);
    }

    @PutMapping("/productos/{id}")
    public ResponseEntity<Object> productsUpdate(@PathVariable("id") Integer id,@RequestBody CategoriaModel request){
        ProductoModel product = this.productService.searchById(id);
        product.setNombre((request.getNombre()));
        product.setSlug(Utilities.getSlug(request.getNombre()));

        this.productService.save(product);

        return Utilities.generateResponse(HttpStatus.OK, "Se editó el registro de forma éxitosa");
    }

    @DeleteMapping("/productos/{id}")
    public ResponseEntity<Object> productsDelete(@PathVariable("id") Integer id){
        try {
            this.productService.delete(id);
            return Utilities.generateResponse(HttpStatus.OK, "Se eliminó el registro de forma éxitosa");            
        } catch (Exception e) {
            // TODO: handle exception
            return Utilities.generateResponse(HttpStatus.BAD_REQUEST, "Falló eliminar el registro, intente más tarde");
        }
    }
}
