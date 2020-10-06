package com.cs360.chess.ui;

import com.cs360.chess.Game;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Menu extends Application {
    
    public static void main(String[] args) {
        launch(args);
    }
    private Game currentGame;

    @Override
    public void start(Stage stage) throws Exception {
        this.currentGame = new Game();
        stage.setTitle("Chess AI");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(20, 20, 20, 20));
        for (int column = 0; column < 8; column++) {
            for (int row = 0; row < 8; row++) {
                Rectangle square = new Rectangle();
                square.setFill(((column + row) % 2 == 0) ? Color.WHITE : Color.BLACK);
                DoubleBinding size = (DoubleBinding) Bindings.when(grid.widthProperty().greaterThan(grid.heightProperty())).then(grid.heightProperty().divide(8)).otherwise(grid.widthProperty().divide(8));
                square.widthProperty().bind(size);
                square.heightProperty().bind(size);
                grid.add(square, column, row);
            }
        }
        Scene loadingScreen = new Scene(grid, 300, 300);
        stage.setScene(loadingScreen);
        stage.show();
    }
    
}