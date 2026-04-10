package com.sgsi.catalogos.repository;

import com.sgsi.catalogos.entity.CatTipoVinculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatTipoVinculoRepository extends JpaRepository<CatTipoVinculo, Integer> {
}
