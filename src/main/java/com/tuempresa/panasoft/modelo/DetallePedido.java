package com.tuempresa.panasoft.modelo;

import java.math.*;

import javax.persistence.*;

import org.openxava.annotations.*;

import lombok.*;

@Embeddable @Getter @Setter
public class DetallePedido {

    int cantidad;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @DescriptionsList
    Pan pan;

    @Stereotype("DINERO")
    BigDecimal precioUnitario;
}