
package com.proasecal.web.service.parametricas;


import com.proasecal.web.entity.parametricas.Departamentos;
import com.proasecal.web.entity.parametricas.Pais;
import com.proasecal.web.repository.parametricas.DepartamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class DepartamentoService {


    @Autowired
    DepartamentoRepository departamentoRepository;

    public List<Departamentos> obtenerDepartamentoXPais(Pais pais){
        return departamentoRepository.findDepartamentosByIdPais(pais);
    }



}

