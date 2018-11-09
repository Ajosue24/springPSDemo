package com.proasecal.web.repository.parametricas;


import com.proasecal.web.entity.parametricas.Ciudad;
import com.proasecal.web.entity.parametricas.Departamentos;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface CiudadRepository extends CrudRepository<Ciudad,Long> {

    List<Ciudad> findCiudadByIdDepartamentos(Departamentos departamentos);

}

