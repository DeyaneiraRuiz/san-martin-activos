package com.sgsi.procesos.service;

import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;

import com.sgsi.catalogos.entity.CatNivelAutomatizacion;
import com.sgsi.catalogos.entity.CatPeriodicidad;
import com.sgsi.procesos.dto.ProcesoDto;
import com.sgsi.procesos.entity.Proceso;
import com.sgsi.procesos.repository.ProcesoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProcesoService {

    private final ProcesoRepository repository;

    public List<Proceso> findAll() {
        return repository.findAll();
    }

    public Optional<Proceso> findById(@NonNull Integer id) {
        return repository.findById(id);
    }

    public Proceso save(@NonNull Proceso entity) {
        if (entity.getCodigo() == null || entity.getCodigo().isBlank()) {
            long count = repository.count();
            entity.setCodigo(String.format("P-%03d", count + 1));
        }
        return repository.save(entity);
    }

    @Transactional
    public Optional<Proceso> update(@NonNull Integer id, @NonNull ProcesoDto.Request request) {
        return repository.findById(id).map(existing -> {
            existing.setNombre(request.nombre());
            existing.setTipo(request.tipo());
            existing.setCategoria(request.categoria());
            existing.setDescripcion(request.descripcion());
            existing.setImpacto(request.impacto());
            existing.setEstado(request.estado() != null ? request.estado() : existing.getEstado());
            existing.setEsCritico(request.esCritico() != null ? request.esCritico() : existing.getEsCritico());

            if (request.periodicidadId() != null) {
                CatPeriodicidad p = new CatPeriodicidad();
                p.setId(request.periodicidadId());
                existing.setPeriodicidad(p);
            }
            if (request.nivelAutomatizacionId() != null) {
                CatNivelAutomatizacion n = new CatNivelAutomatizacion();
                n.setId(request.nivelAutomatizacionId());
                existing.setNivelAutomatizacion(n);
            }
            return repository.save(existing);
        });
    }

    public void deleteById(@NonNull Integer id) {
        repository.deleteById(id);
    }
}
