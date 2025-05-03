package com.alexander.sistema_cerro_verde_backend.entity.ventas;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="ventas_x_reservas")
public class VentasXReservas {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idVentaReserva;

    //Relación de Muchos a Uno con Ventas

    //relación de Muchos a Uno con Reservas

    public Integer getIdVentaReserva() {
        return idVentaReserva;
    }

    public void setIdVentaReserva(Integer idVentaReserva) {
        this.idVentaReserva = idVentaReserva;
    }
}
