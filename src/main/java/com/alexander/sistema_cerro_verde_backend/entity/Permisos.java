package com.alexander.sistema_cerro_verde_backend.entity;
import java.util.LinkedHashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
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
@Table(name = "permisos")
public class Permisos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_permiso")
    private Integer idPermiso;
    @Column(name="nombre")
    private String nombrePermiso;
    private boolean estado;

    @OneToMany(mappedBy = "permisos", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<RolesPermisos> rolesPermisos = new LinkedHashSet<>();
     // Relación con Submodulos (clave foránea)
     @ManyToOne
     @JoinColumn(name = "idSubModulo", nullable = true)  // La relación con el submódulo es opcional
     private Submodulo subModulo;
 
    public Integer getIdPermiso() {
        return idPermiso;
    }
    public void setIdPermiso(Integer idPermisos) {
        this.idPermiso = idPermisos;
    }
    public String getNombrePermiso() {
        return nombrePermiso;
    }
    public void setNombrePermiso(String nombrePermiso) {
        this.nombrePermiso = nombrePermiso;
    }
    public boolean isEstado() {
        return estado;
    }
    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    public Set<RolesPermisos> getRolesPermisos() {
        return rolesPermisos;
    }
    public void setRolesPermisos(Set<RolesPermisos> rolesPermisos) {
        this.rolesPermisos = rolesPermisos;
    }
    
    public Submodulo getSubModulo() {
        return subModulo;
    }
    public void setSubModulo(Submodulo submodulo) {
        this.subModulo = submodulo;
    }
    

}
