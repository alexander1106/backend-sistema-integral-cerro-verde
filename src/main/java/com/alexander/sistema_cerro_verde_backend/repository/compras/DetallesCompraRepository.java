package com.alexander.sistema_cerro_verde_backend.repository.compras;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alexander.sistema_cerro_verde_backend.entity.compras.DetallesCompra;

public interface DetallesCompraRepository extends JpaRepository<DetallesCompra, Integer> {
    //@Query("SELECT p FROM productos p WHERE ")
}