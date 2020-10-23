package com.cs360.chess.piece;

import com.cs360.chess.Board;

public final class Knight extends Piece {
    
    public Knight(boolean isBlack) {
        super(2, isBlack);
    }
    
    // L L L L L L L L L
    //can skip
    @Override
    public int[][] computePossible(Board board, int column, int row) {
        int direction = isBlack() ? 1 : -1;
        int[][] moves;

        return new int[0][];
    }
}
