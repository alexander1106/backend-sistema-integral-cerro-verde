package com.alexander.sistema_cerro_verde_backend.compras.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alexander.sistema_cerro_verde_backend.compras.entity.Productos;
import com.alexander.sistema_cerro_verde_backend.compras.repository.ProductosRepository;
import com.alexander.sistema_cerro_verde_backend.compras.service.IProductosService;

@Service
public class ProductosService implements IProductosService{
    @Autowired
    private ProductosRepository repoProductos;

    @Override
    public List<Productos> buscarTodos(){
        return repoProductos.findAll();
    }
    public void guardar(Productos producto){
        repoProductos.save(producto);
    }
    public void modificar(Productos producto){
        repoProductos.save(producto);
    }
    public Optional<Productos> buscarId(Integer id_producto){
        return repoProductos.findById(id_producto);
    }
    public void eliminar(Integer id_producto){
        repoProductos.deleteById(id_producto);
    }
}