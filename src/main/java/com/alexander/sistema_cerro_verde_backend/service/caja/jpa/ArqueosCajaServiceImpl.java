package com.alexander.sistema_cerro_verde_backend.service.caja.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alexander.sistema_cerro_verde_backend.entity.caja.ArqueosCaja;
import com.alexander.sistema_cerro_verde_backend.repository.caja.ArqueosCajaRepository;
import com.alexander.sistema_cerro_verde_backend.service.caja.ArqueosCajaService;

@Service
public class ArqueosCajaServiceImpl implements ArqueosCajaService {

    @Autowired
    private ArqueosCajaRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<ArqueosCaja> buscarTodos() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public ArqueosCaja guardar(ArqueosCaja arqueo) {
        return repository.save(arqueo);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ArqueosCaja> buscarId(Integer id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public void eliminarId(Integer id) {
        repository.deleteById(id);
    }

}
