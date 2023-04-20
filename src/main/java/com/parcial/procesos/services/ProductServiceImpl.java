package com.parcial.procesos.services;

import com.parcial.procesos.models.Product;
import com.parcial.procesos.repositorys.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    public List<Product> consumirApi(){
        RestTemplate restTemplate = new RestTemplate();
        Product[] consumidos = restTemplate.getForObject("https://fakestoreapi.com/products", Product[].class);
        List<Product> locales = new ArrayList<>();
        for (Product productoExterno : consumidos) {
            locales.add(productRepository.save(productoExterno));
        }
        return locales;
    }

    public List<Product> ObtenerProductos(){
        return productRepository.findAll();
    }

    public Product ObtenerProductosId(Long id){
        return productRepository.findById(id).get();
    }

    public Product ActualizarProductos (Long id, Product product){
        Product DB = productRepository.findById(id).get();
        DB.setTitle(product.getTitle());
        DB.setPrice(product.getPrice());
        DB.setCategory(product.getCategory());
        DB.setDescription(product.getDescription());
        DB.setImage(product.getImage());

        return productRepository.save(DB);
    }

}
