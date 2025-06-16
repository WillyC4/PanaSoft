package com.tuempresa.panasoft.modelo;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.openxava.annotations.*;

import lombok.*;
 
@Entity @Getter @Setter
public class Cliente {
 
    @Id
    @Column(length=6)
    int codigo;
 
    @Column(length=50) @Required
    String nombre;
 
    @Column(length=10)
    @Pattern(regexp="09\\d{8}", message="Debe empezar con 09 y tener 10 dígitos numéricos")
    String telefono;
 
    @Column(length=50) @Stereotype("EMAIL")
    String email;
}