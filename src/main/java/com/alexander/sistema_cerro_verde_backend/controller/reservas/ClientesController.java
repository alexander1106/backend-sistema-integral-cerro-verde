package com.alexander.sistema_cerro_verde_backend.controller.reservas;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

import com.alexander.sistema_cerro_verde_backend.entity.reservas.Clientes;
import com.alexander.sistema_cerro_verde_backend.service.reserva.ApiCliente;
import com.alexander.sistema_cerro_verde_backend.service.reserva.jpa.ClientesService;


@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class ClientesController {

    @Autowired
    private ClientesService serviceClientes;
    @Autowired
    private ApiCliente api;

    @GetMapping("/clientes")
    public List<Clientes> buscarTodos() {
        return serviceClientes.buscarTodos();
    }
    
    @GetMapping("/clientes/{id}")
    public Optional<Clientes> buscarPorId(@PathVariable("id") Integer id) {
        return serviceClientes.buscarPorId(id);
    }

    @PostMapping("/clientes")
    public Clientes guardar(@RequestBody Clientes cliente) {
        serviceClientes.guardar(cliente);
        return cliente;
    }

    @PutMapping("/clientes")
    public Clientes modificar(@RequestBody Clientes cliente) {
        serviceClientes.modificar(cliente);
        return cliente;
    }

    @DeleteMapping("/clientes/{id}")
    public ResponseEntity<Map<String, String>> eliminar(@PathVariable("id") Integer id){
        serviceClientes.eliminar(id);
        return ResponseEntity.ok(Collections.singletonMap("mensaje", "Cliente eliminado"));
    }
    
    @GetMapping("/dni/{id}")
    public ResponseEntity<Map<String, String>> buscarDni(@PathVariable("id") String dni) {
        String resultado = api.consumirApi(dni);
        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("datos", resultado);
        System.out.println(resultado);
        return ResponseEntity.ok(respuesta);
    } 
}
