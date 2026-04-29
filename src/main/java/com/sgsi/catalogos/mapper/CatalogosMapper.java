package com.sgsi.catalogos.mapper;

import com.sgsi.catalogos.dto.*;
import com.sgsi.catalogos.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface CatalogosMapper {
    CatEstadoActivoDto.Response toResponse(CatEstadoActivo entity);
    CatEstadoActivo toEntity(CatEstadoActivoDto.Request request);

    CatTipoActivoDto.Response toResponse(CatTipoActivo entity);
    CatTipoActivo toEntity(CatTipoActivoDto.Request request);

    CatTipoNivelDto.Response toResponse(CatTipoNivel entity);
    CatTipoNivel toEntity(CatTipoNivelDto.Request request);

    @Mapping(source = "tipoNivel.id", target = "tipoNivelId")
    CatNivelDto.Response toResponse(CatNivel entity);
    @Mapping(source = "tipoNivelId", target = "tipoNivel.id")
    CatNivel toEntity(CatNivelDto.Request request);

    CatEstadoIncidenteDto.Response toResponse(CatEstadoIncidente entity);
    CatEstadoIncidente toEntity(CatEstadoIncidenteDto.Request request);

    CatTipoTicketDto.Response toResponse(CatTipoTicket entity);
    CatTipoTicket toEntity(CatTipoTicketDto.Request request);

    CatTipoVinculoDto.Response toResponse(CatTipoVinculo entity);
    CatTipoVinculo toEntity(CatTipoVinculoDto.Request request);

    CatPeriodicidadDto.Response toResponse(CatPeriodicidad entity);
    CatPeriodicidad toEntity(CatPeriodicidadDto.Request request);

    CatNivelAutomatizacionDto.Response toResponse(CatNivelAutomatizacion entity);
    CatNivelAutomatizacion toEntity(CatNivelAutomatizacionDto.Request request);
}
