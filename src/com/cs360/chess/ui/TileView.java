package com.cs360.chess.ui;

import com.cs360.chess.piece.Piece;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

public class TileView extends Rectangle {
    int col, row;
    TileView(int col,int row){
        super();
        this.row=row;
        this.col=col;

    }
}
