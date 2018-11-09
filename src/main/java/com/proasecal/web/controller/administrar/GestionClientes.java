package com.proasecal.web.controller.administrar;



import com.proasecal.web.entity.administrar.Clientes;
import com.proasecal.web.entity.parametricas.Ciudad;
import com.proasecal.web.entity.parametricas.Departamentos;
import com.proasecal.web.entity.parametricas.Pais;
import com.proasecal.web.entity.parametricas.TipoDocumentoPais;
import com.proasecal.web.service.administrar.ClienteService;
import com.proasecal.web.service.parametricas.CiudadService;
import com.proasecal.web.service.parametricas.DepartamentoService;
import com.proasecal.web.service.parametricas.IdTipoPaisService;
import com.proasecal.web.service.parametricas.PaisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;


@Controller
@RequestMapping(value="/gestionClientes")
public class GestionClientes {
    @Autowired
    PaisService paisService;
    @Autowired
    IdTipoPaisService idTipoPaisService;
    @Autowired
    DepartamentoService departamentoService;

    @Autowired
    CiudadService ciudadService;

    @Autowired
    ClienteService clienteService;




/**
     * activa al ingresar desde el index, Precarga la vista para gestion Clientes
     * @return
     */

    @RequestMapping(value="/",method= RequestMethod.GET )
    public ModelAndView main(){
        ModelAndView modelAndView = new ModelAndView("administrar/gestion_clientes");
        Clientes clientesForm = new Clientes();
        modelAndView.addObject("listaClientes",clienteService.getAllClientes());
        modelAndView.addObject("clienteForm", clientesForm);

        return modelAndView;
    }

    @RequestMapping(value="/guardarCliente",method= RequestMethod.POST )
    public ModelAndView salvarActualizarCliente(@ModelAttribute("clienteForm") Clientes clientesForm, ModelAndView model){
                                    //@Valid BindingResult bindingResult
        Date date = new Date();
        clientesForm.setFechaCreacion(date);
        Ciudad cd = new Ciudad();
        clientesForm.setIdCiudad(cd);
        clienteService.guardarActualizarCliente(clientesForm);
        return new ModelAndView("redirect:/gestionClientes/");
    }





    @RequestMapping(value="/editarCliente/{nroIdCliente}",method= RequestMethod.GET )
    public ModelAndView editarCliente(@PathVariable long nroIdCliente){
        Clientes cliente = clienteService.obtenerClienteByID(nroIdCliente);
        ModelAndView modelAndView = main().addObject("clienteForm",cliente);

            modelAndView.addObject("models",idTipoPaisService.obtenerIdSegunPais(cliente.getIdPais()));
            modelAndView.addObject("models2",departamentoService.obtenerDepartamentoXPais(cliente.getIdPais()));

        return modelAndView;
    }



    @RequestMapping("/buscarObtenerCliente")
    public String buscarObtenerCliente(@RequestParam("numeroIdentificacionCliente") String numeroIdentificacionCliente, Model model) {
        Clientes clientesForm = clienteService.obtenerPorDocumento(numeroIdentificacionCliente);

        if(clientesForm!=null){
            model.addAttribute("clienteForm", clientesForm);
            model.addAttribute("models",idTipoPaisService.obtenerIdSegunPais(clientesForm.getIdPais()));
            model.addAttribute("models2",departamentoService.obtenerDepartamentoXPais(clientesForm.getIdPais()));
        }else{
            clientesForm = new Clientes();
            clientesForm.setNumeroIdentificacionCliente(numeroIdentificacionCliente);
            model.addAttribute("clienteForm",clientesForm);
        }
        return "administrar/gestion_clientes :: #clienteForm"; //se dejan nombres standart para ser reusados
    }



/**
     * Obtiene ID Segun Pais
     * @param nombrePais
     * @param model
     * @return
     */

    @RequestMapping("/idTipoPais")
    public String obtenerIDPais(@RequestParam("idPais.nombrePais") String nombrePais, Model model) {
        Pais pais = new Pais();
        pais.setIdPais(Long.valueOf(nombrePais).longValue());
        List<TipoDocumentoPais> models = idTipoPaisService.obtenerIdSegunPais(pais);
        model.addAttribute("models", models);
        return "administrar/gestion_clientes :: models"; //se dejan nombres standart para ser reusados
    }


/**
     * Obtiene Segun Pais El departamento
     * @param nombrePais
     * @param model
     * @return
     */

    @RequestMapping("/obtDepartamentos")
    public String obtenerDeparSegunPais(@RequestParam("idPais.nombrePais") String nombrePais, Model model) {
        Pais pais = new Pais();
        pais.setIdPais(Long.valueOf(nombrePais).longValue());
        List<Departamentos> models = departamentoService.obtenerDepartamentoXPais(pais);
        model.addAttribute("models2", models);
        return "administrar/gestion_clientes :: models2";
    }


    /**
     * Obtener departamentos
     * @param idDepartamento
     * @param model
     * @return
     */
    @RequestMapping("/obtCiudades")
    public String obtenerCiudadesxDepartamentos(@RequestParam("idDepartamento") String idDepartamento, Model model) {
        Departamentos departamentos = new Departamentos();
        departamentos.setIdDepartamentos(Long.valueOf(idDepartamento).longValue());
        List<Ciudad> models = ciudadService.obtenerCiudadxDepartamento(departamentos);
        model.addAttribute("models3", models);
        return "administrar/gestion_clientes :: models3";
    }



}







