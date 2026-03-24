package com.cooperativasanmartinrl.activos.repository;

import com.cooperativasanmartinrl.activos.entity.ClasificacionCIA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClasificacionCIARepository extends JpaRepository<ClasificacionCIA, Long> {
}