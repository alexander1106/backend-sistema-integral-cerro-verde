package com.alexander.sistema_cerro_verde_backend.repository.reservas;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alexander.sistema_cerro_verde_backend.entity.reservas.Clientes;

public interface ClientesRepository extends JpaRepository<Clientes, Integer>{

}
