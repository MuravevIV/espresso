package com.ilyamur.espresso.web.controller;

import com.ilyamur.espresso.data.entity.EchoMessage;
import com.ilyamur.espresso.data.repository.EchoMessageHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class EspressoWebController {

    @Autowired
    private EchoMessageHistoryRepository repository;

    @RequestMapping("/")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("/index");
        List<EchoMessage> echoMessages = repository.findAll();
        modelAndView.addObject("echoMessages", echoMessages);
        return modelAndView;
    }
}
