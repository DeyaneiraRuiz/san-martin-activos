package com.cooperativasanmartinrl.activos.service.impl;

import com.cooperativasanmartinrl.activos.entity.EvaluacionRiesgo;
import com.cooperativasanmartinrl.activos.repository.EvaluacionRiesgoRepository;
import com.cooperativasanmartinrl.activos.service.EvaluacionRiesgoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EvaluacionRiesgoServiceImpl implements EvaluacionRiesgoService {

    private final EvaluacionRiesgoRepository repo;

    public List<EvaluacionRiesgo> listar() { return repo.findAll(); }

    public EvaluacionRiesgo obtener(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Evaluación no encontrada"));
    }

    public EvaluacionRiesgo guardar(EvaluacionRiesgo e) {
        e.setFecha(LocalDateTime.now());
        return repo.save(e);
    }

    public EvaluacionRiesgo actualizar(Long id, EvaluacionRiesgo data) {
        EvaluacionRiesgo e = obtener(id);
        e.setImpacto(data.getImpacto());
        e.setProbabilidad(data.getProbabilidad());
        e.setNivelRiesgo(data.getNivelRiesgo());
        e.setRiesgo(data.getRiesgo());
        return repo.save(e);
    }

    public void eliminar(Long id) { repo.deleteById(id); }
}