package com.cooperativasanmartinrl.activos.service.impl;

import com.cooperativasanmartinrl.activos.entity.Vulnerabilidad;
import com.cooperativasanmartinrl.activos.repository.VulnerabilidadRepository;
import com.cooperativasanmartinrl.activos.service.VulnerabilidadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VulnerabilidadServiceImpl implements VulnerabilidadService {

    private final VulnerabilidadRepository repo;

    public List<Vulnerabilidad> listar() { return repo.findAll(); }

    public Vulnerabilidad obtener(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Vulnerabilidad no encontrada"));
    }

    public Vulnerabilidad guardar(Vulnerabilidad v) { return repo.save(v); }

    public Vulnerabilidad actualizar(Long id, Vulnerabilidad data) {
        Vulnerabilidad v = obtener(id);
        v.setNombre(data.getNombre());
        return repo.save(v);
    }

    public void eliminar(Long id) { repo.deleteById(id); }
}