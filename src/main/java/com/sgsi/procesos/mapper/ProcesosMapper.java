package com.sgsi.procesos.mapper;

import com.sgsi.procesos.dto.*;
import com.sgsi.procesos.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProcesosMapper {
    @Mapping(source = "periodicidad.id", target = "periodicidadId")
    @Mapping(source = "nivelAutomatizacion.id", target = "nivelAutomatizacionId")
    ProcesoDto.Response toResponse(Proceso entity);
    
    @Mapping(source = "periodicidadId", target = "periodicidad.id")
    @Mapping(source = "nivelAutomatizacionId", target = "nivelAutomatizacion.id")
    Proceso toEntity(ProcesoDto.Request request);

    ControlDto.Response toResponse(Control entity);
    Control toEntity(ControlDto.Request request);

    DocumentoDto.Response toResponse(Documento entity);
    Documento toEntity(DocumentoDto.Request request);

    @Mapping(source = "activo.id", target = "activoId")
    @Mapping(source = "proceso.id", target = "procesoId")
    ActivoProcesoDto.Response toResponse(ActivoProceso entity);
    @Mapping(source = "activoId", target = "activo.id")
    @Mapping(source = "procesoId", target = "proceso.id")
    ActivoProceso toEntity(ActivoProcesoDto.Request request);

    @Mapping(source = "proceso.id", target = "procesoId")
    @Mapping(source = "control.id", target = "controlId")
    ProcesoControlDto.Response toResponse(ProcesoControl entity);
    @Mapping(source = "procesoId", target = "proceso.id")
    @Mapping(source = "controlId", target = "control.id")
    ProcesoControl toEntity(ProcesoControlDto.Request request);

    @Mapping(source = "proceso.id", target = "procesoId")
    @Mapping(source = "documento.id", target = "documentoId")
    ProcesoDocumentoDto.Response toResponse(ProcesoDocumento entity);
    @Mapping(source = "procesoId", target = "proceso.id")
    @Mapping(source = "documentoId", target = "documento.id")
    ProcesoDocumento toEntity(ProcesoDocumentoDto.Request request);
}
