package com.manuel.service;

import com.manuel.model.Compra;
import com.manuel.repository.CompraRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

@Service
@Transactional
public class CompraService {

    @Inject
    CompraRepository repository;

    public Compra saveCompra(Compra compra){
        Compra resultado = repository.save(compra);
        return resultado;
    }

    public List<Compra> getAll(){
        List<Compra> resultado = repository.findAll();
        return resultado;
    }
}
