package com.example.demo.model.Dao;

import java.util.List;

import com.example.demo.model.Entity.Cliente;
import org.springframework.data.repository.CrudRepository;

public interface IClienteDao extends CrudRepository<Cliente,Long> {

	//Con crudrepository viene todo implemetado por detras

	/*List<Cliente> findAll();
	public void save(Cliente cliente);
	public Cliente findOne(Long id);
	public void delete(Long id);*/
}
