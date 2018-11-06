package com.proasecal.web.controller.seguridad;

import com.proasecal.web.entity.seguridad.Modulos;
import com.proasecal.web.entity.seguridad.Permisos;
import com.proasecal.web.filter.AclFilter;
import com.proasecal.web.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class RolesAccess {

    private final Logger LOG = LoggerFactory.getLogger(RolesAccess.class);


    /**
     * Atributo Que indica si usuario puede ver el modulo
     */
    public static List<String> rolesPermitidos(String nombreModulo){
        Optional<List<Modulos>> collectModulos = Util.userModules();
        List<String> lista = new ArrayList<>();
        if(collectModulos.isPresent()) {
            Boolean isPermission = collectModulos.get().stream().anyMatch(modulos -> modulos.getNombreModulo().equals(nombreModulo));
            if(!isPermission) {
            }else {
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                authentication.getAuthorities().forEach(l->lista.add(l.getAuthority()));
                for(GrantedAuthority g :authentication.getAuthorities()){
                    lista.add(g.getAuthority());
                }
                return lista;
                //LOG.info("si tiene permisos para acceder a "+nombreModulo);
            }
        }

        return lista;
    }


}
