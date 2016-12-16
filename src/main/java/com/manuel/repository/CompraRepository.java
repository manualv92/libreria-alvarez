package com.manuel.repository;

import com.manuel.model.Compra;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by manua on 7/10/2016.
 */
public interface CompraRepository extends JpaRepository<Compra, Long> {
    List<Compra> findByFechaContainingIgnoreCase(String fecha);
}
