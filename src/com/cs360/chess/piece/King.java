package com.cs360.chess.piece;

import com.cs360.chess.Board;

public final class King extends Piece {
    
    public King(boolean isBlack) {
        super(4, isBlack);
    }
    
    @Override
    public boolean[][] computePossible(Board board) {
        return new boolean[0][];
    }
}
