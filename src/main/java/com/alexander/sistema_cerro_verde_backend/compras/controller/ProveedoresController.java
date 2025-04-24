package com.alexander.sistema_cerro_verde_backend.compras.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alexander.sistema_cerro_verde_backend.compras.entity.Proveedores;
import com.alexander.sistema_cerro_verde_backend.compras.service.IProveedoresService;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class ProveedoresController {
    @Autowired
    private IProveedoresService serviceProveedores;
    @GetMapping("/proveedores")
    public List<Proveedores> buscarTodos() {
        return serviceProveedores.buscarTodos(); //findAll
    }
    @PostMapping("/proveedores")
    public Proveedores guardar(@RequestBody Proveedores proveedor) {
        serviceProveedores.guardar(proveedor);
        return proveedor;
    }
    @PutMapping("/proveedores")
    public Proveedores modificar(@RequestBody Proveedores proveedor) {
        serviceProveedores.modificar(proveedor);
        return proveedor;
    }
    @GetMapping("/proveedores/{id}")
    public Optional<Proveedores> buscarId(@PathVariable("id") String ruc_proveedor) {
        return serviceProveedores.buscarId(ruc_proveedor);
    }
    @DeleteMapping("/proveedores/{id}")
    public ResponseEntity<Map<String, String>> eliminar(@PathVariable("id") String ruc_proveedor){
        serviceProveedores.eliminar(ruc_proveedor);
        return ResponseEntity.ok(Collections.singletonMap("mensaje", "Proveedor eliminado"));
    }
}