package com.cs360.chess.piece;

import com.cs360.chess.Board;

public final class King extends Piece {
    
    public King(boolean isBlack) {
        super(4, isBlack);
    }
    
    //all directions, max of 1 space at a time
    @Override
    public int[][] computePossible(Board board, int column, int row) {
        return new int[0][];
    }
}
