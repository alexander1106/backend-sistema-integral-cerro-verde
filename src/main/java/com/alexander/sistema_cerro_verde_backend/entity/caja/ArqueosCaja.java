package com.alexander.sistema_cerro_verde_backend.entity.caja;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "arqueos_caja")
public class ArqueosCaja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_arqueo")
    private Integer id;

    private String observaciones;

    @OneToOne
    @JoinColumn(name = "id_caja")
    private Cajas caja;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Cajas getCaja() {
        return caja;
    }

    public void setCaja(Cajas caja) {
        this.caja = caja;
    }

    @Override
    public String toString() {
        return "ArqueosCaja [id=" + id + ", observaciones=" + observaciones + ", caja=" + caja + "]";
    }

    
}
