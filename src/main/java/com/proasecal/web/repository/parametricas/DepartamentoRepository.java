
package com.proasecal.web.repository.parametricas;


import com.proasecal.web.entity.parametricas.Departamentos;
import com.proasecal.web.entity.parametricas.Pais;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DepartamentoRepository extends CrudRepository<Departamentos,Long> {

    List<Departamentos> findDepartamentosByIdPais(Pais pais);
}

