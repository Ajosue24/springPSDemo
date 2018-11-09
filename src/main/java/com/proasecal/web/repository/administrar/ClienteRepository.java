
package com.proasecal.web.repository.administrar;


import com.proasecal.web.entity.administrar.Clientes;
import org.springframework.data.repository.CrudRepository;

public interface ClienteRepository extends CrudRepository<Clientes,Long> {

    Clientes findClienteByNumeroIdentificacionClienteEquals(String idCliente);
}

