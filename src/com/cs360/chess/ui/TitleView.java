package com.cs360.chess.ui;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class TitleView extends Application {
    private BorderPane titleBorderPane;
    private Button playButton;
    private GridPane buttonGrid;
    private StackPane titleStack;
    private Button loadButton;
    private Button exitButton;

    @Override
    public void start(Stage stage) throws Exception {
        playButton = new Button("Play Game");
        loadButton = new Button("Load Game");
        exitButton = new Button("Exit Game");
        titleBorderPane = new BorderPane();
        titleStack = new StackPane();
        titleStack.setId("pane");
        buttonGrid = new GridPane();
        stage.setHeight(600);
        stage.setWidth(600);
        stage.setTitle("Chess AI");
        Scene titleScene = new Scene(titleBorderPane);
        titleScene.getStylesheets().add(this.getClass().getResource("StyleSheet.css").toExternalForm());
        playButton.getStyleClass().add("titleButton");
        loadButton.getStyleClass().add("titleButton");
        exitButton.getStyleClass().add("titleButton");
        buttonGrid.setHgap(15);
        buttonGrid.add(playButton, 0, 0);
        buttonGrid.add(loadButton, 1, 0);
        buttonGrid.add(exitButton, 2, 0);
        buttonGrid.setAlignment(Pos.CENTER);
        titleStack.getChildren().add(buttonGrid);
        titleBorderPane.setCenter(titleStack);
        stage.setScene(titleScene);
        stage.show();
    }
}
//