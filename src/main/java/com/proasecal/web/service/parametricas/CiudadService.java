package com.proasecal.web.service.parametricas;

import com.proasecal.web.repository.parametricas.CiudadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class CiudadService {

    @Autowired
    CiudadRepository ciudadRepository;
}
