package com.ggoulis.game;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.SceneFactory;

import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.level.Level;
import com.almasb.fxgl.entity.level.text.TextLevelLoader;
import com.almasb.fxgl.input.UserAction;
import com.ggoulis.game.components.PlayerComponent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.text.Text;
import javafx.util.Duration;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

import static com.almasb.fxgl.dsl.FXGLForKtKt.*;

public class Game extends GameApplication {

    private Entity player;
    private Text uiText;
    private PlayerComponent playerComponent;

    private UIManager uiManager;

    private PhysicsManager physicsManager;

    @Override
    protected void initSettings(GameSettings gameSettings) {
        gameSettings.setTitle("FightOrFIGHT");
        gameSettings.setVersion("1.1");
        gameSettings.setMainMenuEnabled(true);
        gameSettings.setWidth(800);
        gameSettings.setHeight(800);
//        gameSettings.setProfilingEnabled(true);

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


        Level level = getAssetLoader().loadLevel("level.txt", new TextLevelLoader(30, 30, '0'));

        getGameWorld().setLevel(level);

        player = getGameWorld().spawn("player", getAppHeight() / 2, getAppWidth() / 2);
        playerComponent = player.getComponent(PlayerComponent.class);
        getGameScene().getViewport().bindToEntity(player, getAppHeight() / 2, getAppWidth() / 2);

        getGameTimer().runAtInterval(() -> {

            int numEnemies = geti("enemies");

            if (numEnemies < 5) {
                spawn("enemy",
                        FXGLMath.random(0, getAppWidth() - 40),
                        FXGLMath.random(0, getAppHeight() / 2 - 40)
                );

                inc("enemies", +1);
            }

        }, Duration.seconds(1));

    }


    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put("enemies", 0);
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
            }

            @Override
            protected void onActionEnd() {
                playerComponent.stop();
            }
        }, KeyCode.A);

        getInput().addAction(new UserAction("Dash") {
            @Override
            protected void onAction() {
                playerComponent.dash();
            }

            @Override
            protected void onActionEnd() {
//                playerComponent.stop();
            }
        }, KeyCode.SPACE);

        getInput().addAction(new UserAction("Shoot") {
            @Override
            protected void onActionBegin() {
                SpawnData data = new SpawnData(player.getX(), player.getY());
                data.put("mouseX", getInput().getMouseXWorld() - player.getX());
                data.put("mouseY", getInput().getMouseYWorld() - player.getY());
                getGameWorld().spawn("bullet", data);
            }
        }, MouseButton.PRIMARY);

    }

    @Override
    protected void initPhysics() {
        physicsManager = new PhysicsManager(getPhysicsWorld());
        physicsManager.initSettings();
    }

    @Override
    protected void initUI() {
        uiManager = new UIManager(getGameScene(), player);

        uiManager.drawHealthBar();

        uiManager.setVisible(true);
    }

    @Override
    protected void onUpdate(double tpf) {
//        uiManager.updateUI();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
