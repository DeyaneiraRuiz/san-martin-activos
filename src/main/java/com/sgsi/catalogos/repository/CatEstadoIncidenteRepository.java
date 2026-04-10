package com.sgsi.catalogos.repository;

import com.sgsi.catalogos.entity.CatEstadoIncidente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatEstadoIncidenteRepository extends JpaRepository<CatEstadoIncidente, Integer> {
}
