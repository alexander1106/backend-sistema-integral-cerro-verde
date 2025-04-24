package com.alexander.sistema_cerro_verde_backend.compras.entity;

import java.util.List;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "compras")
@SQLDelete(sql = "UPDATE compras SET estado = 0 WHERE id_compra=?")
@Where(clause = "estado = 1")
public class Compras {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_compra;
    private String correlativo;
    private String fecha_compra;    
    private Integer estado = 1;
    private Integer id_usuario;
    private Integer id_sucursal;
    @ManyToOne
    @JoinColumn(name = "ruc_proveedor")
    private Proveedores proveedor;
    @OneToMany(mappedBy = "compra", cascade=CascadeType.ALL)
    @JsonManagedReference
    private List<DetallesCompra> detallecompra;
    @OneToMany(mappedBy = "compra")
    @JsonIgnore
    private List<MovimientosInventario> movimientoinventario;

    public Integer getId_compra() {
        return this.id_compra;
    }

    public void setId_compra(Integer id_compra) {
        this.id_compra = id_compra;
    }

    public String getCorrelativo() {
        return this.correlativo;
    }

    public void setCorrelativo(String correlativo) {
        this.correlativo = correlativo;
    }

    public String getFecha_compra() {
        return this.fecha_compra;
    }

    public void setFecha_compra(String fecha_compra) {
        this.fecha_compra = fecha_compra;
    }

    public Integer getEstado() {
        return this.estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Integer getId_usuario() {
        return this.id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }

    public Integer getId_sucursal() {
        return this.id_sucursal;
    }

    public void setId_sucursal(Integer id_sucursal) {
        this.id_sucursal = id_sucursal;
    }

    public Proveedores getProveedor() {
        return this.proveedor;
    }

    public void setProveedor(Proveedores proveedor) {
        this.proveedor = proveedor;
    }

    public List<DetallesCompra> getDetallecompra() {
        return this.detallecompra;
    }

    public void setDetallecompra(List<DetallesCompra> detallecompra) {
        this.detallecompra = detallecompra;
    }

    public List<MovimientosInventario> getMovimientoinventario() {
        return this.movimientoinventario;
    }

    public void setMovimientoinventario(List<MovimientosInventario> movimientoinventario) {
        this.movimientoinventario = movimientoinventario;
    }

    @Override
    public String toString() {
        return "{" +
            " id_compra='" + getId_compra() + "'" +
            ", correlativo='" + getCorrelativo() + "'" +
            ", fecha_compra='" + getFecha_compra() + "'" +
            ", estado='" + getEstado() + "'" +
            ", id_usuario='" + getId_usuario() + "'" +
            ", id_sucursal='" + getId_sucursal() + "'" +
            ", proveedor='" + getProveedor() + "'" +
            ", detallecompra='" + getDetallecompra() + "'" +
            ", movimientoinventario='" + getMovimientoinventario() + "'" +
            "}";
    }
}