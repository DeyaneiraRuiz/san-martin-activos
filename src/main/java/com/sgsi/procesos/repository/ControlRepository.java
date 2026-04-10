package com.sgsi.procesos.repository;

import com.sgsi.procesos.entity.Control;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ControlRepository extends JpaRepository<Control, Integer> {
}
