package com.alexander.sistema_cerro_verde_backend.compras.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alexander.sistema_cerro_verde_backend.compras.entity.DetallesCompra;
import com.alexander.sistema_cerro_verde_backend.compras.repository.DetallesCompraRepository;
import com.alexander.sistema_cerro_verde_backend.compras.service.IDetallesCompraService;

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