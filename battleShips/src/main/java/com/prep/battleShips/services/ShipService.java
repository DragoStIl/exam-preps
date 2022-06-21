package com.prep.battleShips.services;

import com.prep.battleShips.entities.Category;
import com.prep.battleShips.entities.Ship;
import com.prep.battleShips.entities.User;
import com.prep.battleShips.entities.dto.CreateShipDTO;
import com.prep.battleShips.entities.enums.ShipType;
import com.prep.battleShips.entities.repositories.CategoryRepository;
import com.prep.battleShips.repositories.ShipRepository;
import com.prep.battleShips.repositories.UserRepository;
import com.prep.battleShips.session.LoggedUser;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShipService {

    private ShipRepository shipRepository;
    private CategoryRepository categoryRepository;
    private UserRepository userRepository;
    private LoggedUser loggedUser;

    public ShipService(ShipRepository shipRepository,
                       CategoryRepository categoryRepository,
                       UserRepository userRepository,
                       LoggedUser loggedUser) {
        this.shipRepository = shipRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
        this.loggedUser = loggedUser;
    }

    public boolean create(CreateShipDTO createShipDTO){

        Optional<Ship> byName = this.shipRepository.findByName(createShipDTO.getName());

        if (byName.isPresent()){
            return false;
        }

        Ship ship = new Ship();

        ShipType type = switch (createShipDTO.getCategory()){
            case 0 -> ShipType.BATTLE;
            case 1 -> ShipType.CARGO;
            default -> ShipType.PATROL;
        };

        Category catByName = this.categoryRepository.findByName(type);
        Optional<User> owner = this.userRepository.findById(loggedUser.getId());

        ship.setName(createShipDTO.getName());
        ship.setPower(createShipDTO.getPower());
        ship.setHealth(createShipDTO.getHealth());
        ship.setCategory(catByName);
        ship.setCreated(createShipDTO.getCreated());
        ship.setUser(owner.get());

        this.shipRepository.save(ship);
        return true;
    }
}
