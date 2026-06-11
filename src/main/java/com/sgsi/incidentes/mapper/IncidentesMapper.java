package com.sgsi.incidentes.mapper;

import com.sgsi.activos.entity.Activo;
import com.sgsi.incidentes.dto.*;
import com.sgsi.incidentes.entity.*;
import com.sgsi.security.entity.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IncidentesMapper {

    // =====================================================
    // SLA
    // =====================================================

    SlaDto.Response toResponse(Sla entity);

    Sla toEntity(SlaDto.Request request);

    void updateEntityFromRequest(
            SlaDto.Request request,
            @MappingTarget Sla entity);

    // =====================================================
    // CATEGORIA INCIDENTE
    // =====================================================

    @Mapping(source = "responsable.id", target = "responsableId")
    CategoriaIncidenteDto.Response toResponse(
            CategoriaIncidente entity);

    @Mapping(source = "responsableId", target = "responsable.id")
    CategoriaIncidente toEntity(
            CategoriaIncidenteDto.Request request);

    @Mapping(source = "responsableId", target = "responsable.id")
    void updateEntityFromRequest(
            CategoriaIncidenteDto.Request request,
            @MappingTarget CategoriaIncidente entity);

    // =====================================================
    // INCIDENTE (SOLO RESPONSE)
    // =====================================================

    @Mapping(source = "categoria.id", target = "categoriaId")
    @Mapping(source = "categoria.nombre", target = "categoriaNombre")

    @Mapping(source = "tipoIncidente.id", target = "tipoIncidenteId")
    @Mapping(source = "tipoIncidente.nombre", target = "tipoIncidenteNombre")

    @Mapping(source = "gravedadReportada.id", target = "gravedadReportadaId")
    @Mapping(source = "gravedadReportada.nombre", target = "gravedadReportadaNombre")

    @Mapping(source = "reportador.id", target = "reportadorId")
    @Mapping(source = "reportador.nombreCompleto", target = "reportadorNombre")

    @Mapping(source = "asignadoA.id", target = "asignadoAId")
    @Mapping(source = "asignadoA.nombreCompleto", target = "asignadoANombre")

    @Mapping(source = "asignadoPor.id", target = "asignadoPorId")
    @Mapping(source = "asignadoPor.nombreCompleto", target = "asignadoPorNombre")

    @Mapping(source = "impactoReal.id", target = "impactoRealId")
    @Mapping(source = "impactoReal.nombre", target = "impactoRealNombre")

    @Mapping(source = "prioridad.id", target = "prioridadId")
    @Mapping(source = "prioridad.nombre", target = "prioridadNombre")

    @Mapping(source = "cerradoPor.id", target = "cerradoPorId")
    @Mapping(source = "cerradoPor.nombreCompleto", target = "cerradoPorNombre")

    @Mapping(source = "estado.id", target = "estadoId")
    @Mapping(source = "estado.nombre", target = "estadoNombre")

    @Mapping(source = "reabiertoPor.id", target = "reabiertoPorId")
    @Mapping(source = "reabiertoPor.nombreCompleto", target = "reabiertoPorNombre")

    @Mapping(source = "ultimoModificadoPor.id", target = "ultimoModificadoPorId")
    @Mapping(source = "ultimoModificadoPor.nombreCompleto", target = "ultimoModificadoPorNombre")

    @Mapping(source = "observadores", target = "observadorIds")
    @Mapping(source = "activosAfectados", target = "activoIds")
    IncidenteDto.Response toResponse(
            Incidente entity);

    // =====================================================
    // EVIDENCIAS
    // =====================================================

    @Mapping(source = "incidente.id", target = "incidenteId")
    IncidenteEvidenciaDto.Response toResponse(
            IncidenteEvidencia entity);

    @Mapping(source = "incidenteId", target = "incidente.id")
    IncidenteEvidencia toEntity(
            IncidenteEvidenciaDto.Request request);

    @Mapping(source = "incidenteId", target = "incidente.id")
    void updateEntityFromRequest(
            IncidenteEvidenciaDto.Request request,
            @MappingTarget IncidenteEvidencia entity);

    // =====================================================
    // HISTORIAL
    // =====================================================

    @Mapping(source = "incidente.id", target = "incidenteId")
    @Mapping(source = "estado.id", target = "estadoId")
    @Mapping(source = "changedBy.id", target = "changedById")
    IncidenteHistorialDto.Response toResponse(
            IncidenteHistorial entity);

    @Mapping(source = "incidenteId", target = "incidente.id")
    @Mapping(source = "estadoId", target = "estado.id")
    @Mapping(source = "changedById", target = "changedBy.id")
    IncidenteHistorial toEntity(
            IncidenteHistorialDto.Request request);

    @Mapping(source = "incidenteId", target = "incidente.id")
    @Mapping(source = "estadoId", target = "estado.id")
    @Mapping(source = "changedById", target = "changedBy.id")
    void updateEntityFromRequest(
            IncidenteHistorialDto.Request request,
            @MappingTarget IncidenteHistorial entity);

    // =====================================================
    // VINCULOS
    // =====================================================

    @Mapping(source = "incidente.id", target = "incidenteId")
    @Mapping(source = "incidenteRelacionado.id", target = "incidenteRelacionadoId")
    @Mapping(source = "tipoVinculo.id", target = "tipoVinculoId")
    IncidenteVinculoDto.Response toResponse(
            IncidenteVinculo entity);

    @Mapping(source = "incidenteId", target = "incidente.id")
    @Mapping(source = "incidenteRelacionadoId", target = "incidenteRelacionado.id")
    @Mapping(source = "tipoVinculoId", target = "tipoVinculo.id")
    IncidenteVinculo toEntity(
            IncidenteVinculoDto.Request request);

    @Mapping(source = "incidenteId", target = "incidente.id")
    @Mapping(source = "incidenteRelacionadoId", target = "incidenteRelacionado.id")
    @Mapping(source = "tipoVinculoId", target = "tipoVinculo.id")
    void updateEntityFromRequest(
            IncidenteVinculoDto.Request request,
            @MappingTarget IncidenteVinculo entity);

    // =====================================================
    // TIPO INCIDENTE
    // =====================================================

    @Mapping(source = "categoria.id", target = "categoriaId")
    TipoIncidenteDto.Response toResponse(
            TipoIncidente entity);

    @Mapping(source = "categoriaId", target = "categoria.id")
    TipoIncidente toEntity(
            TipoIncidenteDto.Request request);

    @Mapping(source = "categoriaId", target = "categoria.id")
    void updateEntityFromRequest(
            TipoIncidenteDto.Request request,
            @MappingTarget TipoIncidente entity);

    // =====================================================
    // HELPERS
    // =====================================================

    default Set<Integer> mapUsuarios(Set<Usuario> usuarios) {

        if (usuarios == null) {
            return null;
        }

        return usuarios.stream()
                .map(Usuario::getId)
                .collect(Collectors.toSet());
    }

    default Set<Integer> mapActivos(Set<Activo> activos) {

        if (activos == null) {
            return null;
        }

        return activos.stream()
                .map(Activo::getId)
                .collect(Collectors.toSet());
    }
}