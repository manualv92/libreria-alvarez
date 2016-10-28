package com.manuel.repository;

import com.manuel.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    List<Cliente> findByNombreLike(String nombre);
    List<Cliente> findByNombreAndHabilitado(String nombre, int habilitado);
    List<Cliente> findByNroDocumento(Long nroDocumento);
    Cliente findById(long id);
}
