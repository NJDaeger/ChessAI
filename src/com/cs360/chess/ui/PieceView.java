package com.cs360.chess.ui;

import com.cs360.chess.piece.Piece;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

//GUI object for piece images
public class PieceView extends ImageView {
    Piece piece;
    int col,row;
    PieceView(Image img, Piece piece,int col,int row){
        super(img);
        this.piece = piece;
        this.row=row;
        this.col=col;

    }
}
