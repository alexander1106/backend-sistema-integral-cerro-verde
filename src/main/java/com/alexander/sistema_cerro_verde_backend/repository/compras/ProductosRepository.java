package com.alexander.sistema_cerro_verde_backend.repository.compras;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.alexander.sistema_cerro_verde_backend.entity.compras.Productos;

public interface ProductosRepository extends JpaRepository<Productos, Integer> {
    @Query("SELECT p FROM Productos p WHERE p.estado = 1")
    List<Productos> findActive();

    Optional<Productos> findByNombreIgnoreCase(String nombre);
}