package com.alexander.sistema_cerro_verde_backend.repository.compras;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.alexander.sistema_cerro_verde_backend.entity.compras.UnidadMedida;

public interface UnidadMedidaRepository extends JpaRepository<UnidadMedida, Integer> {

    @Query("SELECT um FROM UnidadMedida um WHERE um.estado = 1")
    List<UnidadMedida> findActive();

    Optional<UnidadMedida> findByNombreIgnoreCase(String nombre);
}
