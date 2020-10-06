package com.cs360.chess.piece;

import com.cs360.chess.Board;

public final class Queen extends Piece {
    
    public Queen(boolean isBlack) {
        super(5, isBlack);
    }
    
    //can move in any (forward, backward, left, right, diagonal) direction at any distance as long as the path is clear
    @Override
    public boolean[][] computePossible(Board board) {
        return new boolean[0][];
    }
}
