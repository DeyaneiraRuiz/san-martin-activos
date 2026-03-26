package com.cooperativasanmartinrl.activos.service.impl;

import com.cooperativasanmartinrl.activos.entity.TipoActivo;
import com.cooperativasanmartinrl.activos.repository.TipoActivoRepository;
import com.cooperativasanmartinrl.activos.service.TipoActivoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TipoActivoServiceImpl implements TipoActivoService {

    private final TipoActivoRepository repo;

    public List<TipoActivo> listar() { return repo.findAll(); }

    public TipoActivo obtener(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Tipo activo no encontrado"));
    }

    public TipoActivo guardar(TipoActivo t) { return repo.save(t); }

    public TipoActivo actualizar(Long id, TipoActivo data) {
        TipoActivo t = obtener(id);
        t.setNombre(data.getNombre());
        return repo.save(t);
    }

    public void eliminar(Long id) { repo.deleteById(id); }
}