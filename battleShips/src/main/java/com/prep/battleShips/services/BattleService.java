package com.prep.battleShips.services;

import com.prep.battleShips.entities.Ship;
import com.prep.battleShips.entities.dto.StartBattleDTO;
import com.prep.battleShips.repositories.ShipRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class BattleService {

    private ShipRepository shipRepository;

    public BattleService(ShipRepository shipRepository) {
        this.shipRepository = shipRepository;
    }

    public void attack(StartBattleDTO action){
        Optional<Ship> attackerOpt = this.shipRepository.findById(action.getAttackerId());
        Optional<Ship> defenderOpt = this.shipRepository.findById(action.getDefenderId());

        if (attackerOpt.isEmpty() || defenderOpt.isEmpty()){
            throw new NoSuchElementException();
        }

        Ship attacker = attackerOpt.get();
        Ship defender = defenderOpt.get();

        long defenderHp = defender.getHealth() - attacker.getPower();

        if (defenderHp <= 0){
            this.shipRepository.delete(defender);
        } else {
            defender.setHealth(defenderHp);
            this.shipRepository.save(defender);
        }




    }
}
