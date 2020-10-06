package com.cs360.chess.piece;

import com.cs360.chess.Board;

public final class Rook extends Piece {

    public Rook(boolean isBlack) {
        super(1, isBlack);
    }

    //forward, backward, left, or right in any direction at any amount as long as there no pieces in the way
    @Override
    public boolean[][] computePossible(Board board) {
        return new boolean[0][];
    }
}
