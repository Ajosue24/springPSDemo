package com.proasecal.web.repository.parametricas;


import com.proasecal.web.entity.parametricas.Pais;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PaisRepository extends CrudRepository<Pais,Long> {

    List<Pais> findByNombrePaisContainingIgnoreCase(String nombrePais);
}
