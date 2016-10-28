package com.manuel.service;

import com.manuel.model.Rol;
import com.manuel.model.Usuario;
import com.manuel.repository.RolRepository;
import com.manuel.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

@Service
@Transactional
public class UsuarioService {

    @Inject
    RolRepository rolRepository;
    @Inject
    UsuarioRepository repository;


    public Usuario saveUsuario(Usuario usuario){
        Usuario resultado = repository.save(usuario);
        return resultado;
    }

    public List<Usuario> getUsuariosByNroDocumento(Long nroDocumento){
        List<Usuario> resultado = repository.findByNroDocumento(nroDocumento);
        return resultado;
    }

    public List<Rol> getAllRoles(){
        List<Rol> resultado = rolRepository.findAll();
        return resultado;
    }

    public List<Usuario> getUsuarioByNameAndHabilitado(String userName, int userHabilitado) {
        List<Usuario> resultado = repository.findByNombreAndHabilitado(userName, userHabilitado);
        return resultado;
    }
    public Usuario getUsuarioByNroDocumentoAndHabilitado(long userNroDocumento, int userHabilitado) {
        Usuario resultado = repository.findByNroDocumentoAndHabilitado(userNroDocumento, userHabilitado);
        return resultado;
    }
    public Usuario getUsuarioById(long id) {
        Usuario resultado = repository.findById(id);
        return resultado;
    }
}
