package com.proasecal.web.controller.seguridad;

import org.springframework.stereotype.Controller;

public class RolesAccess {

    /**
     * Atributo Que indica si usuario puede ver el modulo
     */
    public static String devolverRolParaModulo(String nombreModulo){
        String variable= "ADMIN";
        return variable;
    }
}
