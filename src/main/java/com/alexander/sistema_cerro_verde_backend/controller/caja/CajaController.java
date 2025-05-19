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
    public ResponseEntity<?> aperturarCaja() {
        Usuarios usuario = getUsuarioAutenticado();
        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    
        Optional<Cajas> cajaExistenteOpt = serviceCaja.buscarCajaPorUsuario(usuario);
    
        if (cajaExistenteOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe caja asignada al usuario.");
        }
    
        Cajas caja = cajaExistenteOpt.get();
    
        if ("abierta".equalsIgnoreCase(caja.getEstadoCaja())) {
            return ResponseEntity.badRequest().body("La caja ya está aperturada.");
        }
    
        // Asumimos que saldoFisico fue actualizado correctamente al cerrar
        if (caja.getSaldoFisico() == null) {
            return ResponseEntity.badRequest().body("No se puede aperturar porque el saldo físico anterior es nulo.");
        }
    
        caja.setEstadoCaja("abierta");
        caja.setFechaApertura(new Date());
        caja.setMontoApertura(caja.getSaldoFisico());
        caja.setSaldoFisico(caja.getSaldoFisico()); // reinicia el saldo con base al saldo físico
    
        return ResponseEntity.ok(serviceCaja.guardar(caja));
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
        caja.setSaldoFisico(montoCierre); // ⬅️ ESTO ES CLAVE
        caja.setFechaCierre(new Date());
        caja.setEstadoCaja("cerrada");
        caja.setUsuarioCierre(usuario);
    
        return ResponseEntity.ok(serviceCaja.guardar(caja));
    }    
    
}
