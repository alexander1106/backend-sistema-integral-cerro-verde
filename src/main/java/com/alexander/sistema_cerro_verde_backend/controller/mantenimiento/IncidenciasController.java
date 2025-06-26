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

import com.alexander.sistema_cerro_verde_backend.entity.mantenimiento.Incidencias;
import com.alexander.sistema_cerro_verde_backend.service.SmsService;
import com.alexander.sistema_cerro_verde_backend.service.mantenimiento.jpa.IncidenciasService;



@RestController
@RequestMapping("/cerro-verde/incidencias")
@CrossOrigin("*")
public class IncidenciasController {
    @Autowired
    private IncidenciasService serviceIncidencias;

    @Autowired
    private SmsService mensajeService;
    
    @GetMapping("/ver") //Ver
    public List<Incidencias> buscarTodos() {
        return serviceIncidencias.buscarTodos();
    }

    @GetMapping("/incidencias/{id}") //Ver por Id
    public Optional<Incidencias> buscarPorId(@PathVariable Integer id){
        return serviceIncidencias.buscarPorId(id);
    }

    @PostMapping("/registrar") //Registrar
    public Incidencias registrar(@RequestBody Incidencias incidencias) {
        incidencias.setEstado_incidencia("pendiente");
        serviceIncidencias.registrar(incidencias);

        String ubicacion = "";

        if (incidencias.getHabitacion() != null) {
            ubicacion = "habitación: " + incidencias.getHabitacion().getNumero() 
                        + " - " + incidencias.getHabitacion().getPiso();
        } else if (incidencias.getArea() != null) {
            ubicacion = "Área: " + incidencias.getArea().getNombre(); 
        } else if (incidencias.getSalon() != null) {
            ubicacion = "Salón: " + incidencias.getSalon().getNombre(); 
        }

        String mensaje = "\n Incidencia en "+ubicacion+
                        "\n TIPO: "+incidencias.getTipoIncidencia();        

        //mensajeService.enviarSms(mensaje);
        return incidencias;
    }

    @PutMapping("/actualizar/{id}") //Actualizar
    public void actualizar (@PathVariable Integer id, @RequestBody Incidencias incidencias){
        serviceIncidencias.actualizar(id, incidencias);
    }

    @DeleteMapping("/eliminar/{id}") //Eliminar
    public void eliminarPorId (@PathVariable Integer id){
        serviceIncidencias.eliminarPorId(id);
    }

}