package com.prep.battleShips.controllers;

import com.prep.battleShips.entities.User;
import com.prep.battleShips.entities.dto.UserLoginDTO;
import com.prep.battleShips.entities.dto.UserRegistrationDTO;
import com.prep.battleShips.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class UserController {

    private AuthService authService;

    @Autowired
    public UserController(AuthService authService) {
        this.authService = authService;
    }

    @ModelAttribute("userRegistrationDTO")
    public UserRegistrationDTO initRegistrationDTO(){
        return new UserRegistrationDTO();
    }

    @ModelAttribute("userLoginDTO")
    public UserLoginDTO initUserLoginDTO(){
        return new UserLoginDTO();
    }
    @GetMapping("/register")
    public String register(){

        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid UserRegistrationDTO userRegistrationDTO,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors() || !this.authService.register(userRegistrationDTO)){
            redirectAttributes.addFlashAttribute("userRegistrationDTO", userRegistrationDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegistrationDTO",
                    bindingResult);
            return "redirect:/register";
        }

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/login")
    public String login(@Valid UserLoginDTO userLoginDTO,
                        BindingResult bindingResult,
                        RedirectAttributes redirectAttributes){

        if (bindingResult.hasErrors() || !this.authService.login(userLoginDTO)){
            redirectAttributes.addFlashAttribute("userLoginDTO", userLoginDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userLoginDTO",
                    bindingResult);

            return "redirect:/login";
        }

        return "redirect:/home";
    }

}
