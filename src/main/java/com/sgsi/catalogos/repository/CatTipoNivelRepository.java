package com.sgsi.catalogos.repository;

import com.sgsi.catalogos.entity.CatTipoNivel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatTipoNivelRepository extends JpaRepository<CatTipoNivel, Integer> {
}
