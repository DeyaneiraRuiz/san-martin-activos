package com.sgsi.riesgos.entity;

import com.sgsi.activos.entity.Activo;
import com.sgsi.procesos.entity.Proceso;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Entity
@Table(name = "riesgo")
public class Riesgo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "impacto_negocio", columnDefinition = "TEXT")
    private String impactoNegocio;

    @Column(columnDefinition = "TEXT")
    private String consecuencia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "amenaza_id")
    private Amenaza amenaza;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vulnerabilidad_id")
    private Vulnerabilidad vulnerabilidad;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "riesgo_activo",
        joinColumns = @JoinColumn(name = "riesgo_id"),
        inverseJoinColumns = @JoinColumn(name = "activo_id")
    )
    private Set<Activo> activos;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "riesgo_proceso",
        joinColumns = @JoinColumn(name = "riesgo_id"),
        inverseJoinColumns = @JoinColumn(name = "proceso_id")
    )
    private Set<Proceso> procesos;

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "created_by")
    private Integer createdBy;
}
