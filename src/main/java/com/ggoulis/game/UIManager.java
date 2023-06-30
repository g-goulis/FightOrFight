package com.ggoulis.game;

import com.almasb.fxgl.app.scene.GameScene;
import com.almasb.fxgl.entity.Entity;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.HashSet;
import java.util.Set;

public class UIManager {
//    Rectangle healthBar;
//    Rectangle healthBarBorder;

    static final int HEALTH_BAR_WIDTH = 100;
    static final int HEALTH_BAR_HEIGHT = 20;

    Set<Node> nodes = new HashSet<>();
    GameScene gameScene;

    Entity player;

    public UIManager(GameScene gameScene, Entity player) {
        this.gameScene = gameScene;
        this.player = player;
    }


    public void updateUI() {
        updateZ();
        gameScene.getUINodes().forEach(node -> {
            node.setLayoutX(player.getRightX() - (HEALTH_BAR_WIDTH / 2));
            node.setLayoutY(player.getBottomY() - 10);
        });

    }

    public void updateZ() {
        gameScene.getUINodes().get(0).setTranslateZ(-5);
        gameScene.getUINodes().get(1).setTranslateZ(-1);
    }

    public void drawHealthBar() {
        double healthBarOffsetW = (HEALTH_BAR_WIDTH * .1);
        double healthBarOffsetH = (HEALTH_BAR_HEIGHT * .1);

        Rectangle healthBar = new Rectangle(HEALTH_BAR_WIDTH - healthBarOffsetW, HEALTH_BAR_HEIGHT - healthBarOffsetH, Color.RED);
        Rectangle healthBarBorder = new Rectangle(HEALTH_BAR_WIDTH, HEALTH_BAR_HEIGHT, Color.BLACK);

//        healthBar.setVisible(false);
//        healthBarBorder.setVisible(false);

        ObservableValue<Double> healthBarX = player.xProperty().subtract((HEALTH_BAR_WIDTH - healthBarOffsetW) / 2).add(player.getWidth() / 2).asObject();
        ObservableValue<Double> healthBarY = player.yProperty().add(player.getHeight()).add(10).add(healthBarOffsetH / 2).asObject();

        ObservableValue<Double> healthBarBorderX = player.xProperty().subtract(HEALTH_BAR_WIDTH / 2).add(player.getWidth() / 2).asObject();
        ObservableValue<Double> healthBarBorderY = player.yProperty().add(player.getHeight()).add(10).asObject();


        healthBar.layoutXProperty().bind(healthBarX);
        healthBar.layoutYProperty().bind(healthBarY);
        healthBar.setTranslateZ(5);

        healthBarBorder.layoutXProperty().bind(healthBarBorderX);
        healthBarBorder.layoutYProperty().bind(healthBarBorderY);
        healthBarBorder.setTranslateZ(10);


        gameScene.addUINodes(healthBarBorder, healthBar);
    }

    public void setVisible(boolean visible) {
        nodes.forEach(node -> {
            if(node.isVisible() && !visible) {
                node.setVisible(false);
            } else if (!node.isVisible() && visible) {
                node.setVisible(true);
            }
        });
    }
}
