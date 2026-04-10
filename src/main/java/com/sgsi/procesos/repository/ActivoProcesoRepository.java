package com.sgsi.procesos.repository;

import com.sgsi.procesos.entity.ActivoProceso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivoProcesoRepository extends JpaRepository<ActivoProceso, Integer> {
}
