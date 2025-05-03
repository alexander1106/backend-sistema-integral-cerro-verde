package com.alexander.sistema_cerro_verde_backend.entity.ventas;

public class Ventas {
    private Integer idVentas;
    private String fecha;
    private Double total;
    private Double descuento;
    private Double cargo;

    //Relación de muchos a uno con Usuario

    //Relación de muchos a uno con Cliente

    //Relación de Uno a Muchos con MovimientosInventario

    public Integer getIdVentas() {
        return idVentas;
    }

    public void setIdVentas(Integer idVentas) {
        this.idVentas = idVentas;
    }

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
}
