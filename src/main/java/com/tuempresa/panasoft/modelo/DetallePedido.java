package com.tuempresa.panasoft.modelo;

import java.math.*;

import javax.persistence.*;

import org.openxava.annotations.*;

import com.tuempresa.panasoft.calculadores.*;

import lombok.*;

@Embeddable @Getter @Setter
public class DetallePedido {

    int cantidad;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @DescriptionsList
    Pan pan;

    @DefaultValueCalculator(
        value = CalculadorPrecioPan.class, 
        properties = @PropertyValue(name="codigoPan", from="pan.codigo")
    )
    @Stereotype("DINERO")
    BigDecimal precioUnitario;
    
    @PrePersist @PreUpdate
    private void validarStock() throws Exception {
        if (pan != null && cantidad > pan.getStock()) {
            throw new Exception("No hay suficiente stock para '" + pan.getNombre() + "'. Stock disponible: " + pan.getStock());
        }
    }

    @Stereotype("DINERO")
    @Depends("precioUnitario, cantidad")
    public BigDecimal getImporte() { 
        if (precioUnitario == null) return BigDecimal.ZERO;
        return new BigDecimal(cantidad).multiply(precioUnitario);
    }
    
}