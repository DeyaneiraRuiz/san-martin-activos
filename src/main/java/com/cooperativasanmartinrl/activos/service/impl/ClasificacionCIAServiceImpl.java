package com.cooperativasanmartinrl.activos.service.impl;

import com.cooperativasanmartinrl.activos.entity.ClasificacionCIA;
import com.cooperativasanmartinrl.activos.repository.ClasificacionCIARepository;
import com.cooperativasanmartinrl.activos.service.ClasificacionCIAService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClasificacionCIAServiceImpl implements ClasificacionCIAService {

    private final ClasificacionCIARepository repo;

    public List<ClasificacionCIA> listar() { return repo.findAll(); }

    public ClasificacionCIA obtener(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Clasificación no encontrada"));
    }

    public ClasificacionCIA guardar(ClasificacionCIA c) { return repo.save(c); }

    public ClasificacionCIA actualizar(Long id, ClasificacionCIA data) {
        ClasificacionCIA c = obtener(id);
        c.setConfidencialidad(data.getConfidencialidad());
        c.setIntegridad(data.getIntegridad());
        c.setDisponibilidad(data.getDisponibilidad());
        c.setCriticidad(data.getCriticidad());
        c.setActivo(data.getActivo());
        return repo.save(c);
    }

    public void eliminar(Long id) { repo.deleteById(id); }
}