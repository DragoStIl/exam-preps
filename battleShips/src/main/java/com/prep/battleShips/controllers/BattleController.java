package com.prep.battleShips.controllers;

import com.prep.battleShips.entities.dto.StartBattleDTO;
import com.prep.battleShips.services.AuthService;
import com.prep.battleShips.services.BattleService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class BattleController {

    private final BattleService battleService;
    private final AuthService authService;

    public BattleController(BattleService battleService, AuthService authService) {
        this.battleService = battleService;
        this.authService = authService;
    }

    @PostMapping("/battle")
    public String battle(@Valid StartBattleDTO startBattleDTO,
                         RedirectAttributes redirectAttributes,
                         BindingResult bindingResult) {


        if (this.authService.isNotLoggedIn()){
            return "redirect:/";
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("startBattleDTO", startBattleDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.startBattleDTO",
                    bindingResult);
            return "redirect:/home";
        }
        this.battleService.attack(startBattleDTO);
        return "redirect:/home";
    }

}
