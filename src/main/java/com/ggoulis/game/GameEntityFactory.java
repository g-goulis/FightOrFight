package com.ggoulis.game;

import com.almasb.fxgl.dsl.components.ProjectileComponent;
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
import javafx.geometry.Point2D;
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

    @Spawns("bullet")
    public Entity newBullet(SpawnData data) {
        var view = new Rectangle(30, 3, Color.LIGHTBLUE);
        view.setStroke(Color.BLUE);
        view.setArcWidth(15);
        view.setArcHeight(10);


        return entityBuilder(data)
                .type(EntityType.BULLET)
                .viewWithBBox(view)
                .with(new CollidableComponent(true))
                .with(new ProjectileComponent(new Point2D((Double) data.getData().get("mouseX"), (Double) data.getData().get("mouseY")), 300))
                .build();
    }

    @Spawns("enemy")
    public Entity newEnemy(SpawnData data) {
        return entityBuilder(data)
                .type(EntityType.ENEMY)
                .viewWithBBox(new Rectangle(40, 40, Color.RED))
                .with(new CollidableComponent(true))
                .build();
    }


}
