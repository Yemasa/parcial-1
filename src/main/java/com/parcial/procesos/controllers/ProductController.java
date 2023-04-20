package com.parcial.procesos.controllers;

import com.parcial.procesos.models.Product;
import com.parcial.procesos.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/productos")
    public ResponseEntity consumirApi() {
        Map response = new HashMap();
        try{
            response.put("message","Consumido correctamente");
            response.put("data",productService.consumirApi());
            return new ResponseEntity(response, HttpStatus.CREATED);
        }catch (Exception e){
            response.put("message","Error");
            response.put("data",e.getMessage());
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }

    }
    @GetMapping(value = "/productos")
    public ResponseEntity ObtenerProductos() {
        Map response = new HashMap();
        try {
                response.put("message", "productos encontrados");
                response.put("data", productService.ObtenerProductos());
                return new ResponseEntity(response, HttpStatus.OK);
            } catch (Exception e) {
            response.put("message", "productos no encontrados");
            response.put("data", null);//userService.getUserById(id) //Optional.Empty //"e.getMessage()"
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping(value = "/productos/{id}")
    public ResponseEntity ObtenerProductosId(@PathVariable(name = "id")Long id){
        Map response = new HashMap();
        try{
            response.put("mensaje", "producto obtenido");
            response.put("data", productService.ObtenerProductosId(id));
            return new ResponseEntity(response, HttpStatus.OK);
        }catch (Exception e){
            response.put("mensaje","error");
            response.put("data",e.getMessage());
            return new ResponseEntity(response,HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping(value = "/productos/{id}")
    public ResponseEntity ActualizarProductos(@PathVariable(name = "id") Long id, @RequestBody Product product){
        Map response = new HashMap();
        try{
            response.put("message", "producto actualizado");
            response.put("data", productService.ActualizarProductos(id, product));
            return new ResponseEntity(response, HttpStatus.OK);
        }catch (Exception e){
            response.put("message","Error");
            response.put("data",id);
            return new ResponseEntity(response,HttpStatus.NOT_FOUND);
        }
    }

}