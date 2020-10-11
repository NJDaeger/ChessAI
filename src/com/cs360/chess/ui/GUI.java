package com.cs360.chess.ui;

import com.cs360.chess.Board;
import com.cs360.chess.Game;
import com.cs360.chess.IconMap;
import com.cs360.chess.piece.Piece;
import de.codecentric.centerdevice.javafxsvg.SvgImageLoaderFactory;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
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
        SvgImageLoaderFactory.install(); //Required so we can load SVG images in the program.
        IconMap.loadIcons(); //Loading our icons
    
        borderPane = new BorderPane();
        currentGame = new Game();
        grid = new GridPane();
    
        //Set a base size for the stage
        stage.setHeight(600);
        stage.setWidth(600);
        stage.setTitle("Chess AI");
        
        //Adding the menubar to the borderpane.
        difficulty.getItems().addAll(easy,normal,hard,master);
        game.getItems().addAll(save,flipBoard,restart,quit);
        menuBar.getMenus().addAll(game,difficulty,exit);
        borderPane.setTop(menuBar);
        
        //General alignment/placement of the playing grid
        grid.setAlignment(Pos.CENTER);
        borderPane.setCenter(grid);
        
        //Generating all the tiles on the grid.
        for (int column = 0; column < 8; column++) {
            for (int row = 0; row < 8; row++) {
                
                //We create a rectangle which is to be filled with the correct color to create a checkered pattern.
                Rectangle square = new Rectangle();
                square.setFill(((column + row) % 2 == 0) ? Color.TAN : Color.MAROON);
                
                //We use bindings to make the rectangles automatically adjust to window size changes.
                //Since the board most be square, we must use a conditional binding.
                //If the width is larger than the height, the height property divided by 8 is used as the length of each side of the square
                //If the height is larger than the width, the width property divided by 8 is used as the length of each side of the square
                ReadOnlyDoubleProperty widthProp = borderPane.widthProperty();
                DoubleBinding heightProp = borderPane.heightProperty().subtract(menuBar.heightProperty());
                DoubleBinding size = (DoubleBinding) Bindings.when(widthProp.greaterThan(heightProp)).then(heightProp.divide(8)).otherwise(widthProp.divide(8));
                
                //Setting the bindings and adding the tile
                square.widthProperty().bind(size);
                square.heightProperty().bind(size);
                grid.add(square, column, row);
            }
        }
        
        //Update the board with the current board piece locations.
        update(currentGame.getCurrentBoard());
        
        //Create the scene and show it on the stage
        Scene mainScreen = new Scene(borderPane);
        stage.setScene(mainScreen);
        stage.show();
    }

    //updates the GUI
    void update(Board board){
        Piece[][] tempBoard = board.getBoard();
        
        for(int column=0;column<8;column++){
            for(int row=0;row<8;row++){
                
                //We iterate through the board and find spaces where a piece exists.
                if(tempBoard[column][row]!=null){
                    
                    //We get the SVG icon of the given piece and load it in an ImageView node.
                    ImageView img = new ImageView(IconMap.getIcon(tempBoard[column][row]));
    
                    //We use bindings to make the rectangles automatically adjust to window size changes.
                    //Since the board most be square, we must use a conditional binding.
                    //If the width is larger than the height, the height property divided by 8 is used as the length of each side of the square
                    //If the height is larger than the width, the width property divided by 8 is used as the length of each side of the square
                    ReadOnlyDoubleProperty widthProp = borderPane.widthProperty();
                    DoubleBinding heightProp = borderPane.heightProperty().subtract(menuBar.heightProperty());
                    DoubleBinding size = (DoubleBinding) Bindings.when(widthProp.greaterThan(heightProp)).then(heightProp.divide(8)).otherwise(widthProp.divide(8));
                    
                    //Setting the bindings and adding the tile
                    img.fitWidthProperty().bind(size);
                    img.fitHeightProperty().bind(size);
                    grid.add(img, column, row);
                }

            }
        }
    }
}
