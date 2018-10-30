package com.proasecal.web.controller.seguridad;

import com.proasecal.web.entity.seguridad.Roles;
import com.proasecal.web.service.seguridad.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/rolesC")
public class RolesController {
//TODO:add LOGS
    @Autowired
    RolService rolService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView main() {
        ModelAndView modelAndView = new ModelAndView("security/roles_admin");
        modelAndView.addObject("rolesForm", new Roles());
        modelAndView.addObject("listaRoles", rolService.obtenerListaRoles());

        return modelAndView;
    }

    @RequestMapping(value = "updateRoles/{id}",method = RequestMethod.GET)
    public ModelAndView updateRoles(@PathVariable long id ){
        ModelAndView modelAndView = new ModelAndView("security/roles_admin");
        Roles rol = rolService.obtenerRol(id);
        modelAndView.addObject("rolesForm", rol);
        modelAndView.addObject("listaRoles", rolService.obtenerListaRoles());
        return modelAndView;
    }

    @RequestMapping(value = "/guardarRol",method = RequestMethod.POST)
    public ModelAndView updateRoles(@ModelAttribute("rolesForm")Roles rolesForm){
        rolService.guardarRol(rolesForm);
        return new ModelAndView("redirect:/rolesC/");
    }

}
