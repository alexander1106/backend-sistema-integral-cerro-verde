package com.alexander.sistema_cerro_verde_backend.service.compras.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alexander.sistema_cerro_verde_backend.entity.compras.MovimientosInventario;
import com.alexander.sistema_cerro_verde_backend.repository.compras.MovimientosInventarioRepository;
import com.alexander.sistema_cerro_verde_backend.service.compras.IMovimientosInventarioService;

@Service
public class MovimientosInventariosService implements IMovimientosInventarioService{
    @Autowired
    private MovimientosInventarioRepository repoMovimientosInventario;
    public List<MovimientosInventario> buscarTodos(){
        return repoMovimientosInventario.findAll();
    }
    public void guardar(MovimientosInventario movimientoinventario){
        repoMovimientosInventario.save(movimientoinventario);
    }
    public void modificar(MovimientosInventario movimientoinventario){
        repoMovimientosInventario.save(movimientoinventario);
    }
    public Optional<MovimientosInventario> buscarId(Integer id_movimiento_inventario){
        return repoMovimientosInventario.findById(id_movimiento_inventario);
    }
    public void eliminar(Integer id_movimiento_inventario){
        repoMovimientosInventario.deleteById(id_movimiento_inventario);
    }
}