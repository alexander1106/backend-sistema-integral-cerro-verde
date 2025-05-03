package com.alexander.sistema_cerro_verde_backend.service.compras.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alexander.sistema_cerro_verde_backend.entity.compras.DetallesCompra;
import com.alexander.sistema_cerro_verde_backend.repository.compras.DetallesCompraRepository;
import com.alexander.sistema_cerro_verde_backend.service.compras.IDetallesCompraService;

@Service
public class DetallesCompraService implements IDetallesCompraService{
    @Autowired
    private DetallesCompraRepository repoDetallesCompra;
    public List<DetallesCompra> buscarTodos(){
        return repoDetallesCompra.findAll();
    }
    public void guardar(DetallesCompra detallecompra){
        repoDetallesCompra.save(detallecompra);
    }
    public void modificar(DetallesCompra detallecompra){
        repoDetallesCompra.save(detallecompra);
    }
    public Optional<DetallesCompra> buscarId(Integer id_detalle_compra){
        return repoDetallesCompra.findById(id_detalle_compra);
    }
    public void eliminar(Integer id_detalle_compra){
        repoDetallesCompra.deleteById(id_detalle_compra);
    }
}