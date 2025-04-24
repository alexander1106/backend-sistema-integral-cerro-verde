package com.alexander.sistema_cerro_verde_backend.compras.service;

import java.util.List;
import java.util.Optional;

import com.alexander.sistema_cerro_verde_backend.compras.entity.Proveedores;

public interface IProveedoresService {
    List<Proveedores> buscarTodos();
    //Método para listar todos los registros 
    void guardar(Proveedores proveedor);

    void modificar(Proveedores proveedor);

    Optional<Proveedores> buscarId(String ruc_proveedor);

    void eliminar(String ruc_proveedor);
}