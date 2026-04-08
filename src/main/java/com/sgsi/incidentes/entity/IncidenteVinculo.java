package com.sgsi.incidentes.entity;

import com.sgsi.catalogos.entity.CatTipoVinculo;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "incidente_vinculo")
public class IncidenteVinculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "incidente_id")
    private Incidente incidente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "incidente_relacionado_id")
    private Incidente incidenteRelacionado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_vinculo_id")
    private CatTipoVinculo tipoVinculo;
}
