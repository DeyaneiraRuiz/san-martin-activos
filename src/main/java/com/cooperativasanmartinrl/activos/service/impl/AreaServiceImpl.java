package com.cooperativasanmartinrl.activos.service.impl;

import com.cooperativasanmartinrl.activos.entity.Area;
import com.cooperativasanmartinrl.activos.repository.AreaRepository;
import com.cooperativasanmartinrl.activos.service.AreaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AreaServiceImpl implements AreaService {

    private final AreaRepository repo;

    public List<Area> listar() { return repo.findAll(); }

    public Area obtener(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Area no encontrada"));
    }

    public Area guardar(Area area) { return repo.save(area); }

    public Area actualizar(Long id, Area data) {
        Area a = obtener(id);
        a.setNombre(data.getNombre());
        return repo.save(a);
    }

    public void eliminar(Long id) { repo.deleteById(id); }
}