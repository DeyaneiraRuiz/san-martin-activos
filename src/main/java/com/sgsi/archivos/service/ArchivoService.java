package com.sgsi.archivos.service;

import com.sgsi.archivos.dto.ArchivoDto;
import com.sgsi.archivos.entity.Archivo;
import com.sgsi.archivos.repository.ArchivoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArchivoService {

    private final ArchivoRepository repository;

    @Transactional
    public ArchivoDto.Response guardar(MultipartFile file) throws IOException {
        Archivo archivo = new Archivo();
        archivo.setNombre(file.getOriginalFilename());
        archivo.setTipoMime(file.getContentType());
        archivo.setTamanio(file.getSize());
        archivo.setContenido(file.getBytes());

        Archivo saved = repository.save(archivo);
        return toResponse(saved);
    }

    @Transactional(readOnly = true)
    public Optional<Archivo> findById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    private ArchivoDto.Response toResponse(Archivo a) {
        return new ArchivoDto.Response(a.getId(), a.getNombre(), a.getTipoMime(), a.getTamanio(), a.getCreadoEn());
    }
}
