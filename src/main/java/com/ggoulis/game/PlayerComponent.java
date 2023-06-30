package com.ggoulis.game;

import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;
import javafx.geometry.Point2D;

import static com.almasb.fxgl.dsl.FXGL.*;

public class PlayerComponent extends Component {

    private static final double PLAYER_SPEED = 420;

    private PhysicsComponent physics;

    public void up() {
        if (entity.getY() >= PLAYER_SPEED / 60)
            physics.setVelocityY(-PLAYER_SPEED);
        else
            stop();
    }

    public void down() {
        if (entity.getBottomY() <= getAppHeight() - (PLAYER_SPEED / 60))
            physics.setVelocityY(PLAYER_SPEED);
        else
            stop();
    }

    public void left() {
        if (entity.getX() >= PLAYER_SPEED / 60)
            physics.setVelocityX(-PLAYER_SPEED);
        else
            stop();
    }

    public void right() {
        if (entity.getRightX() <= getAppHeight() - (PLAYER_SPEED / 60))
            physics.setVelocityX(PLAYER_SPEED);
        else
            stop();
    }

    public void stop() {
        physics.setLinearVelocity(0, 0);
    }

}
