package com.example.demo.model.Service;

import com.example.demo.model.Dao.IClienteDao;
import com.example.demo.model.Entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClienteServiceImpl implements IClienteService{

    //Es como una fachada los Servicie que viene del patron de diseño Fascade
    // Copiamos los mismos metodos de nuestro DAO a Service
    //Y aca heramos el Service y lo pintamos con el Dao (en el Dao se encuentra la logica de negocio con JPA)
    //Por aca simplemente lo fachamos y usamos en el Controller el Servicio mas no el Dao
    //Dentro de un servicio podemos encontrar muchos daos


    @Autowired
    public IClienteDao clienteDao;

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> findAll() {
        return (List<Cliente>)clienteDao.findAll();
    }

    @Override
    @Transactional  //sin readonly por que es de escritura
    public void save(Cliente cliente) {
        clienteDao.save(cliente);
    }

    @Override
    @Transactional(readOnly = true) //por es cslo consulta
    public Cliente findOne(Long id) {
        return clienteDao.findById(id).orElse(null);
    }

    @Override
    @Transactional //significa que es un metodo que actualiza una tabla , por eso al guardar y eliminar lo utilizamos
    public void delete(Long id) {
        clienteDao.deleteById(id);
    }
}
