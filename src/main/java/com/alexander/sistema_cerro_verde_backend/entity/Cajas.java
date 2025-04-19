package com.hoteles.caja.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "cajas")
public class Cajas {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_caja")
    private Integer id;

    private Double montoApertura;
    private Double montoCierre;
    private Date fechaApertura;
    private Date fechaCierre;
    private String estadoCaja;
    private Integer estado = 1;

}
