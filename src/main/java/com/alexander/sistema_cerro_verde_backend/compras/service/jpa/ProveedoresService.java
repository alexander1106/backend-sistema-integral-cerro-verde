package com.alexander.sistema_cerro_verde_backend.compras.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alexander.sistema_cerro_verde_backend.compras.entity.Proveedores;
import com.alexander.sistema_cerro_verde_backend.compras.repository.ProveedoresRepository;
import com.alexander.sistema_cerro_verde_backend.compras.service.IProveedoresService;

@Service
public class ProveedoresService implements IProveedoresService{
    @Autowired
    private ProveedoresRepository repoProveedores;
    public List<Proveedores> buscarTodos(){
        return repoProveedores.findAll();
    }
    public void guardar(Proveedores proveedor){
        repoProveedores.save(proveedor);
    }
    public void modificar(Proveedores proveedor){
        repoProveedores.save(proveedor);
    }
    public Optional<Proveedores> buscarId(String ruc_proveedor){
        return repoProveedores.findById(ruc_proveedor);
    }
    public void eliminar(String ruc_proveedor){
        repoProveedores.deleteById(ruc_proveedor);
    }
}