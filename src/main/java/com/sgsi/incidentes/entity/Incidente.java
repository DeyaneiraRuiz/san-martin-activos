package com.sgsi.incidentes.entity;

import com.sgsi.activos.entity.Activo;
import com.sgsi.catalogos.entity.CatEstadoIncidente;
import com.sgsi.catalogos.entity.CatNivel;
import com.sgsi.security.entity.Usuario;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Data
@Entity
@Table(name = "incidente")
public class Incidente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // ============================================
    // DATOS GENERALES
    // ============================================

    @Column(length = 30, unique = true)
    private String codigo;

    @Column(nullable = false, length = 255)
    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id")
    private CategoriaIncidente categoria;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_incidente_id")
    private TipoIncidente tipoIncidente;

    /**
     * gravedadReportada
     * (bajo, medio, alto, crítico)
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gravedad_reportada_id")
    private CatNivel gravedadReportada;

    @Column(name = "fecha_deteccion")
    private LocalDateTime fechaDeteccion;

    @Column(name = "fecha_reporte")
    private LocalDateTime fechaReporte;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reportador_id")
    private Usuario reportador;

    // ============================================
    // SEGUIMIENTO
    // ============================================

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "asignado_a_id")
    private Usuario asignadoA;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "asignado_por_id")
    private Usuario asignadoPor;

    @Column(name = "fecha_asignacion")
    private LocalDateTime fechaAsignacion;

    /**
     * Evaluación técnica
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "impacto_real_id")
    private CatNivel impactoReal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prioridad_id")
    private CatNivel prioridad;

    @Column(name = "causa_raiz", columnDefinition = "TEXT")
    private String causaRaiz;

    @Column(name = "solucion_propuesta", columnDefinition = "TEXT")
    private String solucionPropuesta;

    /**
     * Plan de acción
     */
    @Column(name = "plan_accion", columnDefinition = "TEXT")
    private String planAccion;

    @Column(name = "fecha_compromiso")
    private LocalDateTime fechaCompromiso;

    /**
     * Resolución
     */
    @Column(name = "descripcion_solucion", columnDefinition = "TEXT")
    private String descripcionSolucion;

    @Column(name = "fecha_resolucion")
    private LocalDateTime fechaResolucion;

    /**
     * Cierre
     */
    @Column(name = "comentario_cierre", columnDefinition = "TEXT")
    private String comentarioCierre;

    @Column(name = "fecha_cierre")
    private LocalDateTime fechaCierre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cerrado_por_id")
    private Usuario cerradoPor;

    // ============================================
    // OBSERVADORES
    // ============================================

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "incidente_observador",
            joinColumns = @JoinColumn(name = "incidente_id"),
            inverseJoinColumns = @JoinColumn(name = "usuario_id")
    )
    private Set<Usuario> observadores = new HashSet<>();

    // ============================================
    // ACTIVOS AFECTADOS
    // ============================================

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "incidente_activo",
            joinColumns = @JoinColumn(name = "incidente_id"),
            inverseJoinColumns = @JoinColumn(name = "activo_id")
    )
    private Set<Activo> activosAfectados = new HashSet<>();

    // ============================================
    // ESTADO
    // ============================================

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estado_id")
    private CatEstadoIncidente estado;

    // ============================================
    // REAPERTURA
    // ============================================

    private Boolean reabierto = false;

    @Column(name = "motivo_reapertura", columnDefinition = "TEXT")
    private String motivoReapertura;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reabierto_por_id")
    private Usuario reabiertoPor;

    @Column(name = "fecha_reapertura")
    private LocalDateTime fechaReapertura;

    // ============================================
    // AUDITORÍA
    // ============================================

    @Column(name = "ultima_modificacion")
    private LocalDateTime ultimaModificacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ultimo_modificado_por_id")
    private Usuario ultimoModificadoPor;

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", insertable = false, updatable = false)
    private LocalDateTime updatedAt;

    @Column(name = "created_by")
    private Integer createdBy;

    @Column(name = "updated_by")
    private Integer updatedBy;

    public Optional<Incidente> map(Object object) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'map'");
    }
}