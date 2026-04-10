package com.sgsi.incidentes.repository;

import com.sgsi.incidentes.entity.Sla;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SlaRepository extends JpaRepository<Sla, Integer> {
}
