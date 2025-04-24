package com.alexander.sistema_cerro_verde_backend.compras.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alexander.sistema_cerro_verde_backend.compras.entity.Compras;
import com.alexander.sistema_cerro_verde_backend.compras.repository.ComprasRepository;
import com.alexander.sistema_cerro_verde_backend.compras.repository.ProductosRepository;
import com.alexander.sistema_cerro_verde_backend.compras.service.IComprasService;

@Service
public class ComprasService implements IComprasService{
    @Autowired
    private ComprasRepository repoCompras;

    @Autowired
    private ProductosRepository repoProductos;

    public List<Compras> buscarTodos(){
        return repoCompras.findAll();
    }

    public void guardar(Compras compra){
        System.out.println("DETALLES: " + compra.getDetallecompra());
        repoCompras.save(compra);
    }

    public void modificar(Compras compra){
        repoCompras.save(compra);
    }

    public Optional<Compras> buscarId(Integer id_compra){
        return repoCompras.findById(id_compra);
    }

    public void eliminar(Integer id_compra){
        repoCompras.deleteById(id_compra);
    }
}