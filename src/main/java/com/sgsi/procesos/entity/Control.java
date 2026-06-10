package com.sgsi.procesos.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "control")
public class Control {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 30)
    private String codigo;

    @Column(name = "codigo_iso", length = 30)
    private String codigoIso;

    @Column(length = 150)
    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "tipo_control", length = 50)
    private String tipoControl;

    @Column(name = "tipo_ejecucion", length = 50)
    private String tipoEjecucion;

    @Column(name = "se_ejecuta_con_frecuencia")
    private Boolean seEjecutaConFrecuencia;

    @Column(name = "esta_documentado", length = 50)
    private String estaDocumentado;

    @Column(name = "tiene_evidencia")
    private Boolean tieneEvidencia;

    @Column(name = "tiene_responsables")
    private Boolean tieneResponsablesAsociados;

    @Column(name = "calificacion_disenio")
    private Integer calificacionDisenio;

    @Column(name = "se_han_presentado_eventos")
    private Boolean seHanPresentadoEventos;

    @Column(name = "disenio_es_efectivo")
    private Boolean disenioEsEfectivo;

    @Column(name = "evidencia_es_efectiva")
    private Boolean evidenciaEsEfectiva;

    @Column(name = "calificacion_ejecucion")
    private Integer calificacionEjecucion;

    private Integer solidez;

    @Column(name = "archivo_nombre", length = 255)
    private String archivoNombre;

    @Column(length = 30)
    private String estado = "activo";

    @Column(name = "created_at", insertable = false, updatable = false)
    private java.time.LocalDateTime createdAt;

    @Column(name = "updated_at", insertable = false, updatable = false)
    private java.time.LocalDateTime updatedAt;

    @Column(name = "created_by", length = 100)
    private String creadoPor;

    @Column(name = "updated_by", length = 100)
    private String modificadoPor;
}
