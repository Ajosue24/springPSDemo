package com.proasecal.web.repository.parametricas;


import com.proasecal.web.entity.parametricas.Departamento;
import com.proasecal.web.entity.parametricas.Pais;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DepartamentoRepository extends CrudRepository<Departamento,Long> {

    List<Departamento> findDepartamentoByIdPais(Pais pais);
}
