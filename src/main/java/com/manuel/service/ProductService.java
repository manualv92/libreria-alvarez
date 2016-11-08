package com.manuel.service;

import com.manuel.model.Producto;
import com.manuel.model.TipoProducto;
import com.manuel.repository.ProductoRepository;
import com.manuel.repository.TipoProductoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

@Service
@Transactional
public class ProductService {

    @Inject
    ProductoRepository repository;
    @Inject
    TipoProductoRepository tipoProductoRepository;

    public Producto saveProducto(Producto producto){
        Producto resultado = repository.save(producto);
        return resultado;
    }

    public List<Producto> getProductoByNameAndHabilitado(String productName, int productHabilitado){
        List<Producto> resultado = repository.findByNombreContainingIgnoreCaseAndHabilitado(productName, productHabilitado);
        return resultado;
    }

    public List<TipoProducto> getAllTipoProducto(){
        List<TipoProducto> resultado = tipoProductoRepository.findAll();
        return resultado;
    }
    public List<Producto> getAll(){
        List<Producto> resultado = repository.findAll();
        return resultado;
    }

    public Producto getProductoById(long id){
        Producto resultado = repository.findById(id);
        return resultado;
    }
}
