package com.sgsi.activos.mapper;

import com.sgsi.activos.dto.ActivoDto;
import com.sgsi.activos.entity.Activo;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface ActivoMapper {

    @Mapping(source = "area.id", target = "areaId")
    @Mapping(source = "area.nombre", target = "areaNombre")
    @Mapping(source = "tipoActivo.id", target = "tipoActivoId")
    @Mapping(source = "tipoActivo.nombre", target = "tipoActivoNombre")
    @Mapping(source = "estado.id", target = "estadoId")
    @Mapping(source = "estado.nombre", target = "estadoNombre")
    @Mapping(source = "propietario.id", target = "propietarioId")
    ActivoDto.Response toResponse(Activo entity);

    @Mapping(source = "areaId", target = "area.id")
    @Mapping(source = "tipoActivoId", target = "tipoActivo.id")
    @Mapping(source = "estadoId", target = "estado.id")
    @Mapping(source = "propietarioId", target = "propietario.id")
    Activo toEntity(ActivoDto.Request request);

    @Mapping(source = "areaId", target = "area.id")
    @Mapping(source = "tipoActivoId", target = "tipoActivo.id")
    @Mapping(source = "estadoId", target = "estado.id")
    @Mapping(source = "propietarioId", target = "propietario.id")
    void updateEntityFromRequest(ActivoDto.Request request, @MappingTarget Activo entity);

    @AfterMapping
    default void nullifyNullPropietario(ActivoDto.Request request, @MappingTarget Activo activo) {
        if (request.propietarioId() == null) {
            activo.setPropietario(null);
        }
    }
}
