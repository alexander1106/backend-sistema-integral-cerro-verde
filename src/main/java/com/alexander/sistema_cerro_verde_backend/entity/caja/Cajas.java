package com.alexander.sistema_cerro_verde_backend.entity.caja;

import java.util.Date;

import com.alexander.sistema_cerro_verde_backend.entity.Sucursales;
import com.alexander.sistema_cerro_verde_backend.entity.Usuarios;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuarios usuario;

    @ManyToOne
    @JoinColumn(name = "id_sucursal")
    private Sucursales sucursal;

    private Integer estado = 1;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getMontoApertura() {
        return montoApertura;
    }

    public void setMontoApertura(Double montoApertura) {
        this.montoApertura = montoApertura;
    }

    public Double getMontoCierre() {
        return montoCierre;
    }

    public void setMontoCierre(Double montoCierre) {
        this.montoCierre = montoCierre;
    }

    public Date getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(Date fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    public Date getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(Date fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    public String getEstadoCaja() {
        return estadoCaja;
    }

    public void setEstadoCaja(String estadoCaja) {
        this.estadoCaja = estadoCaja;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public Sucursales getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursales sucursal) {
        this.sucursal = sucursal;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Cajas [id=" + id + ", montoApertura=" + montoApertura + ", montoCierre=" + montoCierre
                + ", fechaApertura=" + fechaApertura + ", fechaCierre=" + fechaCierre + ", estadoCaja=" + estadoCaja
                + ", usuario=" + usuario + ", sucursal=" + sucursal + ", estado=" + estado + "]";
    }

    
}
