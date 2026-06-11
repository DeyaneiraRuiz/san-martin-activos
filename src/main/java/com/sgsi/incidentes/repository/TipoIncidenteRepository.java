package com.sgsi.incidentes.repository;

import com.sgsi.incidentes.entity.TipoIncidente;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TipoIncidenteRepository extends JpaRepository<TipoIncidente, Integer> {
    List<TipoIncidente> findByCategoriaId(Integer categoriaId);
}