package com.cooperativasanmartinrl.activos.service.impl;

import com.cooperativasanmartinrl.activos.entity.Propietario;
import com.cooperativasanmartinrl.activos.repository.PropietarioRepository;
import com.cooperativasanmartinrl.activos.service.PropietarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PropietarioServiceImpl implements PropietarioService {

    private final PropietarioRepository repo;

    public List<Propietario> listar() { return repo.findAll(); }

    public Propietario obtener(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Propietario no encontrado"));
    }

    public Propietario guardar(Propietario p) { return repo.save(p); }

    public Propietario actualizar(Long id, Propietario data) {
        Propietario p = obtener(id);
        p.setNombre(data.getNombre());
        p.setCargo(data.getCargo());
        p.setArea(data.getArea());
        return repo.save(p);
    }

    public void eliminar(Long id) { repo.deleteById(id); }
}