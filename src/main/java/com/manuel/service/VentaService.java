package com.manuel.service;

import com.manuel.model.Venta;
import com.manuel.repository.VentaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;


@Service
@Transactional
public class VentaService {

    @Inject
    VentaRepository repository;

    public Venta saveVenta(Venta venta){
        Venta resultado = repository.save(venta);
        return resultado;
    }

    public List<Venta> getAll(){
        List<Venta> resultado = repository.findAll();
        return resultado;
    }

    public Venta getById(long id){
        Venta resultado = repository.findById(id);
        return resultado;
    }

    public List<Venta> getCompraByFecha(String sellDate){
        List<Venta> resultado = repository.findByFechaContainingIgnoreCase(sellDate);
        return resultado;
    }

}
