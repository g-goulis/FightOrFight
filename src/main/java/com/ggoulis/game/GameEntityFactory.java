package com.ggoulis.game;

import com.almasb.fxgl.dsl.components.ProjectileComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import com.almasb.fxgl.physics.box2d.dynamics.FixtureDef;
import com.ggoulis.game.components.EnemyComponent;
import com.ggoulis.game.components.PlayerComponent;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import static com.almasb.fxgl.dsl.FXGLForKtKt.entityBuilder;

public class GameEntityFactory implements EntityFactory {

    @Spawns("player")
    public Entity newPlayer(SpawnData data) {
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.DYNAMIC);
        physics.setFixtureDef(new FixtureDef().friction(0.0f));
//        physics.setFixtureDef(new FixtureDef().density(0.3f).restitution(1.0f));
//        physics.setOnPhysicsInitialized(() -> physics.setLinearVelocity(5 * 60, -5 * 60));

        return entityBuilder()
                .from(data)
                .type(EntityType.PLAYER)
                .viewWithBBox(new Rectangle(20, 60, Color.LIGHTGRAY))
                .with(physics)
                .with(new CollidableComponent(true))
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
                .with(new PhysicsComponent())
                .with(new EnemyComponent())
                .build();
    }


    @Spawns("r,rect,type1")
    public Entity newRectangle(SpawnData data) {

        return entityBuilder(data)
                .type(EntityType.WALL)
                .viewWithBBox(new Rectangle(50, 50))
                .with(new PhysicsComponent())
                .with(new CollidableComponent(true))
                .build();
    }

    @Spawns("")
    public Entity newEmpty(SpawnData data) {
        return entityBuilder(data)
                .view(new Rectangle(50, 50, Color.RED))
                .build();
    }

    @Spawns("c,circle,type2,type3")
    public Entity newCircle(SpawnData data) {
        return entityBuilder(data)
                .view(new Circle(15, 15, 15))
                .build();
    }

}
