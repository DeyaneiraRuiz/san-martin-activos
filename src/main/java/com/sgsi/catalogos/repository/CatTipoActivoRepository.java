package com.sgsi.catalogos.repository;

import com.sgsi.catalogos.entity.CatTipoActivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatTipoActivoRepository extends JpaRepository<CatTipoActivo, Integer> {
}
