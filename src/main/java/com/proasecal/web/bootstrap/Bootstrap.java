package com.proasecal.web.bootstrap;

import com.proasecal.web.cache.CacheAtrib;
import com.proasecal.web.entity.seguridad.Usuarios;
import com.proasecal.web.service.seguridad.UsuarioService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class Bootstrap implements InitializingBean {
    private final Logger LOG = LoggerFactory.getLogger(Bootstrap.class);
    private CacheAtrib cacheAtrib = CacheAtrib.getInstance();
    
    
    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    /**
     *
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        LOG.info("obtener roles y accesos a url");
        cacheAtrib.setTextoPrueba("texto");
        
        LOG.info("Creandi uaurio admin");
        //this.createUser();
    }
    
    public void createUser() {
    	Usuarios user = new Usuarios();
    	
    	user.setNombres("Administrador");
    	user.setApellidos("Admin");
    	user.setCorreo("admin@proasecal.com");
    	user.setEstado(true);
    	user.setNombreUsuario("admin");
    	user.setPassword(this.bCryptPasswordEncoder.encode("admin"));
    	this.usuarioService.save(user);
    }
}
