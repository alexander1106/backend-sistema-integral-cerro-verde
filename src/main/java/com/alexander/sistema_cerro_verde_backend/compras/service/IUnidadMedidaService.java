package com.alexander.sistema_cerro_verde_backend.compras.service;

import java.util.List;
import java.util.Optional;

import com.alexander.sistema_cerro_verde_backend.compras.entity.UnidadMedida;

public interface IUnidadMedidaService {
    List<UnidadMedida> buscarTodos();
    //Método para listar todos los registros 
    void guardar(UnidadMedida unidad);

    void modificar(UnidadMedida unidad);

    Optional<UnidadMedida> buscarId(Integer id);

    void eliminar(Integer id);
}
