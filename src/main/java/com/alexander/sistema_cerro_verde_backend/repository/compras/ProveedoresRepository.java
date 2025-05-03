package com.alexander.sistema_cerro_verde_backend.repository.compras;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.alexander.sistema_cerro_verde_backend.entity.compras.Proveedores;

public interface ProveedoresRepository extends JpaRepository<Proveedores, String> {
    @Query("SELECT p FROM Proveedores p WHERE p.estado = 1")
    List<Proveedores> findActive();

    @Query("SELECT p FROM Proveedores p WHERE p.ruc_proveedor = :ruc")
    Optional<Proveedores> findByRucIncludingInactives(@Param("ruc") String ruc);
}
