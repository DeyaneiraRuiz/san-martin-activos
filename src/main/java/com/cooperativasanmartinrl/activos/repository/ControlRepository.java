package com.cooperativasanmartinrl.activos.repository;

import com.cooperativasanmartinrl.activos.entity.Control;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ControlRepository extends JpaRepository<Control, Long> {
}