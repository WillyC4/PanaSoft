package com.tuempresa.panasoft.modelo;

import java.time.*;

import javax.persistence.*;

import org.openxava.annotations.*;

import lombok.*;

@Entity @Getter @Setter
public class VentaPan {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @ManyToOne @DescriptionsList
    Pan pan;

    LocalDate fecha;

    int cantidadVendida;
}