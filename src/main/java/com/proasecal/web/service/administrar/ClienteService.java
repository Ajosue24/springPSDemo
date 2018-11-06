package com.proasecal.web.service.administrar;


import com.proasecal.web.entity.administrar.Clientes;
import com.proasecal.web.repository.administrar.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    public Clientes obtenerClienteByID(long idCliente) {
        return clienteRepository.findById(idCliente).get();
    }

    public List<Clientes> getAllClientes() {
        return (List<Clientes>) clienteRepository.findAll();
    }

    public void guardarActualizarCliente(Clientes clientes) {
        clienteRepository.save(clientes);
    }

    public Clientes obtenerPorDocumento(String docCliente) {
        Clientes clientes = new Clientes();
        clientes = clienteRepository.findClienteByNumeroIdentificacionClienteEquals(docCliente);
        return clientes;
    }
}
