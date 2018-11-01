package com.proasecal.web.service.seguridad;

import com.proasecal.web.entity.seguridad.Modulos;
import com.proasecal.web.repository.seguridad.ModuloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class ModuloService {

    @Autowired
    ModuloRepository moduloRepository;

   public List<Modulos> obtenerModulos(){
        return (List<Modulos>)moduloRepository.findAll();
    }

    public Modulos buscarModuloPorId(Long id){
      return moduloRepository.findById(id).get();
    }

    public void guardarModulos(Modulos modulos){
       moduloRepository.save(modulos);
    }
}
