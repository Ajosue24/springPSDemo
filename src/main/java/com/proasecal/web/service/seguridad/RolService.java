package com.proasecal.web.service.seguridad;


import com.proasecal.web.entity.seguridad.Roles;
import com.proasecal.web.repository.seguridad.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class RolService {

    @Autowired
    RolRepository rolesRepository;

    public List<Roles> obtenerListaRoles(){
        return (List<Roles>)rolesRepository.findAll();
    }

    public Roles obtenerRol(Long id){
       return rolesRepository.findById(id).get();
    }

    public void guardarRol(Roles roles){
        rolesRepository.save(roles);
    }

    public Boolean validarSiExisteRol(String nombreRol){
        return rolesRepository.existsByNombreRol(nombreRol);
    }

    public Roles obtenerPorNombreRol(String nombreRol){
        return rolesRepository.findByNombreRol(nombreRol);
    }

    public Boolean validarSiExisteRolParaLab(){
        return rolesRepository.existsByCodigoProasecalIsTrue();
    }



}
