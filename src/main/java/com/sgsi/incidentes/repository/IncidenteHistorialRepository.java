package com.sgsi.incidentes.repository;

import com.sgsi.incidentes.entity.IncidenteHistorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncidenteHistorialRepository extends JpaRepository<IncidenteHistorial, Integer> {
}
