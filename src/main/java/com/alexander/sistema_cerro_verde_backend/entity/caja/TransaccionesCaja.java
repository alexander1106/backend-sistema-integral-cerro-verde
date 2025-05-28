package com.alexander.sistema_cerro_verde_backend.entity.caja;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "transacciones_caja")
public class TransaccionesCaja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_transaccion_caja")
    private Integer id;
    private Double montoTransaccion;
    private Date fechaHoraTransaccion;

    @ManyToOne
    @JoinColumn(name = "id_caja")
    private Cajas caja;

    @ManyToOne
    @JoinColumn(name = "id_tipo_transacciones")
    private TipoTransacciones tipo;

    private Integer estado = 1;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getMontoTransaccion() {
        return montoTransaccion;
    }

    public void setMontoTransaccion(Double montoTransaccion) {
        this.montoTransaccion = montoTransaccion;
    }

    public Date getFechaHoraTransaccion() {
        return fechaHoraTransaccion;
    }

    public void setFechaHoraTransaccion(Date fechaHoraTransaccion) {
        this.fechaHoraTransaccion = fechaHoraTransaccion;
    }

    public Cajas getCaja() {
        return caja;
    }

    public void setCaja(Cajas caja) {
        this.caja = caja;
    }

    public TipoTransacciones getTipo() {
        return tipo;
    }

    public void setTipo(TipoTransacciones tipo) {
        this.tipo = tipo;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "TransaccionesCaja [id=" + id + ", montoTransaccion=" + montoTransaccion + ", fechaHoraTransaccion="
                + fechaHoraTransaccion + ", caja=" + caja + ", tipo=" + tipo + ", estado="
                + estado + "]";
    }



    
}
