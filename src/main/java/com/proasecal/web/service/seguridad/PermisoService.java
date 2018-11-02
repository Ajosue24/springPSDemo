package com.proasecal.web.service.seguridad;

import com.proasecal.web.entity.seguridad.Modulos;
import com.proasecal.web.entity.seguridad.Permisos;
import com.proasecal.web.entity.seguridad.Roles;
import com.proasecal.web.repository.seguridad.PermisoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class PermisoService {

    @Autowired
    PermisoRepository permisoRepository;

    public List<Permisos> obtenerPermisosPorModulos(Modulos modulos){
        return permisoRepository.findAllByIdModulos(modulos);
    }

    public Permisos obtenerPermiso(Long id){
        return permisoRepository.findById(id).get();
    }

    public void guardarPermiso(Permisos permisos){
        permisoRepository.save(permisos);
    }

    public List<Permisos> obtenerPermisosXRol(Roles roles){
        return permisoRepository.findAllByListRoles(roles);
    }
    
    public List<Permisos> all(){
    	return permisoRepository.findAll();
    }

}
