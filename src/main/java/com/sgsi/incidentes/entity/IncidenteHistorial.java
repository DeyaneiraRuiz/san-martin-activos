package com.sgsi.incidentes.entity;

import com.sgsi.catalogos.entity.CatEstadoIncidente;
import com.sgsi.security.entity.Usuario;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "incidente_historial")
public class IncidenteHistorial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "incidente_id")
    private Incidente incidente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estado_id")
    private CatEstadoIncidente estado;

    @Column(columnDefinition = "TEXT")
    private String comentario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "changed_by")
    private Usuario changedBy;

    private LocalDateTime fecha;
}
