package com.sgsi.riesgos.service;

import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;

import com.sgsi.riesgos.dto.RiesgoDto;
import com.sgsi.riesgos.entity.Amenaza;
import com.sgsi.riesgos.entity.Riesgo;
import com.sgsi.riesgos.entity.Vulnerabilidad;
import com.sgsi.riesgos.repository.RiesgoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RiesgoService {

    private final RiesgoRepository repository;

    public List<Riesgo> findAll() {
        return repository.findAll();
    }

    public Optional<Riesgo> findById(@NonNull Integer id) {
        return repository.findById(id);
    }

    public Riesgo save(@NonNull Riesgo entity) {
        if (entity.getCodigo() == null || entity.getCodigo().isBlank()) {
            long count = repository.count();
            entity.setCodigo(String.format("R-%03d", count + 1));
        }
        return repository.save(entity);
    }

    @Transactional
    public Optional<Riesgo> update(@NonNull Integer id, @NonNull RiesgoDto.Request request) {
        return repository.findById(id).map(existing -> {
            existing.setNombre(request.nombre());
            existing.setDescripcion(request.descripcion());
            existing.setImpactoNegocio(request.impactoNegocio());
            existing.setConsecuencia(request.consecuencia());
            existing.setImpacto(request.impacto());
            existing.setFrecuencia(request.frecuencia());
            existing.setCriticidadInherente(request.criticidadInherente());
            existing.setEfectividadControles(request.efectividadControles());
            existing.setCriticidadResidual(request.criticidadResidual());
            existing.setCategoria(request.categoria());
            existing.setEstado(request.estado() != null ? request.estado() : existing.getEstado());
            existing.setFechaIdentificacion(request.fechaIdentificacion());
            existing.setArchivoNombre(request.archivoNombre());

            if (request.amenazaId() != null) {
                Amenaza a = new Amenaza();
                a.setId(request.amenazaId());
                existing.setAmenaza(a);
            }
            if (request.vulnerabilidadId() != null) {
                Vulnerabilidad v = new Vulnerabilidad();
                v.setId(request.vulnerabilidadId());
                existing.setVulnerabilidad(v);
            }
            return repository.save(existing);
        });
    }

    public void deleteById(@NonNull Integer id) {
        repository.deleteById(id);
    }
}
