package com.ggoulis.game;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.SceneFactory;

public class GameSceneFactory extends SceneFactory {

    @Override
    public FXGLMenu newMainMenu() {
        return new StartMenu();
    }

//    @Override
//    public LoadingScene newLoadingScene() {
//        return new StartMenu();
//    }


}