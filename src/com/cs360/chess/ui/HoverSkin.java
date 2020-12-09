package com.cs360.chess.ui;

import javafx.animation.ScaleTransition;
import javafx.scene.control.Button;
import javafx.scene.control.skin.ButtonSkin;
import javafx.util.Duration;

public class HoverSkin extends ButtonSkin {

    public HoverSkin(Button control) {
        super(control);

        ScaleTransition scale = new ScaleTransition(Duration.millis(100));
        scale.setNode(control);
        scale.setByX(.02f);
        scale.setByY(.02f);
        scale.setCycleCount(1);
        scale.setAutoReverse(true);

        ScaleTransition scaleDown = new ScaleTransition(Duration.millis(100));
        scaleDown.setNode(control);
        scaleDown.setByX(-.02f);
        scaleDown.setByY(-.02f);
        scaleDown.setCycleCount(1);
        scaleDown.setAutoReverse(true);

        control.setOnMouseEntered(e -> scale.play());
        control.setOnMouseExited(e -> scaleDown.play());

    }
}
