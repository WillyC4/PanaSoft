package com.tuempresa.panasoft.calculadores;

import org.openxava.calculators.*;

public class CalculadorStockInicial implements ICalculator {
    @Override
    public Object calculate() throws Exception {
        return 0; // Cada pan inicia con 0 unidades en stock
    }
}