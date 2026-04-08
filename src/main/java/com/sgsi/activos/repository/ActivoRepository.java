package com.sgsi.activos.repository;

import com.sgsi.activos.entity.Activo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivoRepository extends JpaRepository<Activo, Integer> {
}
