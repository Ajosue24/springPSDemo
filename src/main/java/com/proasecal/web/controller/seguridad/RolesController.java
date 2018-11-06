package com.proasecal.web.controller.seguridad;

import com.proasecal.web.entity.seguridad.Roles;
import com.proasecal.web.service.seguridad.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.core.env.Environment;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/roles")
@PropertySource("classpath:static/properties/msg.properties")
public class RolesController {
//TODO:add LOGS
    @Autowired
    RolService rolService;

    @Autowired
    Environment env;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView main() {
        ModelAndView modelAndView = new ModelAndView("security/rolesAdmin");
        modelAndView.addObject("rolesForm", new Roles());
        modelAndView.addObject("listaRoles", rolService.obtenerListaRoles());

        return modelAndView;
    }

    @RequestMapping(value = "updateRoles/{id}",method = RequestMethod.GET)
    public ModelAndView updateRoles(@PathVariable long id ){
        ModelAndView modelAndView = new ModelAndView("security/rolesAdmin");
        Roles rol = rolService.obtenerRol(id);
        modelAndView.addObject("rolesForm", rol);
        modelAndView.addObject("listaRoles", rolService.obtenerListaRoles());
        return modelAndView;
    }

    @RequestMapping(value = "/guardarRol",method = RequestMethod.POST)
    public ModelAndView updateRoles(@Valid @ModelAttribute("rolesForm")Roles rolesForm, BindingResult bindingResult,
                                    ModelAndView model){
        rolesForm.setNombreRol(rolesForm.getNombreRol().toUpperCase().trim());
        if (rolService.validarSiExisteRol(rolesForm.getNombreRol())){
            bindingResult.rejectValue("nombreRol", "error", env.getProperty("msg.nombreExistente"));
        }
        if(bindingResult.hasErrors()){
            model.setViewName("rolesAdmin");
            model.addObject("rolesForm", rolesForm);
            model.addObject("listaRoles", rolService.obtenerListaRoles());
            return model;
        }
        rolService.guardarRol(rolesForm);
        return new ModelAndView("redirect:/roles/");
    }

}
