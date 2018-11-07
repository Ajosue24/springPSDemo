package com.proasecal.web.controller.seguridad;

import com.proasecal.web.entity.seguridad.Modulos;
import com.proasecal.web.entity.seguridad.Permisos;
import com.proasecal.web.entity.seguridad.Roles;
import com.proasecal.web.service.seguridad.ModuloService;
import com.proasecal.web.service.seguridad.PermisoService;
import com.proasecal.web.service.seguridad.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping(value = "/pemisosRoles")
public class PermisosRolesController {

    @Autowired
    RolService rolService;
    @Autowired
    ModuloService moduloService;
    @Autowired
    PermisoService permisoService;

    @RequestMapping(value="/",method= RequestMethod.GET )
    public ModelAndView main(){
        ModelAndView modelAndView = new ModelAndView("security/permisos_roles");
        List<String> lista = Arrays.asList("LOCO","otra cosa","otro rol");
        Permisos permisosObj = new Permisos();
        List<Roles> listaRoles = rolService.obtenerListaRoles();
        List<Modulos>listaModulos = moduloService.obtenerModulos();
        modelAndView.addObject("permisosRolesForm", new Permisos());
        modelAndView.addObject("listaModulos",listaModulos);
        modelAndView.addObject("modulosForm",new Modulos());
        modelAndView.addObject("listaRoles",listaRoles);
        return modelAndView;
    }

    /**
     *  Atributo que busca si Los modulos se encuentran habilitados para dicho Rol
     *  0 = roles // pos 1 = modulo
     * @param paramsLong pos
     * @param model
     * @return
     */
    @RequestMapping(value="/enableModule",method= RequestMethod.GET)
    public String enableModule(@RequestParam("modulos") List<Long> paramsLong, Model model){
        Modulos modulos = moduloService.buscarModuloPorId(paramsLong.get(1));
        Roles rolSeleccionado = rolService.obtenerRol(paramsLong.get(0));
        modulos.getRolesList().add(rolSeleccionado);
        moduloService.guardarModulo(modulos);
        List<Permisos> permisosxModulo = permisoService.obtenerPermisosPorModulos(modulos);
       model.addAttribute("listaPermisos",permisosxModulo);
       List<Boolean> permisosRol=new ArrayList<>();
       //front no usa stream
       for(Permisos r : permisosxModulo){
           if(r.getListRoles().stream()
                   .anyMatch(t->t.getIdRoles() == paramsLong.get(0))) {
               permisosRol.add(true);
           }else{
              permisosRol.add(false);
           }
       }
       model.addAttribute("permisosRol",permisosRol);
return "security/permisos_roles :: permisosRolesTable";
    }

    /**
     *
     * Remover De la tabla
     * pos 0 = roles // pos 1 = modulo
     * @param paramsLong
     * @param model
     * @return
     */
    @RequestMapping(value="/disableModule",method= RequestMethod.GET)
    public String disable(@RequestParam("modulos") List<Long> paramsLong, Model model){
        Modulos modulos = moduloService.buscarModuloPorId(paramsLong.get(1));
        modulos.getRolesList().removeIf(t->t.getIdRoles() == paramsLong.get(0));
        Roles roles = rolService.obtenerRol(paramsLong.get(0));
       List<Permisos> permisos = permisoService.obtenerPermisosXRol(roles);
       for(Permisos p:permisos){
           p.getListRoles().remove(roles);
           permisoService.guardarPermiso(p);
       }
        moduloService.guardarModulo(modulos);
        return "security/permisos_roles :: permisosRolesTable";
    }

    /**
     * Habilita Permiso Para Url
     *    0 = idURL / 1 = Rol
     * @param paramsLong
     * @param model
     */
    @RequestMapping(value="/enableUrl",method= RequestMethod.GET)
    public String enableUrl(@RequestParam("url") List<Long> paramsLong, Model model){
        Permisos permisos = permisoService.obtenerPermiso(paramsLong.get(0));
        permisos.getListRoles().add(rolService.obtenerRol(paramsLong.get(1)));
        permisoService.guardarPermiso(permisos);
        return "security/permisos_roles :: content";
    }

    /**
     * Eliminar permiso por url
     * 0 = id / 1 = rol
     * @param paramsLong
     * @param model
     */
    @RequestMapping(value="/disableUrl",method= RequestMethod.GET)
    public String disableUrl(@RequestParam("url") List<Long> paramsLong, Model model){
        Permisos permisos = permisoService.obtenerPermiso(paramsLong.get(0));
        permisos.getListRoles().removeIf(t->t.getIdRoles() == paramsLong.get(1));
        permisoService.guardarPermiso(permisos);
        return "security/permisos_roles :: content";
    }


    /**
     * 0 = rol / 1 = modulo
     * @param rolxmodulo
     * @param model
     * @return
     */
    @RequestMapping(value = "/validateModuleStatus",method= RequestMethod.GET)
    public String validateModuleStatus(@RequestParam("rolxmodulo") List<Long> rolxmodulo, Model model){
        Modulos modulosXRol = moduloService.buscarModuloPorId(rolxmodulo.get(1));
       Boolean cEstadoModulo = modulosXRol.getRolesList().stream().anyMatch(t->t.getIdRoles() == rolxmodulo.get(0));
            model.addAttribute("cEstadoModulo",cEstadoModulo);
        return "security/permisos_roles :: jsBck";
    }


    @RequestMapping(value = "/onlyLoad2Form",method= RequestMethod.GET)
    public String onlyLoad2Form(@RequestParam("rolxmodulo") List<Long> rolxmodulo, Model model){
        if(rolxmodulo.get(2) == 1){
            Modulos modulos = moduloService.buscarModuloPorId(rolxmodulo.get(1));
            List<Permisos> permisosxModulo = new ArrayList<>();
            permisosxModulo = permisoService.obtenerPermisosPorModulos(modulos);
            model.addAttribute("listaPermisos",permisosxModulo);
            List<Boolean> permisosRol=new ArrayList<>();
            //front no usa stream
            for(Permisos r : permisosxModulo){
                if(r.getListRoles().stream()
                        .anyMatch(t->t.getIdRoles() == rolxmodulo.get(0))) {
                    permisosRol.add(true);
                }else{
                    permisosRol.add(false);
                }
            }
            model.addAttribute("permisosRol",permisosRol);
        }
        return "security/permisos_roles :: permisosRolesTable";
    }
}
