package com.manuel.repository;

import com.manuel.model.TipoDocumento;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by manua on 4/9/2016.
 */
public interface TipoDocumentoRepository extends JpaRepository<TipoDocumento, Long> {
}
