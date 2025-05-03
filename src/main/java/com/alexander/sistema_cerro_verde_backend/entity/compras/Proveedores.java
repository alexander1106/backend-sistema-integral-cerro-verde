package com.alexander.sistema_cerro_verde_backend.entity.compras;

import java.util.List;

import org.hibernate.annotations.SQLDelete;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "proveedores")
@SQLDelete(sql = "UPDATE proveedores SET estado = 0 WHERE ruc_proveedor=?")
public class Proveedores {
    @Id
    private String ruc_proveedor;
    private String razon_social;
    private String direccion;
    private Integer estado = 1;
    @OneToMany(mappedBy = "proveedor")
    @JsonIgnore
    private List<Compras> compra;

    public String getRuc_proveedor() {
        return this.ruc_proveedor;
    }

    public void setRuc_proveedor(String ruc_proveedor) {
        this.ruc_proveedor = ruc_proveedor;
    }

    public String getRazon_social() {
        return this.razon_social;
    }

    public void setRazon_social(String razon_social) {
        this.razon_social = razon_social;
    }

    public String getDireccion() {
        return this.direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Integer getEstado() {
        return this.estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public List<Compras> getCompra() {
        return this.compra;
    }

    public void setCompra(List<Compras> compra) {
        this.compra = compra;
    }

    @Override
    public String toString() {
        return "{" +
            " ruc_proveedor='" + getRuc_proveedor() + "'" +
            ", razon_social='" + getRazon_social() + "'" +
            ", direccion='" + getDireccion() + "'" +
            ", estado='" + getEstado() + "'" +
            ", compra='" + getCompra() + "'" +
            "}";
    }
}