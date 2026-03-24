package com.cooperativasanmartinrl.activos.repository;

import com.cooperativasanmartinrl.activos.entity.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AreaRepository extends JpaRepository<Area, Long> {
}