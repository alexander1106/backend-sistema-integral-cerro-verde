package com.alexander.sistema_cerro_verde_backend.controller.caja;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.alexander.sistema_cerro_verde_backend.entity.caja.Cajas;
import com.alexander.sistema_cerro_verde_backend.entity.seguridad.Usuarios;
import com.alexander.sistema_cerro_verde_backend.repository.seguridad.UsuariosRepository;
import com.alexander.sistema_cerro_verde_backend.service.caja.CajasService;

@CrossOrigin("*")
@RestController
@RequestMapping("/cerro-verde/caja")
public class CajaController {

    @Autowired
    private CajasService serviceCaja;

    @Autowired
    private UsuariosRepository usuarioRepository;

    private Usuarios getUsuarioAutenticado() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        return usuarioRepository.findByUsername(username);
    }

    @GetMapping
    public ResponseEntity<?> verificarEstadoCaja() {
        Usuarios usuario = getUsuarioAutenticado();
        Optional<Cajas> cajaAbierta = serviceCaja.buscarCajaAperturadaPorUsuario(usuario);

        if (cajaAbierta.isPresent()) {
            return ResponseEntity.ok(cajaAbierta.get());
        } else {
            return ResponseEntity.ok("no_aperturada");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerCajaPorId(@PathVariable Integer id) {
        Optional<Cajas> caja = serviceCaja.buscarId(id);
        Usuarios usuario = getUsuarioAutenticado();

        if (caja.isPresent() && caja.get().getUsuario().getIdUsuario().equals(usuario.getIdUsuario())) {
            return ResponseEntity.ok(caja.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Caja no encontrada o no autorizada");
        }
    }

    @PostMapping("/aperturar")
    public ResponseEntity<?> aperturarCaja(@RequestBody Cajas cajaRequest) {
        Usuarios usuario = getUsuarioAutenticado();
        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Optional<Cajas> cajaExistente = serviceCaja.buscarCajaPorUsuario(usuario);

        if (cajaExistente.isPresent()) {
            Cajas caja = cajaExistente.get();
            caja.setEstadoCaja("abierta");
            caja.setFechaApertura(new Date());
            return ResponseEntity.ok(serviceCaja.guardar(caja));
        }

        Cajas nuevaCaja = new Cajas();
        nuevaCaja.setMontoApertura(cajaRequest.getMontoApertura());
        nuevaCaja.setFechaApertura(new Date());
        nuevaCaja.setEstadoCaja("abierta");
        nuevaCaja.setUsuario(usuario);
        nuevaCaja.setSaldo(cajaRequest.getMontoApertura());

        return ResponseEntity.ok(serviceCaja.guardar(nuevaCaja));
    }

    @PostMapping("/cerrar")
    public ResponseEntity<?> cerrarCajaActual(@RequestBody Double montoCierre) {
        Usuarios usuario = getUsuarioAutenticado();
        Optional<Cajas> cajaAbierta = serviceCaja.buscarCajaAperturadaPorUsuario(usuario);

        if (cajaAbierta.isEmpty()) {
            return ResponseEntity.badRequest().body("No hay una caja aperturada.");
        }

        Cajas caja = cajaAbierta.get();
        caja.setMontoCierre(montoCierre);
        caja.setFechaCierre(new Date());
        caja.setEstadoCaja("cerrada");
        caja.setUsuarioCierre(usuario);

        return ResponseEntity.ok(serviceCaja.guardar(caja));
    }

    // Se eliminó el endpoint /historial global
}
