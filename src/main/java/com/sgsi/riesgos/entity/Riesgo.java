package com.sgsi.riesgos.entity;

import com.sgsi.activos.entity.Activo;
import com.sgsi.procesos.entity.Proceso;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Entity
@Table(name = "riesgo")
public class Riesgo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 30, unique = true)
    private String codigo;

    @Column(length = 150)
    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "impacto_negocio", columnDefinition = "TEXT")
    private String impactoNegocio;

    @Column(columnDefinition = "TEXT")
    private String consecuencia;

    @Column(length = 50)
    private String impacto;

    @Column(length = 50)
    private String frecuencia;

    @Column(name = "criticidad_inherente")
    private Integer criticidadInherente;

    @Column(name = "efectividad_controles")
    private Integer efectividadControles;

    @Column(name = "criticidad_residual")
    private Integer criticidadResidual;

    @Column(length = 100)
    private String categoria;

    @Column(length = 30)
    private String estado = "activo";

    @Column(name = "fecha_identificacion")
    private LocalDate fechaIdentificacion;

    @Column(name = "archivo_nombre", length = 255)
    private String archivoNombre;

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

    @Column(name = "updated_at", insertable = false, updatable = false)
    private LocalDateTime updatedAt;

    @Column(name = "created_by")
    private Integer createdBy;
}
