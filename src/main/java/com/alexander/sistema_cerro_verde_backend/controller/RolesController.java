package com.alexander.sistema_cerro_verde_backend.controller;
import java.util.List;

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

import com.alexander.sistema_cerro_verde_backend.entity.Roles;
import com.alexander.sistema_cerro_verde_backend.service.IRolesService;

@RestController
@RequestMapping("/cerro-verde")
@CrossOrigin(origins = "*") // Para permitir peticiones desde el frontend (ajusta según sea necesario)
public class RolesController {

    @Autowired
    private IRolesService rolesService;

    @GetMapping("/roles/")
    public List<Roles> obtenerTodosLosPermisos() {
        return rolesService.obtenerTodosLosRoles();
    }

    @PutMapping("/roles/")
    public ResponseEntity<Roles> actualizarPregunta(@RequestBody Roles rol){
        return ResponseEntity.ok(rolesService.actualizarRol(rol));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Roles> obtenerRol(@PathVariable Integer id) {
        Roles rol = rolesService.obtenerRolPorId(id);
        if (rol == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(rol);
    }

    @PostMapping("/roles/")
    public ResponseEntity<Roles> crearRol(@RequestBody Roles rol) {
        return ResponseEntity.ok(rolesService.crearRol(rol));
    }
    
   
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarRol(@PathVariable Integer id) {
        Roles existente = rolesService.obtenerRolPorId(id);
        if (existente == null) {
            return ResponseEntity.notFound().build();
        }
        rolesService.eliminarRol(id);
        return ResponseEntity.noContent().build();
    }
}