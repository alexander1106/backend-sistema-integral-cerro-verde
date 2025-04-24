package com.alexander.sistema_cerro_verde_backend.compras.service;

import java.util.List;
import java.util.Optional;

import com.alexander.sistema_cerro_verde_backend.compras.entity.Productos;

public interface IProductosService {
    List<Productos> buscarTodos();
    //Método para listar todos los registros
    void guardar(Productos producto);

    void modificar(Productos producto);

    Optional<Productos> buscarId(Integer id_producto);

    void eliminar(Integer id_producto);
}