package com.manuel.repository;

import com.manuel.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    List<Cliente> findByNombreContainingIgnoreCase(String nombre);
    List<Cliente> findByNombreContainingIgnoreCaseAndHabilitado(String nombre, int habilitado);
    List<Cliente> findByNroDocumento(Long nroDocumento);
    Cliente findById(long id);
}
