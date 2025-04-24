package com.alexander.sistema_cerro_verde_backend.compras.service;

import java.util.List;
import java.util.Optional;

import com.alexander.sistema_cerro_verde_backend.compras.entity.MovimientosInventario;

public interface IMovimientosInventarioService {
    List<MovimientosInventario> buscarTodos();
    //Método para listar todos los registros
    void guardar(MovimientosInventario movimientoinventario);

    void modificar(MovimientosInventario movimientoinventario);

    Optional<MovimientosInventario> buscarId(Integer id_movimiento_inventario);

    void eliminar(Integer id_movimiento_inventario);
}