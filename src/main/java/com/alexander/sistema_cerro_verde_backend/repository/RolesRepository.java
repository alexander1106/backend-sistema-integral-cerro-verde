package com.alexander.sistema_cerro_verde_backend.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alexander.sistema_cerro_verde_backend.entity.Roles;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Integer> {
    Optional<Roles> findByNombreRol(String nombreRol);
}
