package com.alexander.sistema_cerro_verde_backend.service.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alexander.sistema_cerro_verde_backend.entity.Permisos;
import com.alexander.sistema_cerro_verde_backend.entity.Submodulo;
import com.alexander.sistema_cerro_verde_backend.repository.PermisosRepository;
import com.alexander.sistema_cerro_verde_backend.repository.SubModuloRepository;
import com.alexander.sistema_cerro_verde_backend.service.IPermisosService;

@Service
public class PermisosService implements IPermisosService{

    @Autowired
    private SubModuloRepository subModuloRepository;

    @Autowired
    private PermisosRepository permisosRepository;
    
    @Override
    public List<Permisos> obtenerTodosLosPermisos() {
        return permisosRepository.findAll();
    }

    @Override
    public Permisos editarPermiso(Permisos permisos) {
        return permisosRepository.save(permisos);
        
    }

    @Override
    public Permisos obtenerPermiso(Integer permisosId) {
        return permisosRepository.findById(permisosId).get();
    }

    @Override
    public Permisos crearPermiso(Permisos permiso) {
        Integer idModulo = permiso.getSubModulo().getIdSubModulo();
        Submodulo modulo = subModuloRepository.findById(idModulo).orElse(null);
        if (modulo != null) {
            permiso.setSubModulo(modulo);// Asocia el Modulo completo
            return permisosRepository.save(permiso);
        } else {
            throw new RuntimeException("Sub modulo no encontrado");
        }
    }

    @Override
    public void eliminarPermiso(Integer idPermiso) {
        Permisos permisos = new Permisos();
        permisos.setIdPermiso(idPermiso);
        permisosRepository.delete(permisos);
    }

    @Override
    public List<Permisos> obtenerPermisosPorModulo(Integer idModulo) {
        return permisosRepository.findBySubModulo_Modulo_IdModulo(idModulo);
    }
}