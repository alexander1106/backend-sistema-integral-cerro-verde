package com.alexander.sistema_cerro_verde_backend.entity.reservas;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="reservas")
public class Reservas {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idReserva;
    private Integer estado = 1;
    private String estadoReserva;
    private String fechaHora;

    //Relación de Muchos a Uno con Usuario

    //Relación de Muchos a Uno con Cliente

    public Integer getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(Integer idReserva) {
        this.idReserva = idReserva;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public String getEstadoReserva() {
        return estadoReserva;
    }

    public void setEstadoReserva(String estadoReserva) {
        this.estadoReserva = estadoReserva;
    }

    public String getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(String fechaHora) {
        this.fechaHora = fechaHora;
    }
}
