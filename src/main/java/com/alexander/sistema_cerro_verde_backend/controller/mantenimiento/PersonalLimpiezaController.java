package com.alexander.sistema_cerro_verde_backend.controller.mantenimiento;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alexander.sistema_cerro_verde_backend.entity.mantenimiento.PersonalLimpieza;
import com.alexander.sistema_cerro_verde_backend.service.mantenimiento.jpa.PersonalLimpiezaService;



@RestController
@RequestMapping("/cerro-verde/personallimpieza")
@CrossOrigin("*")
public class PersonalLimpiezaController {
    @Autowired
    private PersonalLimpiezaService servicePersonalLimpieza;
    
    @GetMapping("/ver") //Ver
    public List<PersonalLimpieza> buscarTodos() {
        return servicePersonalLimpieza.buscarTodos();
    }

    @GetMapping("/personallimpieza/{id}") //Ver por Id
    public Optional<PersonalLimpieza> buscarPorId(@PathVariable Integer id){
        return servicePersonalLimpieza.buscarPorId(id);
    }

    @PostMapping("/registrar") //Registrar
    public PersonalLimpieza registrar(@RequestBody PersonalLimpieza personallimpieza) {
        servicePersonalLimpieza.registrar(personallimpieza);
        return personallimpieza;
    }

    @PutMapping("/actualizar/{id}") //Actualizar
    public void actualizar (@PathVariable Integer id, @RequestBody PersonalLimpieza personallimpieza){
        servicePersonalLimpieza.actualizar(id, personallimpieza);
    }

    @DeleteMapping("/eliminar/{id}") //Eliminar
    public void eliminarPorId (@PathVariable Integer id){
        servicePersonalLimpieza.eliminarPorId(id);
    }

}