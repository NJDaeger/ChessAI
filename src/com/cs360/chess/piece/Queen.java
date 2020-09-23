package com.cs360.chess.piece;

import com.cs360.chess.Board;

public final class Queen extends Piece {
    
    public Queen(boolean isBlack) {
        super(5, isBlack);
    }
    
    @Override
    public boolean[][] computePossible(Board board) {
        return new boolean[0][];
    }
}
