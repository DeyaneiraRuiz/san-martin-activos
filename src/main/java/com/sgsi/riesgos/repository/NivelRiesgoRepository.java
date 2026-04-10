package com.sgsi.riesgos.repository;

import com.sgsi.riesgos.entity.NivelRiesgo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NivelRiesgoRepository extends JpaRepository<NivelRiesgo, Integer> {
}
