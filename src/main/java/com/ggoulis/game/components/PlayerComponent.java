package com.ggoulis.game.components;

import com.almasb.fxgl.core.math.Vec2;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;
import javafx.geometry.Point2D;

import java.time.LocalDateTime;
import java.util.Date;

import static com.almasb.fxgl.dsl.FXGL.*;

public class PlayerComponent extends GeneralComponent {

    private static final int DASH_COOLDOWN = 2;

    private Date lastDash = new Date();

    private PhysicsComponent physics;

    public PlayerComponent() {
        super(100, 100, 10);
    }

    public void up() {
        physics.setVelocityY(-getSpeed());

    }

    public void down() {
        physics.setVelocityY(getSpeed());
    }

    public void left() {
        physics.setVelocityX(-getSpeed());
    }

    public void right() {
        physics.setVelocityX(getSpeed());
    }

    public void dash() {

        if(lastDash == null || new Date().getTime() - lastDash.getTime() > DASH_COOLDOWN * 1000) {
            System.out.println("DASHING");
//            physics.applyBodyForce(new Vec2(physics.getVelocityX() * 10, -physics.getVelocityY() * 10), new Vec2());
//            physics.applyBodyForceToCenter(new Vec2(physics.getVelocityX() * 10, -physics.getVelocityY() * 10));
            physics.setVelocityX(physics.getVelocityX() * 20);
            physics.setVelocityY(physics.getVelocityY() * 20);
            lastDash = new Date();
        }


    }

    public void stop() {
        physics.setLinearVelocity(0, 0);
    }

}
