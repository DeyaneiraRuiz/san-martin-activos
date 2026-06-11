package com.sgsi.procesos.service;

import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;

import com.sgsi.procesos.dto.ControlDto;
import com.sgsi.procesos.entity.Control;
import com.sgsi.procesos.repository.ControlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ControlService {

    private final ControlRepository repository;

    public List<Control> findAll() {
        return repository.findAll();
    }

    public Optional<Control> findById(@NonNull Integer id) {
        return repository.findById(id);
    }

    public Control save(@NonNull Control entity) {
        if (entity.getCodigo() == null || entity.getCodigo().isBlank()) {
            long count = repository.count();
            entity.setCodigo(String.format("CTRL-%03d", count + 1));
        }
        return repository.save(entity);
    }

    @Transactional
    public Optional<Control> update(@NonNull Integer id, @NonNull ControlDto.Request request) {
        return repository.findById(id).map(existing -> {
            existing.setCodigoIso(request.codigoIso());
            existing.setNombre(request.nombre());
            existing.setDescripcion(request.descripcion());
            existing.setTipoControl(request.tipoControl());
            existing.setTipoEjecucion(request.tipoEjecucion());
            existing.setSeEjecutaConFrecuencia(request.seEjecutaConFrecuencia());
            existing.setEstaDocumentado(request.estaDocumentado());
            existing.setTieneEvidencia(request.tieneEvidencia());
            existing.setTieneResponsablesAsociados(request.tieneResponsablesAsociados());
            existing.setCalificacionDisenio(request.calificacionDisenio());
            existing.setSeHanPresentadoEventos(request.seHanPresentadoEventos());
            existing.setDisenioEsEfectivo(request.disenioEsEfectivo());
            existing.setEvidenciaEsEfectiva(request.evidenciaEsEfectiva());
            existing.setCalificacionEjecucion(request.calificacionEjecucion());
            existing.setSolidez(request.solidez());
            if (request.estado() != null) existing.setEstado(request.estado());
            return repository.save(existing);
        });
    }

    public void deleteById(@NonNull Integer id) {
        repository.deleteById(id);
    }
}
