package com.cs360.chess.piece;

import com.cs360.chess.Board;

public final class Bishop extends Piece {

    public Bishop(boolean isBlack) {
        super(3, isBlack, 30);
    }
    
    //diagonals, no jumping
    @Override
    public int[][] computePossible(Board board, int column, int row) {
        //{{1, 2}, {1, 2}, {1,2}} array of arrays containing 2 ints
        //for (int column)
        return new int[0][];
    }
}
