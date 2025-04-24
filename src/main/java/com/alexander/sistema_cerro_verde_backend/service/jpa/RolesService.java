package com.alexander.sistema_cerro_verde_backend.service.jpa;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alexander.sistema_cerro_verde_backend.entity.Permisos;
import com.alexander.sistema_cerro_verde_backend.entity.Roles;
import com.alexander.sistema_cerro_verde_backend.entity.RolesPermisos;
import com.alexander.sistema_cerro_verde_backend.repository.PermisosRepository;
import com.alexander.sistema_cerro_verde_backend.repository.RolesRepository;
import com.alexander.sistema_cerro_verde_backend.service.IRolesService;

@Service
public class RolesService implements IRolesService {

    @Autowired
    private RolesRepository rolesRepository;
    @Autowired
    private PermisosRepository permisosRepository;
    
    @Override
    public Roles crearRol(Roles rol) {
        System.out.println("Lista completa de rolesPermisos:");
        System.out.println(rol.getRolesPermisos());

        Set<RolesPermisos> rolesPermisosSet = new HashSet<>();
    
        System.out.println("Permisos recibidos:");
        for (RolesPermisos rp : rol.getRolesPermisos()) {
            if (rp.getPermisos() != null) {
                Integer idPermiso = rp.getPermisos().getIdPermiso();
                Permisos permiso = permisosRepository.findById(idPermiso).orElse(null);
    
                if (permiso != null) {
                    rp.setPermisos(permiso);
                    rp.setRoles(rol); // Establecer la relación inversa
                    rolesPermisosSet.add(rp);
                    System.out.println(" -> ID Permiso: " + permiso.getIdPermiso());
                } else {
                    System.out.println(" -> Permiso con ID " + idPermiso + " no encontrado");
                    throw new RuntimeException("Permiso con ID " + idPermiso + " no encontrado");
                }
            } else {
                System.out.println(" -> Permiso nulo");
                throw new RuntimeException("Permiso nulo recibido en rolesPermisos");
            }
        }
    
        rol.setRolesPermisos(rolesPermisosSet);
        return rolesRepository.save(rol);
    }
    
    @Override
    public Roles actualizarRol(Roles rol){
        return rolesRepository.save(rol);
    }

    @Override
    public Roles obtenerRolPorId(Integer idRol) {
        return rolesRepository.findById(idRol).get();
    }

    @Override
    public List<Roles> obtenerTodosLosRoles() {
        return rolesRepository.findAll();
    }


    @Override
    public void eliminarRol(Integer idRol) {
        if (!rolesRepository.existsById(idRol)) {
            throw new RuntimeException("El rol con ID " + idRol + " no existe");
        }
        rolesRepository.deleteById(idRol);
    }

    public Roles buscarPorNombre(String rol) {
        return rolesRepository.findByNombreRol(rol).orElse(null);
    }

    @Override
    public List<Permisos> obtenerPermisosPorRol(Integer idRol) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obtenerPermisosPorRol'");
    }

    @Override
    public Roles asignarPermisosARol(Integer idRol, List<Integer> idPermisos) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'asignarPermisosARol'");
    }
}
