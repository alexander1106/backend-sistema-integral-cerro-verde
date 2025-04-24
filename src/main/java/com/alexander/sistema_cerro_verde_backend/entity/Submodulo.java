package com.alexander.sistema_cerro_verde_backend.entity;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="submodulo")
public class Submodulo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_sub_modulo")
    private Integer idSubModulo;
    private String nombre;
    private String icon;
    
  // Relación con Permisos (un submódulo tiene varios permisos)
    @OneToMany(mappedBy = "subModulo")
    @JsonIgnore
    private Set<Permisos> permisos = new HashSet<>();

    @ManyToOne // Relación de muchos a uno con Modulo
    @JoinColumn(name = "id_modulo")
    private Modulos modulo;  // El módulo al que pertenece este submódulo


    
    public Integer getIdSubModulo() {
        return idSubModulo;
    }

    public void setIdSubModulo(Integer idSubModulo) {
        this.idSubModulo = idSubModulo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Modulos getModulo() {
        return modulo;
    }

    public void setModulo(Modulos modulo) {
        this.modulo = modulo;
    }

    public Set<Permisos> getPermisos() {
        return permisos;
    }

    public void setPermisos(Set<Permisos> permisos) {
        this.permisos = permisos;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

}
