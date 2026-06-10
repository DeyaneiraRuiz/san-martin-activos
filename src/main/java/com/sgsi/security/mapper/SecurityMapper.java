package com.sgsi.security.mapper;

import com.sgsi.security.dto.RolDto;
import com.sgsi.security.entity.Rol;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface SecurityMapper {
    RolDto.Response toResponse(Rol entity);
    Rol toEntity(RolDto.Request request);
}