package com.proasecal.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping(value="/restC")
public class RestController {

   /* @Autowired
    PaisService paisService;

    @Autowired
    IdTipoPaisService idTipoPaisService;

    *//**
     * Obtener Pais
     *//*
    @RequestMapping(value = "autoCompPais/{desPais}", method = RequestMethod.GET, produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Pais>> getPais(@PathVariable("desPais") String desPais) {
        List<Pais> listaFiltrada = paisService.filtrarPaisLike(desPais);
        try {
            return new ResponseEntity<List<Pais>>(listaFiltrada, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<List<Pais>>(listaFiltrada, HttpStatus.BAD_REQUEST);
        }

    }*/
}


