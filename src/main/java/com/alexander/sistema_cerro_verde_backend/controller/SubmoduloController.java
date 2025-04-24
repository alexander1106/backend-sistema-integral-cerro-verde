package com.alexander.sistema_cerro_verde_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alexander.sistema_cerro_verde_backend.entity.Submodulo;
import com.alexander.sistema_cerro_verde_backend.service.jpa.SubmoduloService;

@RestController
@RequestMapping("/cerro-verde")
@CrossOrigin("*") 
public class SubmoduloController {

    @Autowired
    private SubmoduloService submoduloService;

    @GetMapping("/submodulos/modulos/{idModulo}")
    public List<Submodulo> obtenerPorModulo(@PathVariable Integer idModulo) {
        return submoduloService.obtenerSubModulosPorModuloId(idModulo);
    }

    @GetMapping("/submodulos/")
    public List<Submodulo> obtenerTodos() {
        return submoduloService.obtenerTodosLosSubModulos();
    }
    
    @PostMapping("/sub-modulos/")
    public ResponseEntity<Submodulo> crearRol(@RequestBody Submodulo subModulo) {
        return ResponseEntity.ok(submoduloService.guardarSubModulo(subModulo));
    }
 
}
