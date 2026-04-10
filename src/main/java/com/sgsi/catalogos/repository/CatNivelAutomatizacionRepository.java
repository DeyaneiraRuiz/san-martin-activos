package com.sgsi.catalogos.repository;

import com.sgsi.catalogos.entity.CatNivelAutomatizacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatNivelAutomatizacionRepository extends JpaRepository<CatNivelAutomatizacion, Integer> {
}
