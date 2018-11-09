package com.proasecal.web.interceptor;

import java.util.List;
import java.util.Optional;

import javax.management.RuntimeErrorException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.proasecal.web.entity.seguridad.Permisos;
import com.proasecal.web.filter.AclFilter;
import com.proasecal.web.util.Util;

public class AclInterceptor extends HandlerInterceptorAdapter{
	private final Logger LOG = LoggerFactory.getLogger(AclFilter.class);
	
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String url = request.getRequestURI();
        //Indica si es un atributo del front si es asi no se valida
        Boolean isFrontValue=Util.ifJsorCssorJpg(url);
        if(!isFrontValue){
            url = url.matches(".*\\d+.*")?Util.removeIdFromUrl(url):url;
            if(!url.contains("/login")) {
                Optional<List<Permisos>> collectPermisos = Util.userPermissions();
                if (collectPermisos.isPresent()) {
                    String finalUrl = url;
                    Boolean isPermission = collectPermisos.get().stream().anyMatch(
                            permiso -> permiso.getUrl().equals(finalUrl)||Util.urlCompareUrlDB(permiso.getUrl(),finalUrl));
                    if (!isPermission) {
                        LOG.info("no tiene permisos para acceder a " + url);
                        throw new RuntimeException("error");
                    } else {
                        LOG.info("si tiene permisos para acceder a " + url);
                    }
                }
            }
        }
        //response.sendRedirect(request.getContextPath() + "/login");
        return true;
    }
 
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, //
            Object handler, ModelAndView modelAndView) throws Exception {
    }
 
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, //
            Object handler, Exception ex) throws Exception {
    }

}
