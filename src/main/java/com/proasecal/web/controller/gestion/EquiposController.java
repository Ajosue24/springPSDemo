package com.proasecal.web.controller.gestion;

import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "equipos")
@PropertySource("classpath:static/properties/msg.properties")
public class EquiposController {


    @GetMapping
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView("/administrar/equipos/equipo");
        return modelAndView;
    }
}