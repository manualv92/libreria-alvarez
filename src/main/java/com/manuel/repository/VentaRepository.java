package com.manuel.repository;

import com.manuel.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by manua on 11/10/2016.
 */
public interface VentaRepository extends JpaRepository<Venta, Long> {
    Venta findById(long id);
    List<Venta> findByFechaContainingIgnoreCase(String fecha);
}
