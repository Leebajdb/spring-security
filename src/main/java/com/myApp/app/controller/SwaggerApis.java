package com.myApp.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * The controller with APIs for Swagger.
 */
@Controller
public class SwaggerApis {

    /**
     * Swagger home.
     * @return swagger-ui.html
     */
    @RequestMapping("/")
    public String home() {
        return "redirect:swagger-ui.html";
    }
}
