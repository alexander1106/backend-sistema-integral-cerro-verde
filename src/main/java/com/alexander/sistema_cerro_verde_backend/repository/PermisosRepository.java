package com.alexander.sistema_cerro_verde_backend.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alexander.sistema_cerro_verde_backend.entity.Permisos;

@Repository
public interface PermisosRepository extends JpaRepository<Permisos,Integer>{
    Optional<Permisos> findByNombrePermiso(String nombrePermiso);
    List<Permisos> findBySubModulo_Modulo_IdModulo(Integer id);

} 