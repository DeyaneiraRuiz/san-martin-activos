package com.cooperativasanmartinrl.activos.service.impl;

import com.cooperativasanmartinrl.activos.entity.ProcesoDocumento;
import com.cooperativasanmartinrl.activos.repository.ProcesoDocumentoRepository;
import com.cooperativasanmartinrl.activos.service.ProcesoDocumentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProcesoDocumentoServiceImpl implements ProcesoDocumentoService {

    private final ProcesoDocumentoRepository repo;

    @Override
    public List<ProcesoDocumento> listar() {
        return repo.findAll();
    }

    @Override
    public ProcesoDocumento obtener(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Relación Proceso-Documento no encontrada"));
    }

    @Override
    public ProcesoDocumento guardar(ProcesoDocumento pd) {
        return repo.save(pd);
    }

    @Override
    public ProcesoDocumento actualizar(Long id, ProcesoDocumento data) {
        ProcesoDocumento pd = obtener(id);
        pd.setProceso(data.getProceso());
        pd.setDocumento(data.getDocumento());
        return repo.save(pd);
    }

    @Override
    public void eliminar(Long id) {
        repo.deleteById(id);
    }
}