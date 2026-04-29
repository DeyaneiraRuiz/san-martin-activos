package com.sgsi.security.mapper;

import com.sgsi.security.dto.RolDto;
import com.sgsi.security.dto.UsuarioDto;
import com.sgsi.security.entity.Rol;
import com.sgsi.security.entity.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface SecurityMapper {
    RolDto.Response toResponse(Rol entity);
    Rol toEntity(RolDto.Request request);

    @Mapping(source = "rol.id", target = "rolId")
    UsuarioDto.Response toResponse(Usuario entity);

    @Mapping(source = "rolId", target = "rol.id")
    Usuario toEntity(UsuarioDto.Request request);
}
