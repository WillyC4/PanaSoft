package com.tuempresa.panasoft.modelo;

import java.math.*;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.jpa.*;

import com.tuempresa.panasoft.calculadores.*;

import lombok.*;

@Entity @Getter @Setter
public class Pan {

    @Id @Column(length=9)
    int codigo;

    @Column(length=50) @Required
    String nombre;

    @TextArea
    String descripcion;

    @Money 
    BigDecimal precio;

    @ManyToOne @DescriptionsList
    TamanioPan tamaño; 

    @ManyToOne @DescriptionsList
    TipoMasa tipoMasa; 
   
    @DefaultValueCalculator(value=CalculadorStockInicial.class)
    int stock;

    @Column(length=50) @Files
    String imagen;
    
    public void actualizarStock(int cantidadVendida) {
        this.stock -= cantidadVendida;
        XPersistence.getManager().merge(this); // Guarda el cambio en la BD
    }
}