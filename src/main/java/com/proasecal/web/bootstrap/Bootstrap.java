package com.proasecal.web.bootstrap;

import com.proasecal.web.cache.CacheAtrib;
import com.proasecal.web.entity.seguridad.Modulos;
import com.proasecal.web.entity.seguridad.Permisos;
import com.proasecal.web.entity.seguridad.Roles;
import com.proasecal.web.entity.seguridad.Usuarios;
import com.proasecal.web.service.seguridad.ModuloService;
import com.proasecal.web.service.seguridad.PermisoService;
import com.proasecal.web.service.seguridad.RolService;
import com.proasecal.web.service.seguridad.UsuarioService;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class Bootstrap implements InitializingBean {
    private final Logger LOG = LoggerFactory.getLogger(Bootstrap.class);
    private CacheAtrib cacheAtrib = CacheAtrib.getInstance();
    
    
    @Autowired
    private UsuarioService usuarioService;
    
   /* @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;*/

    @Autowired
    private RolService rolService;
    @Autowired
    PermisoService permisoService;
    @Autowired
    ModuloService moduloService;


    /**
     *
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        LOG.info("obtener roles y accesos a url");
        cacheAtrib.setTextoPrueba("texto");
        
        LOG.info("Creandi uaurio admin");
        //this.createUserAndRol();
  /*      LOG.info("Creando ROL");
        crearRol();
        LOG.info("Creando modulos");
        crearModulos();
        LOG.info("Creando url");
        crearUrl();*/

    }
    
   /* public void createUserAndRol() {

    	Usuarios user = new Usuarios();
    	
    	user.setNombres("Administrador");
    	user.setApellidos("Admin");
    	user.setCorreo("admin@proasecal.com");
    	user.setEstado(true);
    	user.setNombreUsuario("admin");
    	user.setPassword(this.bCryptPasswordEncoder.encode("admin"));
    	this.usuarioService.save(user);
    }

    public void crearRol(){
        Roles rol = new Roles();
        rol.setNombreRol("ADMIN");
        rol.setDescripcion("Rol administrador standart");
        rol.setEstado(true);
        //rolService.guardarRol(rol);

    }


    public void crearModulos(){
        Set<Roles> rolesList= new HashSet<>();   //Atributo que almacena el rol administrador
        rolesList.add(rolService.obtenerPorNombreRol("ADMIN"));
        Set<Modulos> modulosSet= new HashSet<>();
        modulosSet.add(new Modulos("seguridad","gestion Usuarios",rolesList));
        modulosSet.add(new Modulos("administrar","administracion sistema",rolesList));
        moduloService.guardarModulos(modulosSet);

    }

    public void crearUrl(){
        Set<Roles> rolesList= new HashSet<>();   //Atributo que almacena el rol administrador
        rolesList.add(rolService.obtenerPorNombreRol("ADMIN"));
        List<Permisos> permisos1 = new ArrayList<>();
        Modulos moduloSeguridad= moduloService.obtenerModuloPorNombre("seguridad");
        permisoService.guardarPermiso(new Permisos("Pagina de inicio","Pagina de inicio","/index",moduloSeguridad,new Permisos(1l),rolesList));
        Permisos permisos = permisoService.obtenerPermisosPorUrl("/index");
        permisos1.add(new Permisos("Editar Usuarios","Editar Usuarios","/usuarios/update",moduloSeguridad,permisos,rolesList));
        permisos1.add(new Permisos("Listar Usuario","Listar Usuario","/usuarios/index",moduloSeguridad,permisos,rolesList));
        permisos1.add(new Permisos("Pagina de inicio","Pagina de inicio","/index",moduloSeguridad,permisos,rolesList));
        permisos1.add(new Permisos("Pagina de login","Pagina de login","/login",moduloSeguridad,permisos,rolesList));
        permisos1.add(new Permisos("Inicio Gestion Usuarios","inicio Usuarios","/usuarios",moduloSeguridad,permisos,rolesList));
        permisos1.add(new Permisos("Listar programas","Listar Programas","/programas",moduloSeguridad,permisos,rolesList));
        permisoService.guardarPermisos(permisos1);
    }
*/
}
