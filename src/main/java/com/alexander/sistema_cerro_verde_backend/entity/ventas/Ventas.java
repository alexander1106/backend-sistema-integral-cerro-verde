package com.alexander.sistema_cerro_verde_backend.entity.ventas;

import com.alexander.sistema_cerro_verde_backend.entity.reservas.Clientes;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="ventas")
public class Ventas {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idVenta;
    private String fecha;
    private Double total;
    private Double descuento;
    private Double cargo;

    //Relación de muchos a uno con Usuario

    //Relación de muchos a uno con Cliente
    @ManyToOne
    @JoinColumn(name="id_cliente")
    private Clientes cliente;

    //Relación de Uno a Muchos con MovimientosInventario


    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getDescuento() {
        return descuento;
    }

    public void setDescuento(Double descuento) {
        this.descuento = descuento;
    }

    public Double getCargo() {
        return cargo;
    }

    public void setCargo(Double cargo) {
        this.cargo = cargo;
    }

    public Clientes getCliente() {
        return cliente;
    }

    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }

    public Integer getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Integer idVenta) {
        this.idVenta = idVenta;
    }
}
