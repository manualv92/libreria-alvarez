package com.manuel.repository;

import com.manuel.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByEmail(String email);
    List<Usuario> findByNroDocumento(Long nroDocumento);
    Usuario findByNroDocumentoAndHabilitado(Long nroDocumento, int userHabilitado);
    List<Usuario> findByNombreAndHabilitado(String userName, int userHabilitado);
    Usuario findById(long id);
}
