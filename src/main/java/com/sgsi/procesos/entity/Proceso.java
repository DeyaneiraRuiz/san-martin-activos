package com.sgsi.procesos.entity;

import com.sgsi.catalogos.entity.CatNivelAutomatizacion;
import com.sgsi.catalogos.entity.CatPeriodicidad;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "proceso")
public class Proceso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 150)
    private String nombre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "periodicidad_id")
    private CatPeriodicidad periodicidad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nivel_automatizacion_id")
    private CatNivelAutomatizacion nivelAutomatizacion;

    @Column(name = "es_critico")
    private Boolean esCritico;

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", insertable = false, updatable = false)
    private LocalDateTime updatedAt;

    @Column(name = "created_by")
    private Integer createdBy;

    @Column(name = "updated_by")
    private Integer updatedBy;
}
