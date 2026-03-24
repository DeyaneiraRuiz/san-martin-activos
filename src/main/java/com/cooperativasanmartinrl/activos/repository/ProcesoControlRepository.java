package com.cooperativasanmartinrl.activos.repository;

import com.cooperativasanmartinrl.activos.entity.ProcesoControl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcesoControlRepository extends JpaRepository<ProcesoControl, Long> {
}