package com.alexander.sistema_cerro_verde_backend.controller.ventas;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

import com.alexander.sistema_cerro_verde_backend.entity.ventas.Ventas;
import com.alexander.sistema_cerro_verde_backend.service.ventas.IVentaService;

@RestController
@RequestMapping("/cerro-verde")
@CrossOrigin("*")
public class VentaController {

    @Autowired
    private IVentaService ventaService;

    @RequestMapping("/venta")
    public List<Ventas> buscarTodos() {
        return ventaService.buscarTodos();
    }

    @RequestMapping("/venta/{id}")
    public Optional<Ventas> buscarPorId(@PathVariable Integer id) {
        return ventaService.buscarPorId(id);
    }

    @PostMapping("/venta")
    public Ventas guardar(@RequestBody Ventas venta) {
        ventaService.guardar(venta);
        return venta;
    }

    @PutMapping("/venta")
    public Ventas modificar(@RequestBody Ventas venta) {
        ventaService.modificar(venta);
        return venta;
    }

    @DeleteMapping("/venta/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        try {
            ventaService.eliminar(id);
            Map<String, String> response = new HashMap<>();
            response.put("mensaje", "Venta eliminado correctamente");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("mensaje", "No se pudo eliminar la venta");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/pdf/{id}")
    public ResponseEntity<byte[]> descargarComprobante(@PathVariable Integer id) {
        // Generar el PDF como arreglo de bytes
        byte[] pdfBytes = ventaService.generarPdf(id);

        // Configurar cabeceras HTTP
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);

        // Nombre del archivo dinámico según tipo
        String nombreArchivo = "comprobante_" + id + ".pdf";

        headers.setContentDisposition(ContentDisposition.attachment().filename(nombreArchivo).build());

        return ResponseEntity.ok()
                .headers(headers)
                .body(pdfBytes);
    }

}
