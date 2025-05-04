package com.alexander.sistema_cerro_verde_backend.repository.compras;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.alexander.sistema_cerro_verde_backend.entity.compras.CategoriasProductos;

public interface CategoriasProductosRepository extends JpaRepository<CategoriasProductos, Integer> {
    @Query("SELECT cp FROM CategoriasProductos cp WHERE cp.estado = 1")
    List<CategoriasProductos> findActive();

    Optional<CategoriasProductos> findByNombreIgnoreCase(String nombre);
}