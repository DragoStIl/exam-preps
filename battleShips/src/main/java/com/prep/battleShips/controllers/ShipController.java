package com.prep.battleShips.controllers;

import com.prep.battleShips.entities.dto.CreateShipDTO;
import com.prep.battleShips.services.AuthService;
import com.prep.battleShips.services.ShipService;
import com.prep.battleShips.session.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class ShipController {

    private ShipService shipService;
    private final AuthService authService;

    public ShipController(ShipService shipService, LoggedUser loggedUser, AuthService authService) {
        this.shipService = shipService;

        this.authService = authService;
    }

    @ModelAttribute("createShipDTO")
    public CreateShipDTO initShipCreator(){
        return new CreateShipDTO();
    }

    @GetMapping("/ships/add")
    public String ships(){
        if (this.authService.isNotLoggedIn()){
            return "redirect:/";
        }
        return "ship-add";
    }

    @PostMapping("/ships/add")
    public String ships(@Valid CreateShipDTO createShipDTO,
                        RedirectAttributes redirectAttributes,
                        BindingResult bindingResult){

        if (this.authService.isNotLoggedIn()){
            return "redirect:/";
        }

        if (bindingResult.hasErrors() || this.shipService.create(createShipDTO)){
            redirectAttributes.addFlashAttribute("createShipDTO", createShipDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.createShipDTO",
                    bindingResult);
            return "redirect:/ships/add";
        }
        return "redirect:/home";
    }
}
