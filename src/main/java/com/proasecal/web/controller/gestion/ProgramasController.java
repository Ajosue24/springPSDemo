package com.proasecal.web.controller.gestion;

import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.proasecal.web.entity.seguridad.Roles;

@Controller
@RequestMapping(value = "/programas")
@PropertySource("classpath:static/properties/msg.properties")
public class ProgramasController {
	
	
	
	
	
	@GetMapping
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView("/administrar/programas/programa");

        return modelAndView;
	}

}
