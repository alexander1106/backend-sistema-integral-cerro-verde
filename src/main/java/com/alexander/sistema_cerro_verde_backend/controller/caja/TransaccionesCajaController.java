package com.alexander.sistema_cerro_verde_backend.controller.caja;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alexander.sistema_cerro_verde_backend.entity.caja.Cajas;
import com.alexander.sistema_cerro_verde_backend.entity.caja.TransaccionesCaja;
import com.alexander.sistema_cerro_verde_backend.service.caja.CajasService;
import com.alexander.sistema_cerro_verde_backend.service.caja.TransaccionesCajaService;

@CrossOrigin("*")
@RestController
@RequestMapping("/cerro-verde/caja/transacciones")
public class TransaccionesCajaController {

    @Autowired
    private TransaccionesCajaService transaccionesCajaService;

    @Autowired
    private CajasService serviceCaja;

    @PostMapping("/guardar")
    public ResponseEntity<String> guardarTransaccion(@RequestBody TransaccionesCaja transaccion) {
        try {
            Optional<Cajas> cajaAbierta = serviceCaja.buscarCajaAperturada();
            if (cajaAbierta.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No hay una caja abierta para realizar la transacción.");
            }

            Cajas caja = cajaAbierta.get();

            if (transaccion.getTipo().getId() == 2 && transaccion.getMontoTransaccion() > caja.getSaldo()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("El monto del egreso no puede ser mayor al saldo actual de la caja (" + caja.getSaldo() + ")");
            }

            transaccion.setCaja(cajaAbierta.get());
            transaccion.setFechaHoraTransaccion(new Date());
            if (transaccion.getTipo().getId() == 1) {
                caja.setSaldo(caja.getSaldo() + transaccion.getMontoTransaccion());
            } else {
                caja.setSaldo(caja.getSaldo() - transaccion.getMontoTransaccion());
            }

            transaccionesCajaService.guardar(transaccion);
            serviceCaja.guardar(caja);
            return ResponseEntity.status(HttpStatus.CREATED).body("Transacción guardada exitosamente");

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransaccionesCaja> obtenerTransaccion(@PathVariable Integer id) {
        return transaccionesCajaService.encontrarId(id)
                .map(transaccion -> ResponseEntity.ok().body(transaccion))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/all")
    public ResponseEntity<List<TransaccionesCaja>> obtenerTodos() {
        return ResponseEntity.ok(transaccionesCajaService.buscarTodos());
    }

    @GetMapping
    public ResponseEntity<?> obtenerTransaccionesCajaActual() {
    Optional<Cajas> cajaActual = serviceCaja.buscarCajaAperturada();

    if (cajaActual.isEmpty()) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No hay una caja aperturada actualmente.");
    }

    List<TransaccionesCaja> transacciones = transaccionesCajaService.buscarPorCaja(cajaActual.get());
    return ResponseEntity.ok(transacciones);
    }
}