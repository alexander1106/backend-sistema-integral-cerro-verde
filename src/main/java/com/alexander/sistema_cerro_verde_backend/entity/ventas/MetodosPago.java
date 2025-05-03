package com.alexander.sistema_cerro_verde_backend.entity.ventas;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="metodos_pago")
public class MetodosPago {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_metodo_pago")
    private Integer idMetodoPago;
    private String nombre;
    private Integer estado = 1;

    //Relación uno a Muchos con DetalleVentas
    

    public Integer getIdMetodoPago() {
        return idMetodoPago;
    }

    public void setIdMetodoPago(Integer idMetodoPago) {
        this.idMetodoPago = idMetodoPago;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }
}
