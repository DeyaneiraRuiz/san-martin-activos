package com.cooperativasanmartinrl.activos.service.impl;

import com.cooperativasanmartinrl.activos.entity.ProcesoControl;
import com.cooperativasanmartinrl.activos.repository.ProcesoControlRepository;
import com.cooperativasanmartinrl.activos.service.ProcesoControlService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProcesoControlServiceImpl implements ProcesoControlService {

    private final ProcesoControlRepository repo;

    @Override
    public List<ProcesoControl> listar() {
        return repo.findAll();
    }

    @Override
    public ProcesoControl obtener(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Relación Proceso-Control no encontrada"));
    }

    @Override
    public ProcesoControl guardar(ProcesoControl pc) {
        return repo.save(pc);
    }

    @Override
    public ProcesoControl actualizar(Long id, ProcesoControl data) {
        ProcesoControl pc = obtener(id);
        pc.setProceso(data.getProceso());
        pc.setControl(data.getControl());
        return repo.save(pc);
    }

    @Override
    public void eliminar(Long id) {
        repo.deleteById(id);
    }
}