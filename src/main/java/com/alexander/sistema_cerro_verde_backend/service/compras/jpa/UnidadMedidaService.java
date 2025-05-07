package com.alexander.sistema_cerro_verde_backend.service.compras.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alexander.sistema_cerro_verde_backend.entity.compras.UnidadMedida;
import com.alexander.sistema_cerro_verde_backend.repository.compras.UnidadMedidaRepository;
import com.alexander.sistema_cerro_verde_backend.service.compras.IUnidadMedidaService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
public class UnidadMedidaService implements IUnidadMedidaService{
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private UnidadMedidaRepository repoUnidad;
    
    @Override
    public List<UnidadMedida> buscarTodos(){
        return repoUnidad.findAll();
    }

    @Override
    public List<UnidadMedida> buscarActivos(){
        return repoUnidad.findActive();
    }

    @Override
    @Transactional
    public void guardar (UnidadMedida unidad){
        Optional<UnidadMedida> existente = repoUnidad.findByNombreIgnoreCase(unidad.getNombre());
        if(existente.isPresent()){
            UnidadMedida um = existente.get();
            if(um.getEstado() == 0){
                um.setEstado(1);
                um.setNombre(unidad.getNombre());
                um.setAbreviatura(unidad.getAbreviatura());
                entityManager.merge(um);
            } else {
                repoUnidad.save(unidad);
            }
        } else {
            unidad.setEstado(1);
            repoUnidad.save(unidad);
        }
    }

    @Override
    public void modificar(UnidadMedida unidad){
        repoUnidad.save(unidad);
    }

    @Override
    public Optional<UnidadMedida> buscarId(Integer id){
        return repoUnidad.findById(id);
    }

    @Override
    public void eliminar(Integer id){
        repoUnidad.deleteById(id);
    }
}
