package com.cooperativasanmartinrl.activos.repository;

import com.cooperativasanmartinrl.activos.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {
}