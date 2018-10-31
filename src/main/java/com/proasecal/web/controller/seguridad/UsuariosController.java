package com.proasecal.web.controller.seguridad;

import com.proasecal.web.entity.seguridad.Roles;
import com.proasecal.web.entity.seguridad.Usuarios;
import com.proasecal.web.service.seguridad.RolService;
import com.proasecal.web.service.seguridad.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping(value="/usuarios")
public class UsuariosController {
    //TODO:add LOGS

    @Autowired
    RolService rolService;

    @Autowired
    UsuarioService usuarioService;

    @RequestMapping(value="/",method= RequestMethod.GET )
    public ModelAndView main(){
        ModelAndView modelAndView = new ModelAndView("security/usuarios_admin");
        List<Roles> listaRoles = rolService.obtenerListaRoles();
        List<String> lista = Arrays.asList("LOCO","otra cosa","otro rol");
        List<Usuarios>listaUsuarios = usuarioService.list();
        modelAndView.addObject("usuariosForm", new Usuarios());
        modelAndView.addObject("listaUsuarios", listaUsuarios);
        modelAndView.addObject("listaRoles", listaRoles);



        return modelAndView;
    }


    @RequestMapping(value="/save",method= RequestMethod.POST )
    public ModelAndView guardarUsuario(@ModelAttribute("usuariosForm") Usuarios usuariosForm){
        usuariosForm.setPassword(new BCryptPasswordEncoder().encode(usuariosForm.getPassword()));
            usuarioService.save(usuariosForm);
        return new ModelAndView("redirect:/usuariosC/");
    }

    //editar
    @RequestMapping(value="/update/{id}",method=RequestMethod.GET )
    public ModelAndView editArticle(@PathVariable long id) {
        ModelAndView modelAndView = new ModelAndView("security/usuarios_admin");
        Usuarios user= usuarioService.get(id);
        List<Usuarios> listaUsuarios = usuarioService.list();
        List<Roles> listaRoles = rolService.obtenerListaRoles();
        modelAndView.addObject("listaUsuarios", listaUsuarios);
        modelAndView.addObject("listaRoles", listaRoles);
        modelAndView.addObject("usuariosForm",user);
        return modelAndView;
    }


}
