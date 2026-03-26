package com.cooperativasanmartinrl.activos.service.impl;

import com.cooperativasanmartinrl.activos.entity.Rol;
import com.cooperativasanmartinrl.activos.repository.RolRepository;
import com.cooperativasanmartinrl.activos.service.RolService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RolServiceImpl implements RolService {

    private final RolRepository repo;

    public List<Rol> listar() { return repo.findAll(); }

    public Rol obtener(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));
    }

    public Rol guardar(Rol r) { return repo.save(r); }

    public Rol actualizar(Long id, Rol data) {
        Rol r = obtener(id);
        r.setNombre(data.getNombre());
        return repo.save(r);
    }

    public void eliminar(Long id) { repo.deleteById(id); }
}