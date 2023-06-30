package com.ggoulis.game;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import com.almasb.fxgl.physics.box2d.dynamics.FixtureDef;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static com.almasb.fxgl.dsl.FXGLForKtKt.entityBuilder;

public class GameEntityFactory implements EntityFactory {

    @Spawns("player")
    public Entity newPlayer(SpawnData data) {
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.KINEMATIC);
//        physics.setFixtureDef(new FixtureDef().density(0.3f).restitution(1.0f));
//        physics.setOnPhysicsInitialized(() -> physics.setLinearVelocity(5 * 60, -5 * 60));

        return entityBuilder()
                .from(data)
                .type(EntityType.PLAYER)
                .viewWithBBox(new Rectangle(20, 60, Color.LIGHTGRAY))
                .with(new CollidableComponent(true))
                .with(physics)
                .with(new PlayerComponent())
                .build();
    }


}
