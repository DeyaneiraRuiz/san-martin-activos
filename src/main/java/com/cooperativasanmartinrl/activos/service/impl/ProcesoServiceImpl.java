package com.cooperativasanmartinrl.activos.service.impl;

import com.cooperativasanmartinrl.activos.entity.Proceso;
import com.cooperativasanmartinrl.activos.repository.ProcesoRepository;
import com.cooperativasanmartinrl.activos.service.ProcesoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProcesoServiceImpl implements ProcesoService {

    private final ProcesoRepository repo;

    public List<Proceso> listar() { return repo.findAll(); }

    public Proceso obtener(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Proceso no encontrado"));
    }

    public Proceso guardar(Proceso p) { return repo.save(p); }

    public Proceso actualizar(Long id, Proceso data) {
        Proceso p = obtener(id);
        p.setNombre(data.getNombre());
        p.setDescripcion(data.getDescripcion());
        p.setPeriodicidad(data.getPeriodicidad());
        p.setNivelAutomatizacion(data.getNivelAutomatizacion());
        p.setGradoDescentralizacion(data.getGradoDescentralizacion());
        p.setTiempoProceso(data.getTiempoProceso());
        p.setEsCritico(data.getEsCritico());
        p.setProductoServicio(data.getProductoServicio());
        return repo.save(p);
    }

    public void eliminar(Long id) { repo.deleteById(id); }
}