package com.sgsi.incidentes.repository;

import com.sgsi.incidentes.entity.IncidenteEvidencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncidenteEvidenciaRepository extends JpaRepository<IncidenteEvidencia, Integer> {
}
