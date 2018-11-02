package com.proasecal.web.controller.error;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class DefaultErrorController implements ErrorController {

        @RequestMapping(value = "error", method = RequestMethod.GET)
    public ModelAndView renderErrorPage(HttpServletRequest httpRequest) {

        ModelAndView errorPage = new ModelAndView("error/errorPage");
        String errorMsg = "";
        int nroError = getErrorCode(httpRequest);


        switch (nroError) {
            case 400: {
                errorMsg = "Solicitud incorrecta";
                break;
            }
            case 401: {
                errorMsg = "No autorizado";
                break;
            }
            case 404: {
                errorMsg = "No se encuentra la página o recurso solicitado";
                break;
            }
            case 500: {
                errorMsg = "Error Interno";
                break;
            }


        }
        errorPage.addObject("nroError",nroError);
        errorPage.addObject("errorMsg", errorMsg);
        return errorPage;
    }

    private int getErrorCode(HttpServletRequest httpRequest) {
        return (Integer) httpRequest
                .getAttribute("javax.servlet.error.status_code");
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}