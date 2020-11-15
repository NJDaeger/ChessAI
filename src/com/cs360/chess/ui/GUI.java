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
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Arrays;

public class GUI extends Application {
    
    public static void main(String[] args) {
        launch(args);
    }
    


    private Game currentGame;

    //FX stuff
    private GridPane tileGrid;
    private GridPane clickGrid;
    private GridPane pieceGrid;
    private StackPane boardStack;

    //private final TileView[][] tiles =new TileView[8][8];
    private BorderPane borderPane;

    private MenuBar menuBar = new MenuBar();
    private Menu game = new Menu("Game");
    private MenuItem flipBoard = new MenuItem("Flip Board");
    private MenuItem newGame = new MenuItem("New Game");
    private MenuItem restart = new MenuItem("Restart");
    private MenuItem save = new MenuItem("Save");
    private MenuItem quit = new MenuItem("Quit");

    private Menu exit = new Menu("Exit");

    private Menu difficulty = new Menu("Adjust Difficulty");
    private MenuItem easy= new MenuItem("Beginner");
    private MenuItem normal= new MenuItem("Casual");
    private MenuItem hard= new MenuItem("Intermediate");
    private MenuItem master= new MenuItem("Master");

    //We use bindings to make the rectangles automatically adjust to window size changes.
    //Since the board most be square, we must use a conditional binding.
    //If the width is larger than the height, the height property divided by 8 is used as the length of each side of the square
    //If the height is larger than the width, the width property divided by 8 is used as the length of each side of the square
    private ReadOnlyDoubleProperty widthProp;
    private DoubleBinding heightProp;
    private DoubleBinding size;

    @Override
    public void start(Stage stage) throws Exception {
        SvgImageLoaderFactory.install(); //Required so we can load SVG images in the program.
        IconMap.loadIcons(); //Loading our icons

        borderPane = new BorderPane();
        currentGame = new Game();

        tileGrid = new GridPane();
        pieceGrid = new GridPane();
        clickGrid = new GridPane();
        boardStack = new StackPane();

        //Set a base size for the stage
        stage.setHeight(600);
        stage.setWidth(600);
        stage.setTitle("Chess AI");

        //Adding the menubar to the borderpane.
        difficulty.getItems().addAll(easy,normal,hard,master);
        game.getItems().addAll(save,flipBoard,restart,quit);
        difficulty.getItems().addAll(easy,normal,hard,master);
        menuBar.getMenus().addAll(game,difficulty,exit);

        borderPane = new BorderPane();
        borderPane.setTop(menuBar);
        
        //General alignment/placement of the playing grid
        tileGrid.setAlignment(Pos.CENTER);
        pieceGrid.setAlignment(Pos.CENTER);
        clickGrid.setAlignment(Pos.CENTER);
        boardStack.getChildren().add(tileGrid);
        boardStack.getChildren().add(pieceGrid);
        boardStack.getChildren().add(clickGrid);
        borderPane.setCenter(boardStack);

        widthProp = borderPane.widthProperty();
        heightProp = borderPane.heightProperty().subtract(menuBar.heightProperty());
        size = (DoubleBinding) Bindings.when(widthProp.greaterThan(heightProp)).then(heightProp.divide(8)).otherwise(widthProp.divide(8));

        //Generating all the tiles on the grid.
        for (int column = 0; column < 8; column++) {
            for (int row = 0; row < 8; row++) {

                //We create a rectangle which is to be filled with the correct color to create a checkered pattern.
                TileView tile = new TileView(column,row);
                tile.setFill(((column + row) % 2 == 0) ? Color.TAN : Color.MAROON);

                //Setting the bindings and adding the tile
                tile.widthProperty().bind(size);
                tile.heightProperty().bind(size);
                tileGrid.add(tile, column, row);
                //tiles[column][row] = tile;

                TileView clickableTile = new TileView(column, row);
                clickableTile.setOpacity(0);
                clickableTile.addEventHandler(MouseEvent.MOUSE_CLICKED, selectPiece);
                clickableTile.widthProperty().bind(size);
                clickableTile.heightProperty().bind(size);
                clickGrid.add(clickableTile, column, row);
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
        pieceGrid.getChildren().clear();
        Piece[][] tempBoard = board.getBoard();

        for(int column=0;column<8;column++){
            for(int row=0;row<8;row++){

                //We iterate through the board and find spaces where a piece exists.
                if(tempBoard[column][row]!=null){

                    //We get the SVG icon of the given piece and load it in an ImageView node.
                    PieceView img = new PieceView(IconMap.getIcon(tempBoard[column][row]), tempBoard[column][row], column, row);

                    //Setting the bindings and adding the tile
                    img.fitWidthProperty().bind(size);
                    img.fitHeightProperty().bind(size);
                    pieceGrid.add(img, column, row);
                } else {
                    //Fill the spot with an empty and invisible tile if there is no piece in it.
                    TileView clickableTile = new TileView(column, row);
                    clickableTile.setOpacity(0);
                    clickableTile.widthProperty().bind(size);
                    clickableTile.heightProperty().bind(size);
                    pieceGrid.add(clickableTile, column, row);
                }

            }
        }
    }

    void clearEvents(){
        tileGrid.getChildren().removeIf(node -> node instanceof Label);
    }

    //event Handlers
    EventHandler<MouseEvent> selectPiece = event -> {

        //remove everything to do with the last set of moves, wipe all markers
        clearEvents();

        int[] location = currentGame.getSelectedLocation(); //Possible current selected location
        int column = ((TileView) event.getSource()).col; //The column of the spot just clicked
        int row = ((TileView) event.getSource()).row; //The row of the spot just picked

        if (location != null) {
            Piece piece = currentGame.getCurrentBoard().getPieceAt(location[0], location[1]);
            if (isValidSpot(piece.computePossible(currentGame.getCurrentBoard(), location[0], location[1]), column, row)) {
                currentGame.getCurrentBoard().movePiece(location[0], location[1], column, row);
                update(currentGame.getCurrentBoard());
                currentGame.setSelectedLocation(null);
            }
            return;
        }

        Node sel = getCell(pieceGrid, column, row);
        if (sel instanceof PieceView) {
            currentGame.setSelectedLocation(new int[]{column, row});
            location = currentGame.getSelectedLocation();
        }
        else {
            currentGame.setSelectedLocation(null);
            return;
        }

        /*
        add possibleMove->{} event handler to the tiles at the coordinate,
        if the tile has an enemy piece... then add the event handler to the piece and the tile..
        the plan is to call calcPossible() on the PieceViews piece, and loop through the returned array to
        get the coords of each possible move. then return the children of the corresponding gridpane pane and add the
        event child to those objects
        */

        //for now we will use a temp possible move to do this........
        int[][] posMoves = currentGame.getCurrentBoard().getPieceAt(location[0], location[1]).computePossible(currentGame.getCurrentBoard(), column, row);

        for(int[] coord : posMoves){
            Label x = new Label("X");
            if(coord[0]==-1)continue;

            tileGrid.add(x,coord[0],coord[1]);
//            tiles[coord[0]][coord[1]].addEventHandler(MouseEvent.MOUSE_CLICKED,moveableTile);
        }
    };

    private static Node getCell(GridPane gridPane, int column, int row) {
        for (Node node : gridPane.getChildren()) {
            if (GridPane.getColumnIndex(node) == column && GridPane.getRowIndex(node) == row) {
                return node;
            }
        }
        return null;
    }

    private static boolean isValidSpot(int[][] possibleMoves, int column, int row) {
        for (int[] location : possibleMoves) {
            if (location[0] == column && location[1] == row) return true;
        }
        return false;
    }

}
