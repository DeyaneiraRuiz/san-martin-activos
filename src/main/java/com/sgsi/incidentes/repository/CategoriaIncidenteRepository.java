package com.sgsi.incidentes.repository;

import com.sgsi.incidentes.entity.CategoriaIncidente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaIncidenteRepository extends JpaRepository<CategoriaIncidente, Integer> {
}
