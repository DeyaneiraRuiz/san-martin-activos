package com.sgsi.archivos.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "archivo")
public class Archivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String nombre;

    @Column(name = "tipo_mime", length = 100)
    private String tipoMime;

    @Column(name = "tamanio")
    private Long tamanio;

    @Lob
    @Column(name = "contenido", columnDefinition = "bytea")
    private byte[] contenido;

    @Column(name = "creado_en")
    private LocalDateTime creadoEn;

    @PrePersist
    void prePersist() {
        creadoEn = LocalDateTime.now();
    }
}
