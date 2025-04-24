package com.alexander.sistema_cerro_verde_backend.compras.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alexander.sistema_cerro_verde_backend.compras.entity.CategoriasProductos;
import com.alexander.sistema_cerro_verde_backend.compras.repository.CategoriasProductosRepository;
import com.alexander.sistema_cerro_verde_backend.compras.service.ICategoriasProductosService;

@Service
public class CategoriasProductosService implements ICategoriasProductosService{
    @Autowired
    private CategoriasProductosRepository repoCategoriasProductos;
    public List<CategoriasProductos> buscarTodos(){
        return repoCategoriasProductos.findAll();
    }
    public void guardar (CategoriasProductos categoriaproducto){
        repoCategoriasProductos.save(categoriaproducto);
    }
    public void modificar(CategoriasProductos categoriaproducto){
        repoCategoriasProductos.save(categoriaproducto);
    }
    public Optional<CategoriasProductos> buscarId(Integer id_categoria){
        return repoCategoriasProductos.findById(id_categoria);
    }
    public void eliminar(Integer id_categoria){
        repoCategoriasProductos.deleteById(id_categoria);
    }
}