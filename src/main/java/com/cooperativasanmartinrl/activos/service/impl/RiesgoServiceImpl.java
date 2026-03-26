package com.cooperativasanmartinrl.activos.service.impl;

import com.cooperativasanmartinrl.activos.entity.Riesgo;
import com.cooperativasanmartinrl.activos.repository.RiesgoRepository;
import com.cooperativasanmartinrl.activos.service.RiesgoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RiesgoServiceImpl implements RiesgoService {

    private final RiesgoRepository repo;

    public List<Riesgo> listar() { return repo.findAll(); }

    public Riesgo obtener(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Riesgo no encontrado"));
    }

    public Riesgo guardar(Riesgo r) { return repo.save(r); }

    public Riesgo actualizar(Long id, Riesgo data) {
        Riesgo r = obtener(id);
        r.setDescripcion(data.getDescripcion());
        r.setTipoTratamiento(data.getTipoTratamiento());
        r.setActivo(data.getActivo());
        r.setProceso(data.getProceso());
        r.setAmenaza(data.getAmenaza());
        r.setVulnerabilidad(data.getVulnerabilidad());
        return repo.save(r);
    }

    public void eliminar(Long id) { repo.deleteById(id); }
}