package com.proasecal.web.util;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.proasecal.web.entity.seguridad.Modulos;
import com.proasecal.web.filter.AclFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.proasecal.web.entity.seguridad.CustomUserDetail;
import com.proasecal.web.entity.seguridad.Permisos;

public class Util {

    private static final Logger LOG = LoggerFactory.getLogger(Util.class);

	/**
	 * Valida si usuario esta authenticado
	 * @return
	 */
	public static CustomUserDetail userAuth() {
		Optional<Authentication> auth  = Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication());
		if(auth.isPresent()) {
		return (CustomUserDetail) auth.get().getPrincipal();  
		}else {
			return null;
		}
	}

	/**
	 * Permisos al usuario por la url
	 * @return
	 */
	public static Optional<List<Permisos>> userPermissions() {
	    try {
            Optional<CustomUserDetail> usuario = Optional.ofNullable(Util.userAuth());
            if(usuario.isPresent()) {
                return Optional.ofNullable(usuario.get().getRolesList().stream().map(x -> x.getPermisosList()).flatMap(x  -> x.stream()).collect(Collectors.toList()));
            }else {
                return Optional.empty();
            }
        }catch (Exception e){
	        LOG.error("Error al obtener las urls a las cuales usuario");
	        return Optional.empty();
        }

	}

	/**
	 * Obtiene a los modulos que estan asociado al rol
	 * @return
	 */
	public static Optional<List<Modulos>> userModules(){
		Optional<CustomUserDetail> usuario = Optional.ofNullable(Util.userAuth());
		if(usuario.isPresent()) {
			return Optional.ofNullable(usuario.get().getRolesList().stream().map(x -> x.getModulosList()).flatMap(x  -> x.stream()).collect(Collectors.toList()));
		}else {
			return Optional.empty();
		}
	}


}
