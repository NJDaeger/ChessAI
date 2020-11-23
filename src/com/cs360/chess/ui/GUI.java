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
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

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

        //Tile grid holding the colored tiles of the board.
        tileGrid = new GridPane();
        //Piece grid holding the pieces
        pieceGrid = new GridPane();
        //Click grid where the user interacts with the board and where tiles are highlighted from
        clickGrid = new GridPane();
        //Stack pane which compiles each of the grids together in one object.
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
        
        //General alignment/placement of the grids
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

        //Generating all the tiles on the tile grid.
        for (int column = 0; column < 8; column++) {
            for (int row = 0; row < 8; row++) {

                //We create a rectangle which is to be filled with the correct color to create a checkered pattern.
//                TileView tile = new TileView(column,row);
                Rectangle tile = new Rectangle();
                tile.setFill(((column + row) % 2 == 0) ? Color.TAN : Color.MAROON);

                //Setting the bindings and adding the tile
                tile.widthProperty().bind(size);
                tile.heightProperty().bind(size);
                tileGrid.add(tile, column, row);
                //tiles[column][row] = tile;

                ClickView clickableTile = new ClickView(column, row);
                clickableTile.setOpacity(0);
                clickableTile.addEventHandler(MouseEvent.MOUSE_CLICKED, tileClickEvent);
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
                    ImageView img = new ImageView(IconMap.getIcon(tempBoard[column][row])/*, tempBoard[column][row], column, row*/);

                    //Setting the bindings and adding the tile
                    img.fitWidthProperty().bind(size);
                    img.fitHeightProperty().bind(size);
                    pieceGrid.add(img, column, row);
                } else {
                    //Fill the spot with an empty and invisible tile if there is no piece in it.
//                    TileView clickableTile = new TileView(column, row);
                    Rectangle emptySpot = new Rectangle();
                    emptySpot.setOpacity(0);
                    emptySpot.widthProperty().bind(size);
                    emptySpot.heightProperty().bind(size);
                    pieceGrid.add(emptySpot, column, row);
                }

            }
        }
    }

    //event Handlers
    EventHandler<MouseEvent> tileClickEvent = event -> {

        //remove everything to do with the last set of moves, wipe all markers
        clickGrid.getChildren().stream().filter(cell -> cell.getOpacity() == 1).forEach(cell -> cell.setOpacity(0));
        int[] location = currentGame.getSelectedLocation(); //Possible current selected location
//        int column = ((ClickView) event.getSource()).getColumn(); //The column of the spot just clicked
//        int row = ((ClickView) event.getSource()).getRow(); //The row of the spot just picked

        int column = ((ClickView) event.getSource()).getColumn();
        int row = ((ClickView) event.getSource()).getRow();

        if (location != null) {
            Piece piece = currentGame.getCurrentBoard().getPieceAt(location[0], location[1]);
            if (isValidSpot(piece.computePossible(currentGame.getCurrentBoard(), location[0], location[1]), column, row) && (piece.isBlack()!=currentGame.getCurrentBoard().isWhiteToMove())) {
                currentGame.getCurrentBoard().movePiece(location[0], location[1], column, row);
                update(currentGame.getCurrentBoard());
                currentGame.setSelectedLocation(null);
                return;
            }
            else currentGame.setSelectedLocation(null);
        }

        Node selection = getCell(pieceGrid, column, row);
        if (selection instanceof ImageView) {
            currentGame.setSelectedLocation(new int[]{column, row});
            location = currentGame.getSelectedLocation();
        }
        else {
            currentGame.setSelectedLocation(null);
            return;
        }

        //for now we will use a temp possible move to do this........
        int[][] posMoves = currentGame.getCurrentBoard().getPieceAt(location[0], location[1]).computePossible(currentGame.getCurrentBoard(), column, row);

        //For all possible moves for the currently selected piece, we just set the opacity of the clickGrid cell to 1 so it outlines the cell.
        for(int[] coord : posMoves) {
            Node cell = getCell(clickGrid, coord[0], coord[1]);
            cell.setOpacity(1); //Ignore this check, it will not be null unless we screw up
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
