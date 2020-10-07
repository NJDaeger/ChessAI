package com.cs360.chess.ui;

import com.cs360.chess.*;
import com.cs360.chess.piece.Piece;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Menu extends Application {
    
    public static void main(String[] args) {
        launch(args);
    }
    private Game currentGame;
    GridPane grid = new GridPane();
    @Override
    public void start(Stage stage) throws Exception {
        currentGame = new Game();
        stage.setTitle("Chess AI");

        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(20, 20, 20, 20));
        Label test = new Label("X");
        for (int column = 0; column < 8; column++) {
            for (int row = 0; row < 8; row++) {
                Rectangle square = new Rectangle();
                square.setFill(((column + row) % 2 == 0) ? Color.TAN : Color.MAROON);
                DoubleBinding size = (DoubleBinding) Bindings.when(grid.widthProperty().greaterThan(grid.heightProperty())).then(grid.heightProperty().divide(8)).otherwise(grid.widthProperty().divide(8));
                square.widthProperty().bind(size);
                square.heightProperty().bind(size);
                grid.add(square, column, row);
            }
        }
        Board temp = currentGame.getCurrentBoard();
        update(temp);
        Scene loadingScreen = new Scene(grid, 300, 300);
        stage.setScene(loadingScreen);
        stage.show();
    }

    //updates the GUI
    void update(Board board){
        for(int column=0;column<8;column++){
            for(int row=0;row<8;row++){
                Label piece = new Label("x");
                Piece[][] tempBoard = board.getBoard();
                if(tempBoard[column][row]!=null){
                    if(tempBoard[column][row].isBlack()){
                        piece.setTextFill(Color.BLACK);
                    }
                    else{
                        piece.setTextFill(Color.WHITE);
                    }
                    grid.add(piece,column,row);
                }

            }
        }
    }
    
}
