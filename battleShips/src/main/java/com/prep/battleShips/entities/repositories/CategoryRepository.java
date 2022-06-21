package com.prep.battleShips.entities.repositories;


import com.prep.battleShips.entities.Category;
import com.prep.battleShips.entities.enums.ShipType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByName(ShipType type);
}
