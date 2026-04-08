package com.sgsi.procesos.repository;

import com.sgsi.procesos.entity.Proceso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcesoRepository extends JpaRepository<Proceso, Integer> {
}
