package com.proasecal.web.controller.seguridad;

import com.proasecal.web.entity.seguridad.Roles;
import com.proasecal.web.entity.seguridad.Usuarios;
import com.proasecal.web.service.seguridad.RolService;
import com.proasecal.web.service.seguridad.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping(value="/usuarios")
@PropertySource("classpath:static/properties/msg.properties")
public class UsuariosController {
    //TODO:add LOGS

    @Autowired
    Environment env;
    @Autowired
    RolService rolService;

    @Autowired
    UsuarioService usuarioService;

    @RequestMapping(method= RequestMethod.GET )
    public ModelAndView main(){
        ModelAndView modelAndView = new ModelAndView("security/usuariosAdmin");
        List<Roles> listaRoles = rolService.obtenerListaRoles();
        List<String> lista = Arrays.asList("LOCO","otra cosa","otro rol","COORDINADOR");
        List<Usuarios>listaUsuarios = usuarioService.list();
        modelAndView.addObject("usuariosForm", new Usuarios());
        modelAndView.addObject("listaUsuarios", listaUsuarios);
        modelAndView.addObject("listaRoles", listaRoles);



        return modelAndView;
    }


    @RequestMapping(value="/save",method= RequestMethod.POST )
    public ModelAndView guardarUsuario(@Valid @ModelAttribute("usuariosForm") Usuarios usuariosForm,
                                       @RequestParam(value="cancelar", required=false) String cancelar,
                                       BindingResult bindingResult,
                                       ModelAndView model){
        //Limpiar Formulario
        if(cancelar!=null){
            return new ModelAndView("redirect:/usuarios");
        }

        if(usuarioService.validarUsuarioExistente(usuariosForm.getNombreUsuario())){
            bindingResult.rejectValue("nombreUsuario", "error", env.getProperty("msg.nombreExistente"));
        }else if(usuariosForm.getCodProasecal()!=null&& usuariosForm.getCodProasecal()>1){
            if(usuarioService.validarCodProasecalExistente(usuariosForm.getCodProasecal())){
                bindingResult.rejectValue("codProasecal", "error", env.getProperty("msg.codigoExistente"));
            }
        }
        if(bindingResult.hasErrors()){
            model.setViewName("usuariosAdmin");
            List<Usuarios>listaUsuarios = usuarioService.list();
            List<Roles> listaRoles = rolService.obtenerListaRoles();
            model.addObject("usuariosForm", usuariosForm);
            model.addObject("listaUsuarios", listaUsuarios);
            model.addObject("listaRoles", listaRoles);
            return model;
        }
        usuariosForm.setPassword(new BCryptPasswordEncoder().encode(usuariosForm.getPassword()));
        usuarioService.save(usuariosForm);
        return new ModelAndView("redirect:/usuarios");
    }

    //editar
    @RequestMapping(value="/update/{id}",method=RequestMethod.GET )
    public ModelAndView editArticle(@PathVariable long id) {
        ModelAndView modelAndView = new ModelAndView("security/usuariosAdmin");
        Usuarios user= usuarioService.get(id);
        List<Usuarios> listaUsuarios = usuarioService.list();
        List<Roles> listaRoles = rolService.obtenerListaRoles();
        modelAndView.addObject("listaUsuarios", listaUsuarios);
        modelAndView.addObject("listaRoles", listaRoles);
        modelAndView.addObject("usuariosForm",user);
        return modelAndView;
    }


}
