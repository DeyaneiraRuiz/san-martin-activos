package com.sgsi.riesgos.repository;

import com.sgsi.riesgos.entity.Riesgo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RiesgoRepository extends JpaRepository<Riesgo, Integer> {
}
