package com.cooperativasanmartinrl.activos.service.impl;

import com.cooperativasanmartinrl.activos.entity.NivelRiesgo;
import com.cooperativasanmartinrl.activos.repository.NivelRiesgoRepository;
import com.cooperativasanmartinrl.activos.service.NivelRiesgoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NivelRiesgoServiceImpl implements NivelRiesgoService {

    private final NivelRiesgoRepository repo;

    public List<NivelRiesgo> listar() { return repo.findAll(); }

    public NivelRiesgo obtener(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Nivel de riesgo no encontrado"));
    }

    public NivelRiesgo guardar(NivelRiesgo n) { return repo.save(n); }

    public NivelRiesgo actualizar(Long id, NivelRiesgo data) {
        NivelRiesgo n = obtener(id);
        n.setNombre(data.getNombre());
        n.setValorMin(data.getValorMin());
        n.setValorMax(data.getValorMax());
        return repo.save(n);
    }

    public void eliminar(Long id) { repo.deleteById(id); }
}