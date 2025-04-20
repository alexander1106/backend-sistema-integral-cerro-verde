package com.alexander.sistema_cerro_verde_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alexander.sistema_cerro_verde_backend.entity.Modulos;

@Repository
public interface ModuloRepository extends  JpaRepository<Modulos, Integer> {

}
