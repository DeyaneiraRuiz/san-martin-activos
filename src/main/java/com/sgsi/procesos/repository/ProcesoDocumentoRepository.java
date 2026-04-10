package com.sgsi.procesos.repository;

import com.sgsi.procesos.entity.ProcesoDocumento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcesoDocumentoRepository extends JpaRepository<ProcesoDocumento, Integer> {
}
