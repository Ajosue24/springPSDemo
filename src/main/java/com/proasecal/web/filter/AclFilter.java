package com.proasecal.web.filter;

import com.proasecal.web.cache.CacheAtrib;
import com.proasecal.web.entity.seguridad.Usuarios;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AclFilter extends GenericFilterBean {
    private final Logger LOG = LoggerFactory.getLogger(AclFilter.class);
    private CacheAtrib cacheManager = CacheAtrib.getInstance();
  
    
    public AclFilter() {}


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest hRequest = ((HttpServletRequest) request);
        LOG.info("Verificando los permisos del usuario");
        Boolean acceso = true;
        cacheManager.getTextoPrueba();
        
        //Usuarios usuario = (Usuarios) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (acceso) {
            LOG.info("Permiso para acceder al recurso.");
            chain.doFilter(request, response);
        } else {
            LOG.info("No posee el permiso para acceder al recurso");
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
            httpServletResponse.addHeader("Content-Type", "application/json");
            httpServletResponse.setStatus(401);
            JSONObject error = new JSONObject();
            error.put("mensaje", "No posee el permiso para acceder al recurso");
            httpServletResponse.getWriter().print(error.toString());
        }
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
