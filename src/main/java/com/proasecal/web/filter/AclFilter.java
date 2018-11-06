package com.proasecal.web.filter;

import com.proasecal.web.cache.CacheAtrib;
import com.proasecal.web.entity.seguridad.CustomUserDetail;
import com.proasecal.web.entity.seguridad.Permisos;
import com.proasecal.web.entity.seguridad.Usuarios;
import com.proasecal.web.service.seguridad.PermisoService;
import com.proasecal.web.util.Util;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class AclFilter extends GenericFilterBean {
    private final Logger LOG = LoggerFactory.getLogger(AclFilter.class);
    private CacheAtrib cacheManager = CacheAtrib.getInstance();
    private PermisoService permisoService;
    
  
    
    public AclFilter(PermisoService permisoService) {
    	this.permisoService = permisoService;
    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest hRequest = ((HttpServletRequest) request);
        HttpServletResponse hResponse = ((HttpServletResponse) response);
        Boolean acceso = false;
        cacheManager.getTextoPrueba();
        String url = hRequest.getRequestURI();
        
        Optional<Authentication> auth = Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication());
        
        if(auth.isPresent()) {
        
        	
        	Optional<List<Permisos>> collectPermisos = Util.userPermissions();
        	
        	if(collectPermisos.isPresent()) {
        		Boolean isPermission = collectPermisos.get().stream().anyMatch(permiso -> permiso.getUrl().equals(url));
        		if(!isPermission) {
        			
        			
        		}else {
        			 

        		}
        	} 	
	
        }else {
        	chain.doFilter(request, response);
        }       

        chain.doFilter(request, response);
    }

    private String removeIdFromUrl(String url) {
        String urlNew = "";
        String[] arrayUrl = url.split("/");
        for (String segment : arrayUrl) {
            if (!isInteger(segment)) {
                urlNew += segment + "/";
            }
        }
        return urlNew;
    }

    private Boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
        return true;
    }
}
