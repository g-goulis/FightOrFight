package com.ggoulis.game.components;

import com.almasb.fxgl.entity.component.Component;

/**
 * This component implements logic that can be shared between
 * living game entities e.g. the player and different enemies.
 */
public abstract class GeneralComponent extends Component {
    private int speed;

    private int health;

    private int damage;

    public GeneralComponent(int speed, int health, int damage) {
        this.speed = speed;
        this.health = health;
        this.damage = damage;
    }

    public boolean takeDamage(int damage) {
        setHealth(health - damage);

        if(health <= 0) {
            entity.removeFromWorld();
            return true;
        }

        return false;
    }


    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
