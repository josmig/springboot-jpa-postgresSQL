package com.example.demo.model.Service;

import com.example.demo.model.Entity.Cliente;

import java.util.List;

public interface IClienteService {

    List<Cliente> findAll();
    public void save(Cliente cliente);
    public Cliente findOne(Long id);
    public void delete(Long id);
}
