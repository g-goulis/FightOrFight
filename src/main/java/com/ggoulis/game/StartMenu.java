package com.ggoulis.game;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.dsl.FXGL;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.effect.Bloom;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static com.almasb.fxgl.dsl.FXGL.*;
import static com.almasb.fxgl.dsl.FXGLForKtKt.getSettings;
import static com.almasb.fxgl.dsl.FXGLForKtKt.getUIFactoryService;

public class StartMenu extends FXGLMenu {

    public StartMenu() {
        super(MenuType.MAIN_MENU);

        getContentRoot().getChildren().setAll(new Rectangle(getAppWidth(), getAppHeight()));

        var title = getUIFactoryService().newText(getSettings().getTitle(), Color.WHITE, 46.0);
        title.setStroke(Color.WHITE);
        title.setStrokeWidth(1.5);

        if (!FXGL.isMobile()) {
            title.setEffect(new Bloom(0.6));
        }
        centerTextBind(title, getAppWidth() / 2.0, 200);

        var version = getUIFactoryService().newText(getSettings().getVersion(), Color.WHITE, 22.0);
        centerTextBind(version, getAppWidth() / 2.0, 220);

        getContentRoot().getChildren().addAll(title, version);

        var startButton = new Button("Start Game");
        var quitButton = new Button("Quit");
        startButton.setMinSize(200, 100);
        quitButton.setMinSize(200, 100);

        startButton.setOnAction(event -> {
            fireNewGame();
        });
        quitButton.setOnAction(event -> fireExit());



        var menuBox = new VBox(
                2,
                startButton,
                quitButton
        );
        menuBox.setAlignment(Pos.TOP_CENTER);

        menuBox.setTranslateX(getAppWidth() / 2.0 - 125);
        menuBox.setTranslateY(getAppHeight() / 2.0 + 125);


        getContentRoot().getChildren().addAll(menuBox);

    }
}
