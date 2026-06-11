package com.sgsi.archivos.controller;

import com.sgsi.archivos.dto.ArchivoDto;
import com.sgsi.archivos.entity.Archivo;
import com.sgsi.archivos.service.ArchivoService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/archivos")
@RequiredArgsConstructor
public class ArchivoController {

    private final ArchivoService service;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ArchivoDto.Response> upload(
            @RequestPart("archivo") MultipartFile archivo
    ) {
        if (archivo.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        try {
            ArchivoDto.Response response = service.guardar(archivo);
            return ResponseEntity.ok(response);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}/descargar")
    public ResponseEntity<Resource> descargar(@PathVariable Long id) {
        return service.findById(id)
                .map(archivo -> {
                    ByteArrayResource resource = new ByteArrayResource(archivo.getContenido());
                    String contentType = archivo.getTipoMime() != null
                            ? archivo.getTipoMime()
                            : MediaType.APPLICATION_OCTET_STREAM_VALUE;
                    return ResponseEntity.ok()
                            .contentType(MediaType.parseMediaType(contentType))
                            .header(HttpHeaders.CONTENT_DISPOSITION,
                                    "attachment; filename=\"" + archivo.getNombre() + "\"")
                            .contentLength(archivo.getTamanio())
                            .body((Resource) resource);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
