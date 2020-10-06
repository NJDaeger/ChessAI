package com.cs360.chess.piece;

import com.cs360.chess.Board;

public final class Bishop extends Piece {

    public Bishop(boolean isBlack) {
        super(3, isBlack);
    }
    
    //diagonals, no jumping
    @Override
    public boolean[][] computePossible(Board board) {
        return new boolean[0][];
    }
}
