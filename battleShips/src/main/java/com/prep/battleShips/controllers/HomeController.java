package com.prep.battleShips.controllers;

import com.prep.battleShips.entities.dto.ShipDTO;
import com.prep.battleShips.entities.dto.StartBattleDTO;
import com.prep.battleShips.services.ShipService;
import com.prep.battleShips.session.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class HomeController {

    private final ShipService shipService;
    private final LoggedUser loggedUser;

    @ModelAttribute("startBattleDTO")
    public StartBattleDTO initBattleForm(){
        return new StartBattleDTO();
    }
    @Autowired
    public HomeController(ShipService shipService, LoggedUser loggedUser) {
        this.shipService = shipService;
        this.loggedUser = loggedUser;
    }

    @GetMapping("/")
    public String loggedOut(){
        return "index";
    }

    @GetMapping("/home")
    public String LoggedIn(Model model){
        long loggedUserId = this.loggedUser.getId();
        List<ShipDTO> myShips = this.shipService.getShipsOfUser(loggedUserId);
        List<ShipDTO> enemyShips = this.shipService.getShipsNotOwnedBy(loggedUserId);
        List<ShipDTO> sortedShips = this.shipService.getAllSorted();

        model.addAttribute("myShips", myShips);
        model.addAttribute("enemyShips", enemyShips);
        model.addAttribute("sortedShips", sortedShips);

        return "home";
    }



}
