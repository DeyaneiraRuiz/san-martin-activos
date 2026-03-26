package com.cooperativasanmartinrl.activos.service.impl;

import com.cooperativasanmartinrl.activos.entity.Amenaza;
import com.cooperativasanmartinrl.activos.repository.AmenazaRepository;
import com.cooperativasanmartinrl.activos.service.AmenazaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AmenazaServiceImpl implements AmenazaService {

    private final AmenazaRepository repo;

    public List<Amenaza> listar() { return repo.findAll(); }

    public Amenaza obtener(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Amenaza no encontrada"));
    }

    public Amenaza guardar(Amenaza a) { return repo.save(a); }

    public Amenaza actualizar(Long id, Amenaza data) {
        Amenaza a = obtener(id);
        a.setNombre(data.getNombre());
        return repo.save(a);
    }

    public void eliminar(Long id) { repo.deleteById(id); }
}