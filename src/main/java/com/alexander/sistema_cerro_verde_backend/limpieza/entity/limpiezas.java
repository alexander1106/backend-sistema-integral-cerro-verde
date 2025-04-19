package com.alexander.sistema_cerro_verde_backend.limpieza.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "limpiezas")
public class limpiezas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_limpieza;
    private String fecha_hora_limpieza;
    private String observaciones;
    public Integer getId_limpieza() {
        return id_limpieza;
    }
    public void setId_limpieza(Integer id_limpieza) {
        this.id_limpieza = id_limpieza;
    }
    public String getFecha_hora_limpieza() {
        return fecha_hora_limpieza;
    }
    public void setFecha_hora_limpieza(String fecha_hora_limpieza) {
        this.fecha_hora_limpieza = fecha_hora_limpieza;
    }
    public String getObservaciones() {
        return observaciones;
    }
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    @Override
    public String toString() {
        return "limpiezas [id_limpieza=" + id_limpieza + ", fecha_hora_limpieza=" + fecha_hora_limpieza
                + ", observaciones=" + observaciones + "]";
    }

    
}