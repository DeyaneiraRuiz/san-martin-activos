package com.sgsi.activos.entity;

import com.sgsi.catalogos.entity.CatEstadoActivo;
import com.sgsi.catalogos.entity.CatTipoActivo;
import com.sgsi.organizacion.entity.Area;
import com.sgsi.organizacion.entity.Propietario;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "activo")
public class Activo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 30, unique = true)
    private String codigo;

    @Column(length = 255)
    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_activo_id")
    private CatTipoActivo tipoActivo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "area_id")
    private Area area;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "propietario_id")
    private Propietario propietario;

    private Integer confidencialidad;
    private Integer integridad;
    private Integer disponibilidad;

    @Column(name = "valor_activo")
    private Integer valorActivo;

    @Column(length = 150)
    private String ubicacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estado_id")
    private CatEstadoActivo estado;

    @Column(name = "fecha_adquisicion")
    private LocalDate fechaAdquisicion;

    @Column(name = "fecha_baja")
    private LocalDate fechaBaja;

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", insertable = false, updatable = false)
    private LocalDateTime updatedAt;

    @Column(name = "created_by")
    private Integer createdBy;

    @Column(name = "updated_by")
    private Integer updatedBy;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;
}
