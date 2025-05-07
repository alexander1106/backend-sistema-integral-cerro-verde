package com.alexander.sistema_cerro_verde_backend.service.compras.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alexander.sistema_cerro_verde_backend.entity.compras.Productos;
import com.alexander.sistema_cerro_verde_backend.repository.compras.ProductosRepository;
import com.alexander.sistema_cerro_verde_backend.service.compras.IProductosService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
public class ProductosService implements IProductosService{
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ProductosRepository repoProductos;

    @Override
    public List<Productos> buscarTodos(){ //Buscar todos los productos
        return repoProductos.findAll();
    }

    @Override
    public List<Productos> buscarActivos(){ //Buscar los productos activos, estado = 1
        return repoProductos.findActive();
    }

    @Override
    @Transactional
    public void guardar(Productos producto){ //Guardar producto
        Optional<Productos> existente = repoProductos.findByNombreIgnoreCase(producto.getNombre());
        if(existente.isPresent()){
            Productos p = existente.get();
            if(p.getEstado() == 0){
                p.setEstado(1);
                p.setNombre(producto.getNombre());
                p.setDescripcion(producto.getDescripcion());
                p.setCategoriaproducto(producto.getCategoriaproducto());
                p.setUnidad(producto.getUnidad());
                entityManager.merge(p);
            } else {
                repoProductos.save(producto);
            }
        } else {
            producto.setEstado(1);
            repoProductos.save(producto);
        }
    }

    @Override
    public void modificar(Productos producto){ //Modificar productos
        repoProductos.save(producto);
    }
    
    @Override
    public Optional<Productos> buscarId(Integer id_producto){ //Buscar productos por Id
        return repoProductos.findById(id_producto);
    }

    @Override
    public void eliminar(Integer id_producto){ //Eliminar producto
        repoProductos.deleteById(id_producto);
    }
}