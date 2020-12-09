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

public class TitleView extends Application {
    private BorderPane titleBorderPane;
    private GridPane centerGrid;
//    private GridPane buttonGrid;
    private StackPane titleStack;
    private Button twoPlayerButton;
    private Button loadButton;
    private Button exitButton;
    private Button easyButton;
    private Button normalButton;
    private Button hardButton;
    private Button masterButton;
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
    public HBox addHBox2(){
        HBox hbox = new HBox();
        hbox.setSpacing(10);
        twoPlayerButton = new Button("Play 2 Player Game");
        twoPlayerButton.getStyleClass().add("titleButton");
        hbox.getChildren().addAll(twoPlayerButton);
        hbox.setAlignment(Pos.CENTER);
        return hbox;
    }

    public HBox addHBox3(){
        HBox hbox = new HBox();
        hbox.setSpacing(10);
        easyButton = new Button("Play Easy Game");
        easyButton.getStyleClass().add("titleButton");
        easyButton.setPrefWidth(350);
        normalButton = new Button("Play Normal Game");
        normalButton.getStyleClass().add("titleButton");
        normalButton.setPrefWidth(350);
        hbox.getChildren().addAll(easyButton, normalButton);
        hbox.setAlignment(Pos.CENTER);
        return hbox;
    }

    public HBox addHBox4(){
        HBox hbox = new HBox();
        hbox.setSpacing(10);
        hardButton = new Button("Play Hard Game");
        hardButton.getStyleClass().add("titleButton");
        hardButton.setPrefWidth(350);
        masterButton = new Button("Player Master Game");
        masterButton.getStyleClass().add("titleButton");
        masterButton.setPrefWidth(350);
        hbox.getChildren().addAll(hardButton, masterButton);
        hbox.setAlignment(Pos.CENTER);
        return hbox;
    }

    public HBox addHBox5() {
        HBox hbox = new HBox();
        hbox.setSpacing(10);
        loadButton = new Button("Load Game");
        loadButton.getStyleClass().add("titleButton");
        loadButton.setPrefWidth(200);
        exitButton = new Button("Exit Game");
        exitButton.getStyleClass().add("titleButton");
        exitButton.setPrefWidth(200);
        hbox.getChildren().addAll(loadButton, exitButton);
        hbox.setAlignment(Pos.CENTER);
        return hbox;
    }
    //constructor for v-box
    public VBox addVBox(){
        VBox vbox = new VBox();
        vbox.setSpacing(10);
        vbox.getChildren().addAll(addHBox1(), addHBox2(), addHBox3(), addHBox4(), addHBox5());
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
        twoPlayerButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //TODO merge GUIs to allow scene selection/game starting
                //Initiate a new game and switch scenes, no AI usage
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

        //Action event handling for menu bar items
        easyButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //TODO allow depth to be passed when a game is started
                //Set the depth to be an easy difficulty
//                currentGame.setDepth(2);
            }
        });

        normalButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //TODO allow depth to be passed when a game is started
                //Set the depth to be a normal difficulty
//                currentGame.setDepth(3);
            }
        });

        hardButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //TODO allow depth to be passed when a game is started
                //Set the depth to be a hard difficulty
//                currentGame.setDepth(4);
            }
        });

        masterButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //TODO allow depth to be passed when a game is started
                //Set the depth to be a master difficulty
//                currentGame.setDepth(5);
            }
        });

        stage.setScene(titleScene);
        stage.show();
    }
}
//