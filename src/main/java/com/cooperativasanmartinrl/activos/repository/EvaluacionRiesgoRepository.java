package com.cooperativasanmartinrl.activos.repository;

import com.cooperativasanmartinrl.activos.entity.EvaluacionRiesgo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvaluacionRiesgoRepository extends JpaRepository<EvaluacionRiesgo, Long> {
}