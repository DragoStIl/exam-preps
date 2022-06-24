package com.prep.battleShips.entities.dto;


import com.prep.battleShips.entities.Ship;

public class ShipDTO {

    private long id;
    private String name;
    private long health;
    private long power;


    public ShipDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getHealth() {
        return health;
    }

    public void setHealth(long health) {
        this.health = health;
    }

    public long getPower() {
        return power;
    }

    public void setPower(long power) {
        this.power = power;
    }
}
