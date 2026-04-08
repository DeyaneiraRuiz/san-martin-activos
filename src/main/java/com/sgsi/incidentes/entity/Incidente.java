package com.sgsi.incidentes.entity;

import com.sgsi.activos.entity.Activo;
import com.sgsi.catalogos.entity.CatEstadoIncidente;
import com.sgsi.catalogos.entity.CatNivel;
import com.sgsi.catalogos.entity.CatTipoTicket;
import com.sgsi.riesgos.entity.Riesgo;
import com.sgsi.security.entity.Usuario;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Entity
@Table(name = "incidente")
public class Incidente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 30, unique = true)
    private String codigo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_ticket_id")
    private CatTipoTicket tipoTicket;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id")
    private CategoriaIncidente categoria;

    @Column(length = 255)
    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "impacto_id")
    private CatNivel impacto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "urgencia_id")
    private CatNivel urgencia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prioridad_id")
    private CatNivel prioridad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estado_id")
    private CatEstadoIncidente estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sla_id")
    private Sla sla;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reportado_por")
    private Usuario reportadoPor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "asignado_a")
    private Usuario asignadoA;

    @Column(name = "fecha_reporte")
    private LocalDateTime fechaReporte;

    @Column(name = "fecha_cierre")
    private LocalDateTime fechaCierre;

    @Column(name = "sla_vencimiento")
    private LocalDateTime slaVencimiento;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "incidente_activo",
        joinColumns = @JoinColumn(name = "incidente_id"),
        inverseJoinColumns = @JoinColumn(name = "activo_id")
    )
    private Set<Activo> activos;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "incidente_observador",
        joinColumns = @JoinColumn(name = "incidente_id"),
        inverseJoinColumns = @JoinColumn(name = "usuario_id")
    )
    private Set<Usuario> observadores;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "incidente_riesgo",
        joinColumns = @JoinColumn(name = "incidente_id"),
        inverseJoinColumns = @JoinColumn(name = "riesgo_id")
    )
    private Set<Riesgo> riesgosAsociados;

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", insertable = false, updatable = false)
    private LocalDateTime updatedAt;

    @Column(name = "created_by")
    private Integer createdBy;

    @Column(name = "updated_by")
    private Integer updatedBy;
}
