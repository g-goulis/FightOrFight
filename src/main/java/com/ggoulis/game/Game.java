package com.ggoulis.game;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;

public class Game extends GameApplication {
    @Override
    protected void initSettings(GameSettings gameSettings) {
        gameSettings.setTitle("Pong");
        gameSettings.setVersion("1.0");
        gameSettings.setSceneFactory(new GameSceneFactory());

    }

    public static void main(String[] args) {
        launch(args);
    }
}
