package com.sgsi.riesgos.repository;

import com.sgsi.riesgos.entity.Vulnerabilidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VulnerabilidadRepository extends JpaRepository<Vulnerabilidad, Integer> {
}
