package com.cooperativasanmartinrl.activos.repository;

import com.cooperativasanmartinrl.activos.entity.Documento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentoRepository extends JpaRepository<Documento, Long> {
}