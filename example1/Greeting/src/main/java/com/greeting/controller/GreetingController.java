package com.greeting.controller;

import com.greeting.service.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GreetingController {
    @Autowired
    private GreetingService greetingService;

//    public GreetingController(GreetingService greetingService) {
//        this.greetingService = greetingService;
//    }

    @GetMapping("/")
    public String showGreeting(Model model){
        model.addAttribute("message",greetingService.getGreeting());
        return "greeting";
    }

}
