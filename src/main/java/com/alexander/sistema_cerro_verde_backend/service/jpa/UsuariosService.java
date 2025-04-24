package com.alexander.sistema_cerro_verde_backend.service.jpa;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alexander.sistema_cerro_verde_backend.entity.Permisos;
import com.alexander.sistema_cerro_verde_backend.entity.Roles;
import com.alexander.sistema_cerro_verde_backend.entity.RolesPermisos;
import com.alexander.sistema_cerro_verde_backend.entity.Usuarios;
import com.alexander.sistema_cerro_verde_backend.excepciones.CorreoYaRegistradoException;
import com.alexander.sistema_cerro_verde_backend.repository.PermisosRepository;
import com.alexander.sistema_cerro_verde_backend.repository.RolesPermisosRepository;
import com.alexander.sistema_cerro_verde_backend.repository.RolesRepository;
import com.alexander.sistema_cerro_verde_backend.repository.UsuariosRepository;
import com.alexander.sistema_cerro_verde_backend.service.IUsuariosService;

@Service
public class UsuariosService implements IUsuariosService {

    @Autowired
    private UsuariosRepository usuariosRepository;

    @Autowired
    private RolesRepository rolesRepository; 

    @Autowired
    private RolesPermisosRepository rolesPermisosRepository; 
    
    @Autowired
    private PermisosRepository permisosRepository; 
    @Override
    public Usuarios guardarUsuario(Usuarios usuario) throws Exception {
        if (usuariosRepository.existsByEmail(usuario.getEmail())) {
            throw new CorreoYaRegistradoException();  // Se lanza la excepción personalizada
        }
        Set<RolesPermisos> permisosDelJson = usuario.getRol().getRolesPermisos();
        Integer idRol = usuario.getRol().getIdRol();
        Roles rol = rolesRepository.findById(idRol).orElse(null); 
        if (rol == null) {
            throw new RuntimeException("Rol no encontrado");
        }
        usuario.setRol(rol);
        Usuarios usuarioGuardado = usuariosRepository.save(usuario);
        // 🔸 Usa la lista que venía del JSON, no la del rol de BD
        if (permisosDelJson != null) {
            Set<RolesPermisos> nuevosPermisos = new HashSet<>();

            for (RolesPermisos rp : permisosDelJson) {
                Permisos permiso = permisosRepository.findById(rp.getPermisos().getIdPermiso()).orElse(null);
                if (permiso != null) {
                    RolesPermisos nuevo = new RolesPermisos();
                    nuevo.setRoles(rol);
                    nuevo.setPermisos(permiso);
                    nuevosPermisos.add(nuevo);
                    rolesPermisosRepository.save(nuevo);
                }
            }
            rol.setRolesPermisos(nuevosPermisos); // Si quieres mantener la asociación en memoria
        }
        return usuarioGuardado;
    }

    
    @Override
    public Usuarios obtenerUsuario(String username) {
        return usuariosRepository.findByUsername(username);
    }
   
    @Override
    public Usuarios obtenerUsuarioPorId(Integer id) throws Exception {
        return usuariosRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminarUsuario(Integer id) {
        usuariosRepository.deleteById(id);
    }

    @Override
    public Usuarios actualizarUsuario(Usuarios usuarioActualizado) throws Exception {
        return usuariosRepository.save(usuarioActualizado);
    }

    @Override
    public List<Usuarios> obtenerTodosUsuarios() {
        return usuariosRepository.findAll();
    }

    @Override
    public boolean existeUsuario(String username) {
        return usuariosRepository.findByUsername(username) != null;
    }

    @Override
    public void cambiarContraseña(Integer id, String nuevaContraseña) throws Exception {
        Optional<Usuarios> optional = usuariosRepository.findById(id);
        if (optional.isPresent()) {
            Usuarios usuario = optional.get();
            usuario.setPassword(nuevaContraseña);
            usuariosRepository.save(usuario);
        } else {
            throw new Exception("Usuario no encontrado para cambiar contraseña");
        }
    }


    @Override
    public Usuarios guardarUsuarioConPermisos(Usuarios usuario) throws Exception {
        Integer idRol = usuario.getRol().getIdRol();
        Roles rol = rolesRepository.findById(idRol).orElse(null);
        
        if (rol == null) {
            throw new RuntimeException("Rol no encontrado");
        }

        usuario.setRol(rol);
        Usuarios usuarioGuardado = usuariosRepository.save(usuario);
        if (usuario.getRol().getRolesPermisos() != null) {
            for (RolesPermisos permiso : usuario.getRol().getRolesPermisos()) {
                // Asignamos el rol al permiso para asegurarnos
                permiso.setRoles(rol);
                rolesPermisosRepository.save(permiso);
            }
        }

        return usuarioGuardado;
    }
}
