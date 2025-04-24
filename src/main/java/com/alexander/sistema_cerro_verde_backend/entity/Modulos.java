package com.alexander.sistema_cerro_verde_backend.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="modulos")
public class Modulos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idModulo;
    private String nombre;
    private String icon;
    @OneToMany(mappedBy = "modulo", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Submodulo> submodulos;  // Lista de submodulos asociados a este módulo

    public Integer getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(Integer idModulo) {
        this.idModulo = idModulo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Submodulo> getSubmodulos() {
        return submodulos;
    }

    public void setSubmodulos(List<Submodulo> submodulos) {
        this.submodulos = submodulos;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
