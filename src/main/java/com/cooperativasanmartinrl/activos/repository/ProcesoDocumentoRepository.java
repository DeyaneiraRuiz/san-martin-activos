package com.cooperativasanmartinrl.activos.repository;

import com.cooperativasanmartinrl.activos.entity.ProcesoDocumento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcesoDocumentoRepository extends JpaRepository<ProcesoDocumento, Long> {
}