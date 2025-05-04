package com.alexander.sistema_cerro_verde_backend.service.compras.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alexander.sistema_cerro_verde_backend.entity.compras.Proveedores;
import com.alexander.sistema_cerro_verde_backend.repository.compras.ProveedoresRepository;
import com.alexander.sistema_cerro_verde_backend.service.compras.IProveedoresService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
public class ProveedoresService implements IProveedoresService {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ProveedoresRepository repoProveedores;

    @Override
    public List<Proveedores> buscarTodos() { //Buscar todos los proveedores
        return repoProveedores.findAll();
    }

    @Override
    public List<Proveedores> buscarActivos() { //Buscar los proveedores activos
        return repoProveedores.findActive();
    }

    @Override
    @Transactional
    public void guardar(Proveedores proveedor) {
        Optional<Proveedores> existente = repoProveedores.findByRucIncludingInactives(proveedor.getRuc_proveedor());
        if (existente.isPresent()) {
            Proveedores prov = existente.get();
            if (prov.getEstado() == 0) {
                prov.setEstado(1);
                prov.setRazon_social(proveedor.getRazon_social());
                prov.setDireccion(proveedor.getDireccion());
                entityManager.merge(prov);
            } else {
                repoProveedores.save(proveedor);
            }
        } else {
            proveedor.setEstado(1);
            repoProveedores.save(proveedor);
        }
    }

    @Override
    public void modificar(Proveedores proveedor) {
        repoProveedores.save(proveedor);
    }

    @Override
    public Optional<Proveedores> buscarId(String ruc_proveedor) {
        return repoProveedores.findById(ruc_proveedor);
    }

    @Override
    public void eliminar(String ruc_proveedor) {
        repoProveedores.deleteById(ruc_proveedor);
    }
}
