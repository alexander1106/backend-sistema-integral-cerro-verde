package com.alexander.sistema_cerro_verde_backend.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alexander.sistema_cerro_verde_backend.entity.Usuarios;
@Repository
public interface UsuariosRepository extends JpaRepository<Usuarios, Integer> {
    public Usuarios findByUsername(String username);
    public Optional<Usuarios> findByEmail(String email);
    public boolean existsByEmail(String email);
} 