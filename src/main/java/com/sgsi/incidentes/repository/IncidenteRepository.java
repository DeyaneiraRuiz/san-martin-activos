package com.sgsi.incidentes.repository;

import com.sgsi.incidentes.entity.Incidente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncidenteRepository extends JpaRepository<Incidente, Integer> {

    boolean existsByCodigo(String codigo);

    List<Incidente> findByEstadoId(Integer estadoId);

    List<Incidente> findByCategoriaId(Integer categoriaId);

    List<Incidente> findByTipoIncidenteId(Integer tipoIncidenteId);

    List<Incidente> findByAsignadoAId(Integer usuarioId);

    List<Incidente> findByReportadorId(Integer usuarioId);

}