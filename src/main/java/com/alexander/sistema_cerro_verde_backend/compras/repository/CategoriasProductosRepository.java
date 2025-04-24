package com.alexander.sistema_cerro_verde_backend.compras.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.alexander.sistema_cerro_verde_backend.compras.entity.CategoriasProductos;

public interface CategoriasProductosRepository extends JpaRepository<CategoriasProductos, Integer> {
}