package com.proasecal.web.service.parametricas;


import com.proasecal.web.entity.parametricas.Pais;
import com.proasecal.web.repository.parametricas.PaisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class PaisService {

    @Autowired
    PaisRepository paisRepository;


    public List<Pais> obtenerPaises(){
        return (List<Pais>) paisRepository.findAll();
    }
public void actualizaYGuardaPais(Pais pais){
        paisRepository.save(pais);
}

public Pais obtenerPaisEspecifico(long idPais){
      return  paisRepository.findById(idPais).get();
}

public List<Pais> filtrarPaisLike(String nombrePais){
       return  paisRepository.findByNombrePaisContainingIgnoreCase(nombrePais);
}

}
