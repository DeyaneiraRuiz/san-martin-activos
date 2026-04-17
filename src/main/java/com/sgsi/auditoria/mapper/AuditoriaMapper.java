package com.sgsi.auditoria.mapper;

import com.sgsi.auditoria.dto.AuditoriaDto;
import com.sgsi.auditoria.entity.Auditoria;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AuditoriaMapper {

    @Mapping(source = "usuario.id", target = "usuarioId")
    AuditoriaDto.Response toResponse(Auditoria entity);

    @Mapping(source = "usuarioId", target = "usuario.id")
    Auditoria toEntity(AuditoriaDto.Request request);
    @Mapping(source = "usuarioId", target = "usuario.id")
    void updateEntityFromRequest(AuditoriaDto.Request request, @MappingTarget Auditoria entity);
}
