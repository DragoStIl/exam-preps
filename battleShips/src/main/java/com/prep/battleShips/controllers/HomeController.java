package com.prep.battleShips.controllers;

import com.prep.battleShips.entities.dto.ShipDTO;
import com.prep.battleShips.entities.dto.StartBattleDTO;
import com.prep.battleShips.services.AuthService;
import com.prep.battleShips.services.ShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class HomeController {

    private final ShipService shipService;
    private AuthService authService;

    @ModelAttribute("startBattleDTO")
    public StartBattleDTO initBattleForm(){
        return new StartBattleDTO();
    }
    @Autowired
    public HomeController(ShipService shipService, AuthService authService) {
        this.shipService = shipService;
        this.authService = authService;
    }

    @GetMapping("/")
    public String loggedOut(){
        if(!this.authService.isNotLoggedIn()){
            return "redirect:/home";
        }

        return "index";
    }

    @GetMapping("/home")
    public String LoggedIn(Model model){


        if(this.authService.isNotLoggedIn()){
            return "redirect:/";
        }
        long loggedUserId = this.authService.getLoggedUserId();

        List<ShipDTO> myShips = this.shipService.getShipsOfUser(loggedUserId);
        List<ShipDTO> enemyShips = this.shipService.getShipsNotOwnedBy(loggedUserId);
        List<ShipDTO> sortedShips = this.shipService.getAllSorted();

        model.addAttribute("myShips", myShips);
        model.addAttribute("enemyShips", enemyShips);
        model.addAttribute("sortedShips", sortedShips);

        return "home";
    }



}
