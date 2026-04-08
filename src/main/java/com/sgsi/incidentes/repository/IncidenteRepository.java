package com.sgsi.incidentes.repository;

import com.sgsi.incidentes.entity.Incidente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncidenteRepository extends JpaRepository<Incidente, Integer> {
}
