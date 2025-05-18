package com.alexander.sistema_cerro_verde_backend.repository.caja;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alexander.sistema_cerro_verde_backend.entity.caja.Cajas;
import com.alexander.sistema_cerro_verde_backend.entity.seguridad.Usuarios;

public interface CajasRepository extends JpaRepository<Cajas, Integer> {

    Optional<Cajas> findByEstadoCaja(String estadoCaja);

    List<Cajas> findAllByEstadoCaja(String estadoCaja);

    Optional<Cajas> findByUsuarioAndEstadoCaja(Usuarios usuario, String estadoCaja);

    Optional<Cajas> findByUsuario(Usuarios usuario);

}
