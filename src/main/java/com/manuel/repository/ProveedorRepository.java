package com.manuel.repository;

import com.manuel.model.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by manua on 1/10/2016.
 */
public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {
    List<Proveedor> findByNombreAndHabilitado(String nombre, int habilitado);
    Proveedor findById(long id);
}
