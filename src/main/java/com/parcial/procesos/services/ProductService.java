package com.parcial.procesos.services;
import com.parcial.procesos.models.Product;
import java.util.List;

public interface ProductService{

    List<Product> consumirApi();

    List<Product> ObtenerProductos();

    Product ObtenerProductosId (Long id);

    Product ActualizarProductos(Long id,Product product);


}
