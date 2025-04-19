package com.alexander.sistema_cerro_verde_backend.service.caja;

import java.util.List;
import java.util.Optional;

import com.alexander.sistema_cerro_verde_backend.entity.caja.ArqueosCaja;

public interface ArqueosCajaService {
    
    List<ArqueosCaja> buscarTodos();
    
    ArqueosCaja guardar(ArqueosCaja arqueo);

    Optional<ArqueosCaja> buscarId(Integer id);

    void eliminarId(Integer id);

}
