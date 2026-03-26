package com.cooperativasanmartinrl.activos.service.impl;

import com.cooperativasanmartinrl.activos.entity.Activo;
import com.cooperativasanmartinrl.activos.repository.ActivoRepository;
import com.cooperativasanmartinrl.activos.service.ActivoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ActivoServiceImpl implements ActivoService {

    private final ActivoRepository repo;

    @Override
    public List<Activo> listar() {
        return repo.findAll();
    }

    @Override
    public Activo obtener(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Activo no encontrado"));
    }

    @Override
    public Activo guardar(Activo activo) {
        activo.setCreatedAt(LocalDateTime.now());
        return repo.save(activo);
    }

    @Override
    public Activo actualizar(Long id, Activo data) {
        Activo a = obtener(id);
        a.setNombre(data.getNombre());
        a.setDescripcion(data.getDescripcion());
        a.setMedio(data.getMedio());
        a.setOrigen(data.getOrigen());
        a.setTipoActivo(data.getTipoActivo());
        a.setArea(data.getArea());
        a.setPropietario(data.getPropietario());
        return repo.save(a);
    }

    @Override
    public void eliminar(Long id) {
        repo.deleteById(id);
    }
}