package com.prep.battleShips.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String loggedOut(){
        return "index";
    }

    @GetMapping("/home")
    public String LoggedIn(){
        return "home";
    }

}
