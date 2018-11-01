package com.proasecal.web.controller;

import com.proasecal.web.service.seguridad.PermisoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.proasecal.web.entity.seguridad.Usuarios;
import com.proasecal.web.service.seguridad.UsuarioService;

@Controller
public class AccessController {
	
	
	@Autowired
	private UsuarioService usuarioService;



    @GetMapping("/index")
    @PreAuthorize("hasRole(T(com.example.demo.controller.security.RolesAccess).devolverRol('vista1'))")
    public String index() {
        return "index";
    }
    @PostMapping("/index")
    public ModelAndView postIndex() {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("rol","ADMIN");
        return modelAndView;
    }
    @GetMapping("/login")
    public ModelAndView login(@RequestParam(value = "logout", required = false) String logout) {
        ModelAndView model = new ModelAndView();
        if (logout != null) {
            model.addObject("msg", "You've been logged out successfully.");
        }
        model.setViewName("login");

       // UserAndRol userEntity =  new UserAndRol();
        //model.addObject("user",User);

        return model;
    }

    @GetMapping("/pagExtra")
    public String pagExtra() {
        return "pagExtra";
    }


    @GetMapping("/")
    public String home() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ROLE_ANONYMOUS"))){
            return "login";
        }
        return "index";

    }


}


