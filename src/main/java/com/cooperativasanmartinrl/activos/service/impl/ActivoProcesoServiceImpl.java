package com.cooperativasanmartinrl.activos.service.impl;

import com.cooperativasanmartinrl.activos.entity.ActivoProceso;
import com.cooperativasanmartinrl.activos.repository.ActivoProcesoRepository;
import com.cooperativasanmartinrl.activos.service.ActivoProcesoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ActivoProcesoServiceImpl implements ActivoProcesoService {

    private final ActivoProcesoRepository repo;

    @Override
    public List<ActivoProceso> listar() {
        return repo.findAll();
    }

    @Override
    public ActivoProceso obtener(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Relación Activo-Proceso no encontrada"));
    }

    @Override
    public ActivoProceso guardar(ActivoProceso ap) {
        return repo.save(ap);
    }

    @Override
    public ActivoProceso actualizar(Long id, ActivoProceso data) {
        ActivoProceso ap = obtener(id);
        ap.setActivo(data.getActivo());
        ap.setProceso(data.getProceso());
        return repo.save(ap);
    }

    @Override
    public void eliminar(Long id) {
        repo.deleteById(id);
    }
}