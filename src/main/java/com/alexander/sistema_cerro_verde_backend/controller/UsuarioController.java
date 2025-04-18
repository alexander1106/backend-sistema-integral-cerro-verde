package com.alexander.sistema_cerro_verde_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alexander.sistema_cerro_verde_backend.entity.Usuarios;
import com.alexander.sistema_cerro_verde_backend.service.IUsuariosService;
import com.alexander.sistema_cerro_verde_backend.service.jpa.UsuariosService;

@RestController
@RequestMapping("/cerro-verde/usuarios")
@CrossOrigin("*")
public class UsuarioController {

    @Autowired
    private IUsuariosService usuarioService;

    @Autowired
    private UsuariosService usuarioServiceImpl;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/")
    public List<Usuarios> listarUsuarios(){
        return usuarioServiceImpl.obtenerTodosUsuarios();
    }

    @PostMapping("/")
    public Usuarios guardUsuario(@RequestBody Usuarios usuario) throws Exception {
        usuario.setPassword(this.bCryptPasswordEncoder.encode(usuario.getPassword()));
        return usuarioService.guardarUsuario(usuario);
    }
    
    @GetMapping("/{usuarioId}")
    public Usuarios obtenerUsuarioPorId(@PathVariable("usuarioId") Integer usuarioId) throws Exception {
        return usuarioServiceImpl.obtenerUsuarioPorId(usuarioId);
    }

    @GetMapping("/username/{usuario}")
    public Usuarios obtenerUsuario(@PathVariable("usuario") String usuario) {
        return usuarioServiceImpl.obtenerUsuario(usuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuarios> actualizarUsuario(@PathVariable Integer id, @RequestBody Usuarios usuarioActualizado)  throws Exception{
        usuarioActualizado.setIdUsuarios(id);
        Usuarios actualizado = usuarioService.actualizarUsuario(usuarioActualizado);
        return ResponseEntity.ok(actualizado);
    }
  
    @DeleteMapping("/{usuarioId}")
    public void eliminarUsuario(@PathVariable("usuarioId") Integer usuarioId){
        usuarioServiceImpl.eliminarUsuario(usuarioId);
    }


}
