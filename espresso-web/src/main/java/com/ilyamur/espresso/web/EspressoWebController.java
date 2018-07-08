package com.ilyamur.espresso.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EspressoWebController {

    @RequestMapping("/")
    public String index() {
        return "/index";
    }
}
