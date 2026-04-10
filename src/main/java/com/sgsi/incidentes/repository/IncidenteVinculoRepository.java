package com.sgsi.incidentes.repository;

import com.sgsi.incidentes.entity.IncidenteVinculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncidenteVinculoRepository extends JpaRepository<IncidenteVinculo, Integer> {
}
