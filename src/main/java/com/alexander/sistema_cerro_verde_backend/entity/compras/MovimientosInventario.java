package com.alexander.sistema_cerro_verde_backend.entity.compras;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "movimientos_inventario")
@SQLDelete(sql = "UPDATE movimientos_inventario SET estado = 0 WHERE id_movimiento_inventario=?")
@Where(clause = "estado = 1")
public class MovimientosInventario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_movimiento_inventario;
    private String fecha;
    private Integer cantidad;
    private String tipo_movimiento;
    private Integer estado = 1;
    @ManyToOne
    @JoinColumn(name = "id_compra")
    private Compras compra;
    @ManyToOne
    @JoinColumn(name = "id_producto")
    private Productos producto;

    public Integer getId_movimiento_inventario() {
        return this.id_movimiento_inventario;
    }

    public void setId_movimiento_inventario(Integer id_movimiento_inventario) {
        this.id_movimiento_inventario = id_movimiento_inventario;
    }

    public String getFecha() {
        return this.fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Integer getCantidad() {
        return this.cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getTipo_movimiento() {
        return this.tipo_movimiento;
    }

    public void setTipo_movimiento(String tipo_movimiento) {
        this.tipo_movimiento = tipo_movimiento;
    }

    public Integer getEstado() {
        return this.estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Compras getCompra() {
        return this.compra;
    }

    public void setCompra(Compras compra) {
        this.compra = compra;
    }

    public Productos getProducto() {
        return this.producto;
    }

    public void setProducto(Productos producto) {
        this.producto = producto;
    }

    @Override
    public String toString() {
        return "{" +
            " id_movimiento_inventario='" + getId_movimiento_inventario() + "'" +
            ", fecha='" + getFecha() + "'" +
            ", cantidad='" + getCantidad() + "'" +
            ", tipo_movimiento='" + getTipo_movimiento() + "'" +
            ", estado='" + getEstado() + "'" +
            ", compra='" + getCompra() + "'" +
            ", producto='" + getProducto() + "'" +
            "}";
    }
}