package com.alexander.sistema_cerro_verde_backend.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alexander.sistema_cerro_verde_backend.entity.Submodulo;

@Repository
public interface SubModuloRepository extends JpaRepository<Submodulo, Integer> {
    List<Submodulo> findByModuloIdModulo(Integer idModulo);

    List<Submodulo> idSubModulo(Integer idSubModulo);

}
