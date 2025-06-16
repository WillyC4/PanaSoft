package com.tuempresa.panasoft.modelo;

import javax.persistence.*;

import lombok.*;

@Entity @Getter @Setter
public class TipoMasa {

    @Id @Column(length=20)
    String nombre;
}