package com.alexander.sistema_cerro_verde_backend.controller.reportes;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alexander.sistema_cerro_verde_backend.entity.reportes.CajaResumenDTO;
import com.alexander.sistema_cerro_verde_backend.service.reportes.jpa.CajaReporteService;

@RestController
@RequestMapping("/cerro-verde/reportes/caja")
@CrossOrigin(origins = "http://localhost:4200")
public class CajaReporteController {

    private final CajaReporteService service;

    public CajaReporteController(CajaReporteService service) {
        this.service = service;
    }

    @GetMapping(path = "/resumen", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CajaResumenDTO> getResumen(
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate desde,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate hasta,
        @RequestParam("tipos") String tiposCsv
    ) {
        List<String> tipos = Arrays.asList(tiposCsv.split(","));
        LocalDateTime from = desde.atStartOfDay();
        LocalDateTime to   = hasta.atTime(LocalTime.MAX);
        return service.obtenerResumenCaja(from, to, tipos);
    }

    @GetMapping("/resumen/pdf")
    public ResponseEntity<byte[]> getResumenPdf(
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate desde,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate hasta,
        @RequestParam("tipos") String tiposCsv
    ) throws IOException {
        List<String> tipos = Arrays.asList(tiposCsv.split(","));
        LocalDateTime from = desde.atStartOfDay();
        LocalDateTime to   = hasta.atTime(LocalTime.MAX);

        ByteArrayInputStream bis = service.generarPdfResumenCaja(from, to, tipos);
        byte[] pdf = bis.readAllBytes();

        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"resumen_caja.pdf\"")
            .contentType(MediaType.APPLICATION_PDF)
            .body(pdf);
    }

    @GetMapping("/resumen/excel")
    public ResponseEntity<byte[]> getResumenExcel(
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate desde,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate hasta,
        @RequestParam("tipos") String tiposCsv
    ) {
        List<String> tipos = Arrays.asList(tiposCsv.split(","));
        LocalDateTime from = desde.atStartOfDay();
        LocalDateTime to   = hasta.atTime(LocalTime.MAX);

        byte[] xlsx = service.generarExcelResumenCaja(from, to, tipos);

        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"resumen_caja.xlsx\"")
            .contentType(MediaType.parseMediaType(
                "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
            .body(xlsx);
    }

}
