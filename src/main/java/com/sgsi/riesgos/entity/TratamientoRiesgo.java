package com.sgsi.riesgos.entity;

import com.sgsi.security.entity.Usuario;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "tratamiento_riesgo")
public class TratamientoRiesgo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "riesgo_id")
    private Riesgo riesgo;

    @Column(length = 50)
    private String estrategia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "responsable")
    private Usuario responsable;

    @Column(name = "fecha_plazo")
    private LocalDate fechaPlazo;

    @Column(length = 30)
    private String estado;
}
