package com.sgsi.organizacion.mapper;

import com.sgsi.organizacion.dto.AreaDto;
import com.sgsi.organizacion.dto.GrupoDto;
import com.sgsi.organizacion.dto.PropietarioDto;
import com.sgsi.organizacion.entity.Area;
import com.sgsi.organizacion.entity.Grupo;
import com.sgsi.organizacion.entity.Propietario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface OrganizacionMapper {
    AreaDto.Response toResponse(Area entity);
    Area toEntity(AreaDto.Request request);

    GrupoDto.Response toResponse(Grupo entity);
    Grupo toEntity(GrupoDto.Request request);

    @Mapping(source = "area.id", target = "areaId")
    PropietarioDto.Response toResponse(Propietario entity);

    @Mapping(source = "areaId", target = "area.id")
    Propietario toEntity(PropietarioDto.Request request);
}
