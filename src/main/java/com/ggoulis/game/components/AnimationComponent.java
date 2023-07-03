package com.ggoulis.game.components;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import javafx.geometry.Point2D;
import javafx.util.Duration;

public class AnimationComponent extends Component {
    private AnimatedTexture texture;
    private AnimationChannel animIdle, animWalk;

    public AnimationComponent() {
        animIdle = new AnimationChannel(FXGL.image("downidle.png"), 4, 64, 64, Duration.seconds(1), 0, 4);
        animWalk = new AnimationChannel(FXGL.image("sidewalk.png"), 4, 64, 64, Duration.seconds(1), 0, 5);

        texture = new AnimatedTexture(animIdle);
    }

    @Override
    public void onAdded() {
        entity.getTransformComponent().setScaleOrigin(new Point2D(0, 0));
        entity.getViewComponent().addChild(texture);
        texture.loopAnimationChannel(animIdle);
    }

    @Override
    public void onUpdate(double tpf) {

        if (entity.getComponent(PhysicsComponent.class).getVelocityX() == 0
                && entity.getComponent(PhysicsComponent.class).getVelocityY() == 0
                && texture.getAnimationChannel() != animIdle) {
            texture.loopAnimationChannel(animIdle);

        } else if((entity.getComponent(PhysicsComponent.class).getVelocityX() != 0
                || entity.getComponent(PhysicsComponent.class).getVelocityY() != 0)
                && texture.getAnimationChannel() != animWalk){
            texture.loopAnimationChannel(animWalk);
        }
    }


}
