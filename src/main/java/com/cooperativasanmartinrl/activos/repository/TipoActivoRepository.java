package com.cooperativasanmartinrl.activos.repository;

import com.cooperativasanmartinrl.activos.entity.TipoActivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoActivoRepository extends JpaRepository<TipoActivo, Long> {
}