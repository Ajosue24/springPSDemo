
package com.proasecal.web.service.parametricas;

import com.proasecal.web.entity.parametricas.Ciudad;
import com.proasecal.web.entity.parametricas.Departamentos;
import com.proasecal.web.entity.parametricas.Pais;
import com.proasecal.web.repository.parametricas.CiudadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class CiudadService {

    @Autowired
    CiudadRepository ciudadRepository;


    public List<Ciudad> obtenerCiudadxDepartamento(Departamentos departamentos){
        return ciudadRepository.findCiudadByIdDepartamentos(departamentos);
}
}

