package com.sgsi.procesos.repository;

import com.sgsi.procesos.entity.ProcesoControl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcesoControlRepository extends JpaRepository<ProcesoControl, Integer> {
}
