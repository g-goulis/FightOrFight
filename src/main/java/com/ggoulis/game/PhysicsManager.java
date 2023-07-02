package com.ggoulis.game;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.physics.PhysicsWorld;
import com.ggoulis.game.components.EnemyComponent;

import static com.almasb.fxgl.dsl.FXGLForKtKt.inc;

public class PhysicsManager {
    private PhysicsWorld physicsWorld;

    public PhysicsManager(PhysicsWorld physicsWorld) {
        this.physicsWorld = physicsWorld;
    }

    public void initSettings() {
        physicsWorld.setGravity(0, 0);
        initCollisionHandlers();
    }

    private void initCollisionHandlers() {
        physicsWorld.addCollisionHandler(new CollisionHandler(EntityType.BULLET, EntityType.ENEMY) {
            @Override
            protected void onCollisionBegin(Entity bullet, Entity enemy) {
                bullet.removeFromWorld();

                boolean dead = enemy.getComponent(EnemyComponent.class).takeDamage(10);

                if(dead) inc("enemies", -1);
            }

        });

        physicsWorld.addCollisionHandler(new CollisionHandler(EntityType.BULLET, EntityType.WALL) {
            @Override
            protected void onCollisionBegin(Entity bullet, Entity enemy) {
                bullet.removeFromWorld();
            }
        });
    }


}
