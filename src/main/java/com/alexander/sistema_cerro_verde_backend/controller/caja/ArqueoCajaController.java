package com.alexander.sistema_cerro_verde_backend.controller.caja;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alexander.sistema_cerro_verde_backend.entity.caja.ArqueosCaja;
import com.alexander.sistema_cerro_verde_backend.entity.caja.Cajas;
import com.alexander.sistema_cerro_verde_backend.entity.caja.DenominacionDinero;
import com.alexander.sistema_cerro_verde_backend.entity.caja.DetalleArqueo;
import com.alexander.sistema_cerro_verde_backend.service.caja.ArqueosCajaService;
import com.alexander.sistema_cerro_verde_backend.service.caja.CajasService;
import com.alexander.sistema_cerro_verde_backend.service.caja.DenominacionDineroService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@CrossOrigin("*")
@RestController
@RequestMapping("/cerro-verde/caja/arqueo")
public class ArqueoCajaController {

    @Autowired
    private CajasService cajasService;

    @Autowired
    private ArqueosCajaService arqueosCajaService;

    @Autowired
    private DenominacionDineroService denominacionDineroService;

    @GetMapping("/denominaciones")
    public ResponseEntity<List<DenominacionDinero>> obtenerDenominaciones() {
        List<DenominacionDinero> denominaciones = denominacionDineroService.buscarTodos();
        return ResponseEntity.ok(denominaciones);
    }

    @GetMapping
    public ResponseEntity<?> verificarExistenciaArqueo() {
            Optional<Cajas> cajaOpt = cajasService.buscarCajaAperturada();

        if (cajaOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay caja aperturada.");
        }
        
        else {
            Optional<ArqueosCaja> arqueoExistencia = arqueosCajaService.buscarPorCaja(cajaOpt.get());

            if (arqueoExistencia.isPresent()) {
                return ResponseEntity.ok(arqueoExistencia.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap("mensaje", "Arqueo no existe"));
            }
        }

    }
    
    
    @PostMapping("/crear")
    public ResponseEntity<?> realizarArqueo(@RequestBody ArqueosCaja arqueo) {
    
        Optional<Cajas> cajaAperturadaOpt = cajasService.buscarCajaAperturada();
        if (cajaAperturadaOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("No hay una caja aperturada actualmente.");
        }
    
        Cajas cajaAperturada = cajaAperturadaOpt.get();
        arqueo.setCaja(cajaAperturada); // Asociar la caja
    
        // Asociar cada detalle con el arqueo (para la relación bidireccional)
        if (arqueo.getDetalles() != null) {
            for (DetalleArqueo detalle : arqueo.getDetalles()) {
                detalle.setArqueo(arqueo);
            }
        }
    
        ArqueosCaja arqueoGuardado = arqueosCajaService.guardar(arqueo);
    
        return ResponseEntity.ok(arqueoGuardado);
    }
    

    @GetMapping("/{id}")
    public ResponseEntity<ArqueosCaja> obtenerArqueoPorId(@PathVariable Integer id) {
        
        Optional<ArqueosCaja> arqueo = arqueosCajaService.buscarId(id);
        
        if (arqueo.isPresent()) {
            ArqueosCaja arqueoExistente = arqueo.get();
            return ResponseEntity.ok(arqueoExistente);
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArqueosCaja> actualizarArqueo(@PathVariable Integer id, @RequestBody ArqueosCaja arqueoActualizado) {
        Optional<ArqueosCaja> arqueoOpt = arqueosCajaService.buscarId(id);
    
        if (arqueoOpt.isPresent()) {
            ArqueosCaja arqueoExistente = arqueoOpt.get();
    
            // Actualizar observaciones y caja
            arqueoExistente.setObservaciones(arqueoActualizado.getObservaciones());
    
            // Limpiar detalles antiguos
            arqueoExistente.getDetalles().clear();
    
            // Agregar los nuevos detalles
            for (DetalleArqueo nuevoDetalle : arqueoActualizado.getDetalles()) {
                nuevoDetalle.setArqueo(arqueoExistente); // establecer la relación
                arqueoExistente.getDetalles().add(nuevoDetalle);
            }
    
            ArqueosCaja actualizado = arqueosCajaService.guardar(arqueoExistente);
            return ResponseEntity.ok(actualizado);
        }
    
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/caja/{idCaja}")
    public ResponseEntity<?> obtenerArqueoPorCaja(@PathVariable Integer idCaja) {
    Optional<Cajas> caja = cajasService.buscarId(idCaja);
    if (caja.isPresent()) {
        Optional<ArqueosCaja> arqueo = arqueosCajaService.buscarPorCaja(caja.get());
        return arqueo.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    return ResponseEntity.notFound().build();
}
    
}
    
