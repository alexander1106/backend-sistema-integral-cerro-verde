package com.alexander.sistema_cerro_verde_backend.compras.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alexander.sistema_cerro_verde_backend.compras.entity.DetallesCompra;
import com.alexander.sistema_cerro_verde_backend.compras.service.IDetallesCompraService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api")
public class DetallesCompraController {
    @Autowired
    private IDetallesCompraService serviceDetallesCompra;
    @GetMapping("/detallescompra")
    public List<DetallesCompra> buscarTodos(){
        return serviceDetallesCompra.buscarTodos(); //findAll
    }
    @PostMapping("/detallescompra")
    public DetallesCompra guardar(@RequestBody DetallesCompra detallecompra) {
        serviceDetallesCompra.guardar(detallecompra);
        return detallecompra;
    }
    @PutMapping("/detallescompra")
    public DetallesCompra modificar(@RequestBody DetallesCompra detallecompra) {
        serviceDetallesCompra.modificar(detallecompra);
        return detallecompra;
    }
    @GetMapping("/detallescompra/{id}")
    public Optional<DetallesCompra> buscarId(@PathVariable("id") Integer id_detalle_compra) {
        return serviceDetallesCompra.buscarId(id_detalle_compra);
    }
    @DeleteMapping("/detallescompra/{id}")
    public String eliminar(@PathVariable("id") Integer id_detalle_compra){
        serviceDetallesCompra.eliminar(id_detalle_compra);
        return "Proveedor eliminado";
    }
}