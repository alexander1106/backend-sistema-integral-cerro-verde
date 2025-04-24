package com.alexander.sistema_cerro_verde_backend.compras.service;

import java.util.List;
import java.util.Optional;

import com.alexander.sistema_cerro_verde_backend.compras.entity.CategoriasProductos;

public interface ICategoriasProductosService {
    List<CategoriasProductos> buscarTodos();
    //Método para listar todos los registros 
    void guardar(CategoriasProductos categoriaProducto);

    void modificar(CategoriasProductos categoriaProducto);

    Optional<CategoriasProductos> buscarId(Integer id_categoria);

    void eliminar(Integer id_categoria);
}