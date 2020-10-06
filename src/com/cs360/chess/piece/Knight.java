package com.cs360.chess.piece;

import com.cs360.chess.Board;

public final class Knight extends Piece {
    
    public Knight(boolean isBlack) {
        super(2, isBlack);
    }
    
    // L L L L L L L L L
    //can skip
    @Override
    public boolean[][] computePossible(Board board) {
        return new boolean[0][];
    }
}
