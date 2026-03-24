package com.cooperativasanmartinrl.activos.repository;

import com.cooperativasanmartinrl.activos.entity.TratamientoRiesgo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TratamientoRiesgoRepository extends JpaRepository<TratamientoRiesgo, Long> {
}