package com.alexander.sistema_cerro_verde_backend.service.jpa.caja;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alexander.sistema_cerro_verde_backend.entity.caja.TransaccionesCaja;
import com.alexander.sistema_cerro_verde_backend.repository.caja.TransaccionesCajaRepository;
import com.alexander.sistema_cerro_verde_backend.service.caja.TransaccionesCajaService;

@Service
public class TransaccionesCajaServiceImpl implements TransaccionesCajaService {

    @Autowired
    private TransaccionesCajaRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<TransaccionesCaja> buscarTodos() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TransaccionesCaja> encontrarId(Integer id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public TransaccionesCaja guardar(TransaccionesCaja transaccion) {
        return repository.save(transaccion);
    }

    @Override
    @Transactional
    public void eliminarId(Integer id) {
        repository.deleteById(id);
    }

}
