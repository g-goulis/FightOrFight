package com.ggoulis.game;

import com.almasb.fxgl.app.scene.StartupScene;
import com.almasb.fxgl.dsl.FXGL;

public class Startup extends StartupScene {
    public Startup(int appWidth, int appHeight) {
        super(appWidth, appHeight);
        getContentRoot().getChildren().addAll(FXGL.getUIFactoryService().newButton("HELLO"));
    }
}
