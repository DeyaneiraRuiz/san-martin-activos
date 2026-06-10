package com.sgsi.incidentes.repository;

import com.sgsi.incidentes.entity.TipoIncidente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TipoIncidenteRepository extends JpaRepository<TipoIncidente, Integer> {
    List<TipoIncidente> findByCategoriaId(Integer categoriaId);
}