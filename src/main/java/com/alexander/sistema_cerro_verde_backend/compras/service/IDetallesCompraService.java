package com.alexander.sistema_cerro_verde_backend.compras.service;

import java.util.List;
import java.util.Optional;

import com.alexander.sistema_cerro_verde_backend.compras.entity.DetallesCompra;

public interface IDetallesCompraService {
    List<DetallesCompra> buscarTodos();
    //Método para listar todos los registros 
    void guardar(DetallesCompra detallecompra);

    void modificar(DetallesCompra detallecompra);

    Optional<DetallesCompra> buscarId(Integer id_detalle_compra);

    void eliminar(Integer id_detalle_compra);
}