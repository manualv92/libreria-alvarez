package com.manuel.repository;

import com.manuel.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
        List<Producto> findByNombreLike(String nombre);
        List<Producto> findByNombreContainingIgnoreCaseAndHabilitado(String nombre, int habilitado);
        Producto findById(long id);
}
