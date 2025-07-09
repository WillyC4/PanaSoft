package com.tuempresa.panasoft.modelo;
import java.math.*;
import java.time.*;
import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.calculators.*;
import org.openxava.jpa.*;

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
    @DefaultValueCalculator(CurrentLocalDateCalculator.class) // Fecha automática
    LocalDate fecha;

    @ManyToOne(fetch=FetchType.LAZY, optional=false) // Cliente es obligatorio
    Cliente cliente;

    @ElementCollection
    @ListProperties("pan.nombre, cantidad, precioUnitario, importe") 
    Collection<DetallePedido> detalles;

    @TextArea
    String observaciones;
    
    @PostPersist
    private void actualizarStock() throws Exception {

        /*if (detalles == null || detalles.isEmpty()) {
            throw new Exception("El pedido debe tener al menos un producto.");
        }*/

        EntityManager em = XPersistence.getManager();

        for (DetallePedido detalle : detalles) {
            if (detalle.getPan() != null && detalle.getCantidad() > detalle.getPan().getStock()) {
                throw new Exception("No hay suficiente stock para '" + detalle.getPan().getNombre() +
                    "'. Stock disponible: " + detalle.getPan().getStock());
            }

            detalle.getPan().actualizarStock(detalle.getCantidad());

            VentaPan venta = new VentaPan();
            venta.setPan(detalle.getPan());
            venta.setFecha(this.fecha != null ? this.fecha : LocalDate.now());
            venta.setCantidadVendida(detalle.getCantidad());
            em.persist(venta);
        }
    }

    
    @ReadOnly
    @Money
    public BigDecimal getTotalCalculado() {
        BigDecimal total = BigDecimal.ZERO;
        if (detalles != null) {
            for (DetallePedido detalle : detalles) {
                if (detalle.getImporte() != null) {
                    total = total.add(detalle.getImporte());
                }
            }
        }
        return total;
    }


    /*@ReadOnly
    @Money
    @Calculation("sum(detalles.importe)")
    BigDecimal total; */ 
}