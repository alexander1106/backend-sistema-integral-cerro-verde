package com.alexander.sistema_cerro_verde_backend.service;

import java.util.List;

import com.alexander.sistema_cerro_verde_backend.entity.Submodulo;

public interface ISubModuloService {
    Submodulo guardarSubModulo(Submodulo subModulos);
    List<Submodulo> obtenerSubModulosPorModuloId(Integer id);
    List<Submodulo> obtenerTodosLosSubModulos();
    //Modulos editarSubModulo(Submodulo Submodulo);
    //void eliminarSubModulo(Integer iid);   
}
