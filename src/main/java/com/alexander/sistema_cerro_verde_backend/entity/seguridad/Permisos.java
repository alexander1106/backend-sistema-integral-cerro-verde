package com.alexander.sistema_cerro_verde_backend.entity.seguridad;
import java.util.LinkedHashSet;
import java.util.List;
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
    private Integer id;
    @Column(name="nombre")
    private String nombrePermiso;
    private String descripcion;
    private boolean estado;

    @OneToMany(mappedBy = "permisos", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<RolesPermisos> rolesPermisos = new LinkedHashSet<>();
    
     // Relación con Submodulos (clave foránea)
     @ManyToOne
     @JoinColumn(name = "id_sub_modulo", nullable = true)  // La relación con el submódulo es opcional
     private Submodulo subModulo;

     @OneToMany(mappedBy = "permisos", cascade = CascadeType.ALL)
     @JsonIgnore  // Si no necesitas serializar la relación completa
    private List<UsuariosPermisos> usuariosPermisos;
    
  
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
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public List<UsuariosPermisos> getUsuariosPermisos() {
        return usuariosPermisos;
    }
    public void setUsuariosPermisos(List<UsuariosPermisos> usuariosPermisos) {
        this.usuariosPermisos = usuariosPermisos;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    

}
