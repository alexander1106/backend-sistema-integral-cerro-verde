package com.alexander.sistema_cerro_verde_backend.compras.service;

import java.util.List;
import java.util.Optional;

import com.alexander.sistema_cerro_verde_backend.compras.entity.CategoriasProductos;

public interface ICategoriasProductosService {

    List<CategoriasProductos> buscarTodos(); //Listar categorias

    List<CategoriasProductos> buscarActivos(); //Listar categorias activos

    void guardar(CategoriasProductos categoriaProducto); //Guardar categoria

    void modificar(CategoriasProductos categoriaProducto); //Modificar categoria

    Optional<CategoriasProductos> buscarId(Integer id_categoria); //Buscar por Id categoria

    void eliminar(Integer id_categoria); //Eliminar categoria, estado = 0
}