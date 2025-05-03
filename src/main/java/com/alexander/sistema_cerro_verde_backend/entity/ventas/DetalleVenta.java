package com.alexander.sistema_cerro_verde_backend.entity.ventas;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="detalle_venta")
public class DetalleVenta {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idDetalleVenta;
    private Integer cantidad;
    private Double precioUnit;
    private Double subTotal;
    private Integer estado = 1;
    
    //Relación de muchos a uno con Productos

    //Relación de muchos a uno con Ventas

    //Relación de muchos a uno con MetodosPago

    public Integer getIdDetalleVenta() {
        return idDetalleVenta;
    }

    public void setIdDetalleVenta(Integer idDetalleVenta) {
        this.idDetalleVenta = idDetalleVenta;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecioUnit() {
        return precioUnit;
    }

    public void setPrecioUnit(Double precioUnit) {
        this.precioUnit = precioUnit;
    }

    public Double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }
}
