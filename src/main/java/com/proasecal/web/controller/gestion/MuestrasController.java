package com.proasecal.web.controller.gestion;

import com.proasecal.web.entity.administrar.Clientes;
import com.proasecal.web.entity.parametricas.Ciudad;
import com.proasecal.web.entity.parametricas.Departamento;
import com.proasecal.web.entity.parametricas.Pais;
import com.proasecal.web.entity.parametricas.TipoDocumentoPais;

import com.proasecal.web.service.administrar.ClienteService;
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
@RequestMapping(value = "/muestras")

public class MuestrasController {

    @Autowired
    PaisService paisService;
    @Autowired
    IdTipoPaisService idTipoPaisService;
    @Autowired
    DepartamentoService departamentoService;
   /*
    @Autowired
    MuestrasService muestrasService;
    */

    /**
     * activa al ingresar desde el index, Precarga la vista para gestion laboratorios
     *
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView main() {
        ModelAndView modelAndView = new ModelAndView("administrar/muestras");

        return modelAndView;
    }

    @RequestMapping(value = "/guardarMuestra", method = RequestMethod.POST)
    public ModelAndView saveMuestra(@ModelAttribute("muestraForm") /*Laboratorios loaboratoriosForm,*/ ModelAndView model) {
        //@Valid BindingResult bindingResult

        return new ModelAndView("redirect:/muestras/");
    }

    @RequestMapping(value = "/editarMuestra/{nroIdMuestra}", method = RequestMethod.GET)
    public ModelAndView editarMuestra(@PathVariable long nroIdMuestra) {
        /*
        Muestras muestra = muestrasService.obtenerMuestrasByID(nroIdMuestra)
        */

        ModelAndView modelAndView = main().addObject("muestrasForm", "" /*muestra*/);
/*
        modelAndView.addObject("models", idTipoPaisService.obtenerIdSegunPais(cliente.getIdPais()));
        modelAndView.addObject("models2", departamentoService.obtenerDepartamentoXPais(cliente.getIdPais()));
*/
        return modelAndView;
    }

    @RequestMapping("/buscarObtenerCliente")
    public String buscarObtenerCliente(@RequestParam("numeroIdentificacionCliente") String numeroIdentificacionCliente, Model model) {
      /*
        Clientes clientesForm = clienteService.obtenerPorDocumento(numeroIdentificacionCliente);

        if (clientesForm != null) {
            model.addAttribute("clienteForm", clientesForm);
            model.addAttribute("models", idTipoPaisService.obtenerIdSegunPais(clientesForm.getIdPais()));
            model.addAttribute("models2", departamentoService.obtenerDepartamentoXPais(clientesForm.getIdPais()));
        } else {
            clientesForm = new Clientes();
            clientesForm.setNumeroIdentificacionCliente(numeroIdentificacionCliente);
            model.addAttribute("clienteForm", clientesForm);
        }
        */
        return "administrar/gestion_clientes :: #clienteForm"; //se dejan nombres standart para ser reusados
    }

    /**
     * Obtiene ID Segun Pais
     *
     * @param nombrePais
     * @param model
     * @return
     */
    @RequestMapping("/idTipoPais")
    public String obtenerIDPais(@RequestParam("idPais.nombrePais") String nombrePais, Model model) {
       /*
        Pais pais = new Pais();
        pais.setIdPais(Long.valueOf(nombrePais).longValue());
        List<TipoDocumentoPais> models = idTipoPaisService.obtenerIdSegunPais(pais);
        model.addAttribute("models", models);
        */
        return "administrar/gestion_clientes :: models"; //se dejan nombres standart para ser reusados
    }

    /**
     * Obtiene Segun Pais El departamento
     *
     * @param nombrePais
     * @param model
     * @return
     */
    @RequestMapping("/obtDepartamentos")
    public String obtenerDeparSegunPais(@RequestParam("idPais.nombrePais") String nombrePais, Model model) {
        /*
        Pais pais = new Pais();
        pais.setIdPais(Long.valueOf(nombrePais).longValue());
        List<Departamento> models = departamentoService.obtenerDepartamentoXPais(pais);
        model.addAttribute("models2", models);
        */
        return "administrar/gestion_clientes :: models2";
    }
}
