package com.sgsi.riesgos.mapper;

import com.sgsi.riesgos.dto.*;
import com.sgsi.riesgos.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RiesgosMapper {
    AmenazaDto.Response toResponse(Amenaza entity);
    Amenaza toEntity(AmenazaDto.Request request);

    VulnerabilidadDto.Response toResponse(Vulnerabilidad entity);
    Vulnerabilidad toEntity(VulnerabilidadDto.Request request);

    NivelRiesgoDto.Response toResponse(NivelRiesgo entity);
    NivelRiesgo toEntity(NivelRiesgoDto.Request request);

    @Mapping(source = "amenaza.id", target = "amenazaId")
    @Mapping(source = "vulnerabilidad.id", target = "vulnerabilidadId")
    RiesgoDto.Response toResponse(Riesgo entity);

    @Mapping(source = "amenazaId", target = "amenaza.id")
    @Mapping(source = "vulnerabilidadId", target = "vulnerabilidad.id")
    Riesgo toEntity(RiesgoDto.Request request);

    @Mapping(source = "riesgo.id", target = "riesgoId")
    @Mapping(source = "nivelRiesgo.id", target = "nivelRiesgoId")
    @Mapping(source = "evaluadoPor.id", target = "evaluadoPorId")
    EvaluacionRiesgoDto.Response toResponse(EvaluacionRiesgo entity);

    @Mapping(source = "riesgoId", target = "riesgo.id")
    @Mapping(source = "nivelRiesgoId", target = "nivelRiesgo.id")
    @Mapping(source = "evaluadoPorId", target = "evaluadoPor.id")
    EvaluacionRiesgo toEntity(EvaluacionRiesgoDto.Request request);

    @Mapping(source = "riesgo.id", target = "riesgoId")
    @Mapping(source = "responsable.id", target = "responsableId")
    TratamientoRiesgoDto.Response toResponse(TratamientoRiesgo entity);

    @Mapping(source = "riesgoId", target = "riesgo.id")
    @Mapping(source = "responsableId", target = "responsable.id")
    TratamientoRiesgo toEntity(TratamientoRiesgoDto.Request request);
    void updateEntityFromRequest(AmenazaDto.Request request, @MappingTarget Amenaza entity);

    void updateEntityFromRequest(VulnerabilidadDto.Request request, @MappingTarget Vulnerabilidad entity);

    void updateEntityFromRequest(NivelRiesgoDto.Request request, @MappingTarget NivelRiesgo entity);

    @Mapping(source = "amenazaId", target = "amenaza.id")
    @Mapping(source = "vulnerabilidadId", target = "vulnerabilidad.id")
    void updateEntityFromRequest(RiesgoDto.Request request, @MappingTarget Riesgo entity);

    @Mapping(source = "riesgoId", target = "riesgo.id")
    @Mapping(source = "nivelRiesgoId", target = "nivelRiesgo.id")
    @Mapping(source = "evaluadoPorId", target = "evaluadoPor.id")
    void updateEntityFromRequest(EvaluacionRiesgoDto.Request request, @MappingTarget EvaluacionRiesgo entity);

    @Mapping(source = "riesgoId", target = "riesgo.id")
    @Mapping(source = "responsableId", target = "responsable.id")
    void updateEntityFromRequest(TratamientoRiesgoDto.Request request, @MappingTarget TratamientoRiesgo entity);
}
