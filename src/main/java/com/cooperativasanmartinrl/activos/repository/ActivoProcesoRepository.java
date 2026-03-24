package com.cooperativasanmartinrl.activos.repository;

import com.cooperativasanmartinrl.activos.entity.ActivoProceso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivoProcesoRepository extends JpaRepository<ActivoProceso, Long> {
}