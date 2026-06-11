package com.sgsi.activos.service;

import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;

import com.sgsi.activos.dto.ActivoDto;
import com.sgsi.activos.entity.Activo;
import com.sgsi.activos.repository.ActivoRepository;
import com.sgsi.catalogos.entity.CatEstadoActivo;
import com.sgsi.catalogos.entity.CatTipoActivo;
import com.sgsi.organizacion.entity.Area;
import com.sgsi.organizacion.entity.Propietario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ActivoService {

    private final ActivoRepository repository;

    public List<Activo> findAll() {
        return repository.findAll();
    }

    public Optional<Activo> findById(@NonNull Integer id) {
        return repository.findById(id);
    }

    public Activo save(@NonNull Activo entity) {
        if (entity.getCodigo() == null || entity.getCodigo().isBlank()) {
            long count = repository.count();
            entity.setCodigo(String.format("A-%03d", count + 1));
        }
        return repository.save(entity);
    }

    @Transactional
    public Optional<Activo> update(@NonNull Integer id, @NonNull ActivoDto.Request request) {
        return repository.findById(id).map(existing -> {
            existing.setNombre(request.nombre());
            existing.setDescripcion(request.descripcion());
            existing.setConfidencialidad(request.confidencialidad());
            existing.setIntegridad(request.integridad());
            existing.setDisponibilidad(request.disponibilidad());
            existing.setConfidencialidadAlmacenamiento(request.confidencialidadAlmacenamiento());
            existing.setProbabilidad(request.probabilidad());
            existing.setImpacto(request.impacto());
            existing.setUbicacion(request.ubicacion());
            existing.setFechaAdquisicion(request.fechaAdquisicion());

            if (request.tipoActivoId() != null) {
                CatTipoActivo ta = new CatTipoActivo();
                ta.setId(request.tipoActivoId());
                existing.setTipoActivo(ta);
            }
            if (request.areaId() != null) {
                Area a = new Area();
                a.setId(request.areaId());
                existing.setArea(a);
            }
            if (request.estadoId() != null) {
                CatEstadoActivo e = new CatEstadoActivo();
                e.setId(request.estadoId());
                existing.setEstado(e);
            }
            if (request.propietarioId() != null) {
                Propietario p = new Propietario();
                p.setId(request.propietarioId());
                existing.setPropietario(p);
            }
            return repository.save(existing);
        });
    }

    public void deleteById(@NonNull Integer id) {
        repository.deleteById(id);
    }
}
