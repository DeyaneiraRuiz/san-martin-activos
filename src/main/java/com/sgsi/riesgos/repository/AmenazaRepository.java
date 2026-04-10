package com.sgsi.riesgos.repository;

import com.sgsi.riesgos.entity.Amenaza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AmenazaRepository extends JpaRepository<Amenaza, Integer> {
}
