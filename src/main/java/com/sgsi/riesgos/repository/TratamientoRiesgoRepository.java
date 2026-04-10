package com.sgsi.riesgos.repository;

import com.sgsi.riesgos.entity.TratamientoRiesgo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TratamientoRiesgoRepository extends JpaRepository<TratamientoRiesgo, Integer> {
}
