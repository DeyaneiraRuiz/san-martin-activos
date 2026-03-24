package com.cooperativasanmartinrl.activos.repository;

import com.cooperativasanmartinrl.activos.entity.Proceso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcesoRepository extends JpaRepository<Proceso, Long> {
}