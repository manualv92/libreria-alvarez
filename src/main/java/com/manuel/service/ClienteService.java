package com.manuel.service;

import com.manuel.model.Cliente;
import com.manuel.model.TipoDocumento;
import com.manuel.model.TipoPersona;
import com.manuel.repository.ClienteRepository;
import com.manuel.repository.TipoDocumentoRepository;
import com.manuel.repository.TipoPersonaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.management.RuntimeErrorException;
import java.util.List;

/**
 * Created by manua on 25/8/2016.
 */

@Service
@Transactional
public class ClienteService {

    @Inject ClienteRepository repository;
    @Inject TipoPersonaRepository tipoPersonaRepository;
    @Inject TipoDocumentoRepository tipoDocumentoRepository;

    public Cliente saveCliente(Cliente cliente){
        Cliente resultado = repository.save(cliente);
        return resultado;
    }

    public List<Cliente> getClienteByNroDocumento(Long nroDocumento){
        List<Cliente> resultado = repository.findByNroDocumento(nroDocumento);
        return resultado;
    }

    public List<Cliente> getClienteByNameAndHabilitado(String clientName, int clientHabilitado){
        List<Cliente> resultado = repository.findByNombreContainingIgnoreCaseAndHabilitado(clientName, clientHabilitado);
        return resultado;
    }

    /////////
    public List<Cliente> getClienteByNameContainingIgnoreCase(String clientName){
        List<Cliente> resultado = repository.findByNombreContainingIgnoreCase(clientName);
        return resultado;
    }

    public List<TipoPersona> getAllTipoPersona(){
        List<TipoPersona> resultado = tipoPersonaRepository.findAll();
        return resultado;
    }

    public List<TipoDocumento> getAllTipoDocumento(){
        List<TipoDocumento> resultado = tipoDocumentoRepository.findAll();
        return resultado;
    }

    public List<Cliente> getAll(){
        List<Cliente> resultado = repository.findAll();
        return resultado;
    }

    public Cliente getClienteById(long id){
        Cliente resultado = repository.findById(id);
        return resultado;
    }

}
