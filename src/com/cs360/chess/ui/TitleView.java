package com.cs360.chess.ui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.awt.event.ActionListener;

public class TitleView extends Application{
    private BorderPane titleBorderPane;
    private Button playButton;
    private GridPane centerGrid;
//    private GridPane buttonGrid;
    private StackPane titleStack;
    private Button loadButton;
    private Button exitButton;
    private Label titleLabel;
    public static void main(String[] args) {
        launch(args);
    }
    //constructors for h-boxes
    public HBox addHBox1() {
        HBox hbox = new HBox();
        hbox.setSpacing(10);
        titleLabel = new Label("Chess");
        titleLabel.getStyleClass().add("titleLabel");
        hbox.getChildren().addAll(titleLabel);
        hbox.setAlignment(Pos.CENTER);
        return hbox;
    }
    public HBox addHBox2() {
        HBox hbox = new HBox();
        hbox.setSpacing(10);
        playButton = new Button("Play Game");
        playButton.getStyleClass().add("titleButton");
        loadButton = new Button("Load Game");
        loadButton.getStyleClass().add("titleButton");
        hbox.getChildren().addAll(playButton, loadButton);
        hbox.setAlignment(Pos.CENTER);
        return hbox;
    }
    public HBox addHBox3() {
        HBox hbox = new HBox();
        hbox.setSpacing(10);
        exitButton = new Button("Exit Game");
        exitButton.getStyleClass().add("titleButton");
        hbox.getChildren().addAll(exitButton);
        hbox.setAlignment(Pos.CENTER);
        return hbox;
    }
    //constructor for v-box
    public VBox addVBox(){
        VBox vbox = new VBox();
        vbox.setSpacing(10);
        vbox.getChildren().addAll(addHBox1(), addHBox2(), addHBox3());
        vbox.setAlignment(Pos.CENTER);
        return vbox;
    }
    @Override
    public void start(Stage stage) throws Exception {
        titleBorderPane = new BorderPane();
        titleStack = new StackPane();
        titleStack.setId("pane");
        stage.setHeight(800);
        stage.setWidth(800);
        stage.setTitle("Chess AI");
        stage.setResizable(false);
        Scene titleScene = new Scene(titleBorderPane);
        stage.setTitle("Chess");
        titleScene.getStylesheets().add(this.getClass().getResource("/StyleSheet.css").toExternalForm());
        centerGrid = new GridPane();
        centerGrid.add(addVBox(), 0, 1);
        centerGrid.setAlignment(Pos.CENTER);
        titleStack.getChildren().add(centerGrid);
        titleBorderPane.setCenter(titleStack);
        playButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //TODO merge GUIs to allow scene selection/game starting
                //Initiate a new game and switch scenes
            }
        });
        loadButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //TODO implement loading
                //Load a saved game and switch scenes if a valid game is chosen
            }
        });
        exitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //Exit the game
                System.exit(0);
            }
        });
        stage.setScene(titleScene);
        stage.show();
    }
}
//