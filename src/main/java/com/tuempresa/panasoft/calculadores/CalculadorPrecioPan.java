package com.tuempresa.panasoft.calculadores;

import static org.openxava.jpa.XPersistence.getManager; // Importamos XPersistence

import org.openxava.calculators.*;

import com.tuempresa.panasoft.modelo.*;

import lombok.*;

public class CalculadorPrecioPan implements ICalculator {

    @Getter @Setter
    int codigoPan;

    @Override
    public Object calculate() throws Exception {
        Pan pan = getManager() // Usamos XPersistence
            .find(Pan.class, codigoPan); // Buscamos el pan por código
        return pan != null ? pan.getPrecio() : null; // Retornamos su precio
    }
} 