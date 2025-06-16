package com.tuempresa.panasoft.modelo;

import java.math.*;
import java.time.*;
import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;

import lombok.*;

@Entity @Getter @Setter
@View(members=  
    "fecha, cliente; " +  
    "detalles;" +  
    "observaciones"
)
public class Pedido {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Required
    LocalDate fecha;

    @ManyToOne(fetch=FetchType.LAZY, optional=false)
    Cliente cliente;

    @ElementCollection
    @ListProperties("pan.nombre, cantidad, precioUnitario")
    Collection<DetallePedido> detalles;

    @TextArea
    String observaciones;

    @ReadOnly
    @Money
    BigDecimal total;
}