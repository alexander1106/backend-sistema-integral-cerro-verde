package com.alexander.sistema_cerro_verde_backend.repository.compras;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alexander.sistema_cerro_verde_backend.entity.compras.Compras;

public interface ComprasRepository extends JpaRepository<Compras, Integer> {
}
