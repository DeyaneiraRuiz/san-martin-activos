package com.cooperativasanmartinrl.activos.repository;

import com.cooperativasanmartinrl.activos.entity.NivelRiesgo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NivelRiesgoRepository extends JpaRepository<NivelRiesgo, Long> {
}