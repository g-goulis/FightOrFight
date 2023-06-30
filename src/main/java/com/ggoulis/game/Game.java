package com.ggoulis.game;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.SceneFactory;

import com.almasb.fxgl.app.scene.StartupScene;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.UserAction;
import com.sun.media.jfxmedia.logging.Logger;
import javafx.scene.input.KeyCode;
import org.jetbrains.annotations.NotNull;

import static com.almasb.fxgl.dsl.FXGLForKtKt.*;

public class Game extends GameApplication {

    private Entity player;
    private PlayerComponent playerComponent;

    @Override
    protected void initSettings(GameSettings gameSettings) {
        gameSettings.setTitle("FightOrFIGHT");
        gameSettings.setVersion("1.1");
        gameSettings.setMainMenuEnabled(true);
        gameSettings.setWidth(800);
        gameSettings.setHeight(800);
        gameSettings.setSceneFactory(new SceneFactory() {
            @NotNull
            @Override
            public FXGLMenu newMainMenu() {
                return new StartMenu();
            }

            @NotNull
            @Override
            public FXGLMenu newGameMenu() {
                return super.newGameMenu();
            }
        });
    }

    @Override
    protected void initGame() {

        getGameWorld().addEntityFactory(new GameEntityFactory());
        player = getGameWorld().spawn("player");
        playerComponent = player.getComponent(PlayerComponent.class);
    }

    @Override
    protected void initInput() {
        getInput().addAction(new UserAction("Up") {
            @Override
            protected void onAction() {
                playerComponent.up();
            }

            @Override
            protected void onActionEnd() {
                playerComponent.stop();
            }
        }, KeyCode.W);

        getInput().addAction(new UserAction("Down") {
            @Override
            protected void onAction() {
                playerComponent.down();
            }

            @Override
            protected void onActionEnd() {
                playerComponent.stop();
            }
        }, KeyCode.S);

        getInput().addAction(new UserAction("Right") {
            @Override
            protected void onAction() {
                playerComponent.right();
            }

            @Override
            protected void onActionEnd() {
                playerComponent.stop();
            }
        }, KeyCode.D);

        getInput().addAction(new UserAction("Left") {
            @Override
            protected void onAction() {
                playerComponent.left();
                System.out.println("GOING Down");
            }

            @Override
            protected void onActionEnd() {
                playerComponent.stop();
            }
        }, KeyCode.A);

    }

    @Override
    protected void initPhysics() {
        getPhysicsWorld().setGravity(0, 0);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
