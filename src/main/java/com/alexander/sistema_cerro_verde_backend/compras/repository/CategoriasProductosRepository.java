package com.alexander.sistema_cerro_verde_backend.compras.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.alexander.sistema_cerro_verde_backend.compras.entity.CategoriasProductos;

public interface CategoriasProductosRepository extends JpaRepository<CategoriasProductos, Integer> {
    @Query("SELECT cp FROM CategoriasProductos cp WHERE cp.estado = 1")
    List<CategoriasProductos> findActive();

    @Query("SELECT cp FROM CategoriasProductos cp WHERE cp.id_categoria = :id")
    Optional<CategoriasProductos> findByIdIncludingInactives(@Param("id") Integer id);
}