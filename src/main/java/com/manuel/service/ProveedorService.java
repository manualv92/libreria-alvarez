package com.manuel.service;

import com.manuel.model.Proveedor;
import com.manuel.repository.ProveedorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

@Service
@Transactional
public class ProveedorService {
    @Inject
    ProveedorRepository repository;

    public Proveedor saveProveedor(Proveedor proveedor){
        Proveedor resultado = repository.save(proveedor);
        return resultado;
    }

    public List<Proveedor> getProveedorByNameAndHabilitado(String providerName, int providerHabilitado){
        List<Proveedor> resultado = repository.findByNombreContainingIgnoreCaseAndHabilitado(providerName, providerHabilitado);
        return resultado;
    }

    public List<Proveedor> getAll(){
        List<Proveedor> resultado = repository.findAll();
        return resultado;
    }

    public Proveedor getProveedorById(long id){
        Proveedor resultado = repository.findById(id);
        return resultado;
    }
}
