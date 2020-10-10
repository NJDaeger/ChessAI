package com.cs360.chess.ui;

import com.cs360.chess.*;
import com.cs360.chess.piece.Piece;
import de.codecentric.centerdevice.javafxsvg.SvgImageLoaderFactory;
import de.codecentric.centerdevice.javafxsvg.dimension.DimensionProvider;
import de.codecentric.centerdevice.javafxsvg.dimension.PrimitiveDimensionProvider;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class GUI extends Application {
    
    public static void main(String[] args) {
        launch(args);
    }
    


    private Game currentGame;

    //FX stuff
    private GridPane grid;
    private BorderPane borderPane;
    MenuBar menuBar = new MenuBar();
    Menu game = new Menu("Game");
    MenuItem flipBoard = new MenuItem("Flip Board");
    MenuItem newGame = new MenuItem("New Game");
    //MenuItem difficulty = new MenuItem("Adjust Difficulty");
    MenuItem restart = new MenuItem("Restart");
    MenuItem save = new MenuItem("Save");
    MenuItem quit = new MenuItem("Quit");

    Menu exit = new Menu("Exit");

    Menu difficulty = new Menu("Adjust Difficulty");
    MenuItem easy= new MenuItem("Beginner");
    MenuItem normal= new MenuItem("Casual");
    MenuItem hard= new MenuItem("Intermediate");
    MenuItem master= new MenuItem("Master");

    @Override
    public void start(Stage stage) throws Exception {
        SvgImageLoaderFactory.install();
        IconMap.loadIcons();
        currentGame = new Game();
        grid = new GridPane();
        game.getItems().addAll(save,flipBoard,restart,quit);
        difficulty.getItems().addAll(easy,normal,hard,master);
        menuBar.getMenus().addAll(game,difficulty,exit);

        borderPane = new BorderPane();
        borderPane.setTop(menuBar);
        
        stage.setTitle("Chess AI");
        grid.setAlignment(Pos.CENTER);
        //grid.setPadding(new Insets(20, 20, 20, 20));
        for (int column = 0; column < 8; column++) {
            for (int row = 0; row < 8; row++) {
                Rectangle square = new Rectangle();
                square.setFill(((column + row) % 2 == 0) ? Color.TAN : Color.MAROON);
                //DoubleBinding size = DoubleBinding(8);//(DoubleBinding) Bindings.when(grid.widthProperty().greaterThan(grid.heightProperty())).then(grid.heightProperty().divide(8)).otherwise(grid.widthProperty().divide(8));
                //square.widthProperty().bind(size);
                //square.heightProperty().bind(size);
                square.setWidth(40);
                square.setHeight(40);
                grid.add(square, column, row);
            }
        }

        Board temp = currentGame.getCurrentBoard();
        update(temp);
        grid.setPrefSize(320,320);
        borderPane.setCenter(grid);
        Scene loadingScreen = new Scene(borderPane);
        stage.setScene(loadingScreen);
        stage.show();
    }

    //updates the GUI
    void update(Board board){
        for(int column=0;column<8;column++){
            for(int row=0;row<8;row++){
                Piece[][] tempBoard = board.getBoard();
                if(tempBoard[column][row]!=null){
                    ImageView img = new ImageView(IconMap.getIcon(tempBoard[column][row]));
                    //img.fitWidthProperty().bind(Bindings.when(grid.widthProperty().greaterThan(grid.heightProperty())).then(grid.heightProperty().divide(8)).otherwise(grid.widthProperty().divide(8)));
                    //img.fitHeightProperty().bind(Bindings.when(grid.widthProperty().greaterThan(grid.heightProperty())).then(grid.heightProperty().divide(8)).otherwise(grid.widthProperty().divide(8)));
                    img.setFitHeight(40);
                    img.setFitWidth(40);
                    grid.add(img, column, row);
                }

            }
        }
    }
    
}
