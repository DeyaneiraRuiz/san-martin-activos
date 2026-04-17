package com.sgsi.incidentes.mapper;

import com.sgsi.incidentes.dto.*;
import com.sgsi.incidentes.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface IncidentesMapper {
    SlaDto.Response toResponse(Sla entity);

    Sla toEntity(SlaDto.Request request);

    @Mapping(source = "responsable.id", target = "responsableId")
    @Mapping(source = "grupo.id", target = "grupoId")
    CategoriaIncidenteDto.Response toResponse(CategoriaIncidente entity);

    @Mapping(source = "responsableId", target = "responsable.id")
    @Mapping(source = "grupoId", target = "grupo.id")
    CategoriaIncidente toEntity(CategoriaIncidenteDto.Request request);

    @Mapping(source = "categoria.id", target = "categoriaId")
    @Mapping(source = "estado.id", target = "estadoId")
    @Mapping(source = "tipoTicket.id", target = "tipoTicketId")
    @Mapping(source = "reportadoPor.id", target = "reportadoPorId")
    @Mapping(source = "asignadoA.id", target = "asignadoAId")
    IncidenteDto.Response toResponse(Incidente entity);

    @Mapping(source = "categoriaId", target = "categoria.id")
    @Mapping(source = "estadoId", target = "estado.id")
    @Mapping(source = "tipoTicketId", target = "tipoTicket.id")
    @Mapping(source = "reportadoPorId", target = "reportadoPor.id")
    @Mapping(source = "asignadoAId", target = "asignadoA.id")
    Incidente toEntity(IncidenteDto.Request request);

    @Mapping(source = "incidente.id", target = "incidenteId")
    @Mapping(source = "createdBy", target = "subidoPorId")
    IncidenteEvidenciaDto.Response toResponse(IncidenteEvidencia entity);

    @Mapping(source = "incidenteId", target = "incidente.id")
    @Mapping(source = "subidoPorId", target = "createdBy")
    IncidenteEvidencia toEntity(IncidenteEvidenciaDto.Request request);

    @Mapping(source = "incidente.id", target = "incidenteId")
    @Mapping(source = "changedBy.id", target = "modificadoPorId")
    IncidenteHistorialDto.Response toResponse(IncidenteHistorial entity);

    @Mapping(source = "incidenteId", target = "incidente.id")
    @Mapping(source = "modificadoPorId", target = "changedBy.id")
    IncidenteHistorial toEntity(IncidenteHistorialDto.Request request);

    @Mapping(source = "incidente.id", target = "incidenteId")
    @Mapping(source = "incidenteRelacionado.id", target = "incidenteRelacionadoId")
    @Mapping(source = "tipoVinculo.id", target = "tipoVinculoId")
    IncidenteVinculoDto.Response toResponse(IncidenteVinculo entity);

    @Mapping(source = "incidenteId", target = "incidente.id")
    @Mapping(source = "incidenteRelacionadoId", target = "incidenteRelacionado.id")
    @Mapping(source = "tipoVinculoId", target = "tipoVinculo.id")
    IncidenteVinculo toEntity(IncidenteVinculoDto.Request request);
    void updateEntityFromRequest(SlaDto.Request request, @MappingTarget Sla entity);

    @Mapping(source = "responsableId", target = "responsable.id")
    @Mapping(source = "grupoId", target = "grupo.id")
    void updateEntityFromRequest(CategoriaIncidenteDto.Request request, @MappingTarget CategoriaIncidente entity);

    @Mapping(source = "categoriaId", target = "categoria.id")
    @Mapping(source = "estadoId", target = "estado.id")
    @Mapping(source = "tipoTicketId", target = "tipoTicket.id")
    @Mapping(source = "reportadoPorId", target = "reportadoPor.id")
    @Mapping(source = "asignadoAId", target = "asignadoA.id")
    void updateEntityFromRequest(IncidenteDto.Request request, @MappingTarget Incidente entity);

    @Mapping(source = "incidenteId", target = "incidente.id")
    @Mapping(source = "subidoPorId", target = "createdBy")
    void updateEntityFromRequest(IncidenteEvidenciaDto.Request request, @MappingTarget IncidenteEvidencia entity);

    @Mapping(source = "incidenteId", target = "incidente.id")
    @Mapping(source = "modificadoPorId", target = "changedBy.id")
    void updateEntityFromRequest(IncidenteHistorialDto.Request request, @MappingTarget IncidenteHistorial entity);

    @Mapping(source = "incidenteId", target = "incidente.id")
    @Mapping(source = "incidenteRelacionadoId", target = "incidenteRelacionado.id")
    @Mapping(source = "tipoVinculoId", target = "tipoVinculo.id")
    void updateEntityFromRequest(IncidenteVinculoDto.Request request, @MappingTarget IncidenteVinculo entity);
}
