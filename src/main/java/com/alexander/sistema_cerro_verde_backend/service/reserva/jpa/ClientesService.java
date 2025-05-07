package com.alexander.sistema_cerro_verde_backend.service.reserva.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alexander.sistema_cerro_verde_backend.entity.reservas.Clientes;
import com.alexander.sistema_cerro_verde_backend.repository.reservas.ClientesRepository;
import com.alexander.sistema_cerro_verde_backend.service.reserva.IClientesService;

@Service
public class ClientesService implements IClientesService{

    @Autowired
    private ClientesRepository repoClientes;

    @Override
    public List<Clientes> buscarTodos() {
        return repoClientes.findAll();
    }

    @Override
    public Optional<Clientes> buscarPorId(Integer id){
        return repoClientes.findById(id);
    }

    @Override
    public void guardar(Clientes cliente){
        repoClientes.save(cliente);
    }

    @Override
    public void modificar(Clientes cliente){
        repoClientes.save(cliente);
    }

    @Override
    public void eliminar(Integer id){
        repoClientes.deleteById(id);
    }
}
