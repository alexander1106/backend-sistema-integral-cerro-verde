package com.alexander.sistema_cerro_verde_backend.compras.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alexander.sistema_cerro_verde_backend.compras.entity.UnidadMedida;
import com.alexander.sistema_cerro_verde_backend.compras.repository.UnidadMedidaRepository;
import com.alexander.sistema_cerro_verde_backend.compras.service.IUnidadMedidaService;

@Service
public class UnidadMedidaService implements IUnidadMedidaService{
    @Autowired
    private UnidadMedidaRepository repoUnidad;
    
    public List<UnidadMedida> buscarTodos(){
        return repoUnidad.findAll();
    }
    public void guardar(UnidadMedida unidad){
        repoUnidad.save(unidad);
    }
    public void modificar(UnidadMedida unidad){
        repoUnidad.save(unidad);
    }
    public Optional<UnidadMedida> buscarId(Integer id){
        return repoUnidad.findById(id);
    }
    public void eliminar(Integer id){
        repoUnidad.deleteById(id);
    }
}
