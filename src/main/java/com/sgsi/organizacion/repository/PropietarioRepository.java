package com.sgsi.organizacion.repository;

import com.sgsi.organizacion.entity.Propietario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropietarioRepository extends JpaRepository<Propietario, Integer> {
}
