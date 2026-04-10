package com.sgsi.catalogos.repository;

import com.sgsi.catalogos.entity.CatPeriodicidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatPeriodicidadRepository extends JpaRepository<CatPeriodicidad, Integer> {
}
