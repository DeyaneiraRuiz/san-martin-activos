package com.sgsi.catalogos.repository;

import com.sgsi.catalogos.entity.CatEstadoActivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatEstadoActivoRepository extends JpaRepository<CatEstadoActivo, Integer> {
}
