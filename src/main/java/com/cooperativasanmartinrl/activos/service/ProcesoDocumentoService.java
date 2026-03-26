package com.cooperativasanmartinrl.activos.service;

import com.cooperativasanmartinrl.activos.entity.ProcesoDocumento;
import java.util.List;

public interface ProcesoDocumentoService {
    List<ProcesoDocumento> listar();
    ProcesoDocumento obtener(Long id);
    ProcesoDocumento guardar(ProcesoDocumento pd);
    ProcesoDocumento actualizar(Long id, ProcesoDocumento pd);
    void eliminar(Long id);
}