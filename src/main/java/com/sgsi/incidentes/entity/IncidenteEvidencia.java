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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "incidente_id")
    private Incidente incidente;

    @Column(name = "archivo_url", columnDefinition = "TEXT")
    private String archivoUrl;

    @Column(name = "nombre_archivo", length = 150)
    private String nombreArchivo;

    @Column(name = "tipo_archivo", length = 50)
    private String tipoArchivo;

    private Integer tamaño;

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "created_by")
    private Integer createdBy;
}
