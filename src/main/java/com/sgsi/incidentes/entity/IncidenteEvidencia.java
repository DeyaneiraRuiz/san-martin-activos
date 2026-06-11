package com.sgsi.incidentes.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "incidente_evidencia")
public class IncidenteEvidencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // ============================================
    // RELACIÓN
    // ============================================

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "incidente_id", nullable = false)
    private Incidente incidente;

    // ============================================
    // ARCHIVO
    // ============================================

    @Column(name = "nombre_archivo", nullable = false)
    private String nombreArchivo;

    @Column(name = "archivo_url", nullable = false)
    private String archivoUrl;

    @Column(name = "tipo_archivo")
    private String tipoArchivo;

    @Column(name = "tamaño")
    private Integer tamaño;

    // ============================================
    // AUDITORÍA
    // ============================================

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", insertable = false, updatable = false)
    private LocalDateTime updatedAt;

    @Column(name = "created_by")
    private Integer createdBy;

    @Column(name = "updated_by")
    private Integer updatedBy;
}