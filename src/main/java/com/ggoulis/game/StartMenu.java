package com.ggoulis.game;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import javafx.scene.text.Text;
import org.jetbrains.annotations.NotNull;

public class StartMenu extends FXGLMenu {
    private static final int SIZE = 150;


    public StartMenu() {
        super(MenuType.MAIN_MENU);
        getContentRoot().setTranslateX(getAppWidth() / 2.0 - SIZE);
        getContentRoot().setTranslateY( getAppHeight() / 2.0 - SIZE);


        Text uiText = new Text("Hello World");


        getContentRoot().getChildren().add(uiText);

    }
}
