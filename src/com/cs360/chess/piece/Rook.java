package com.cs360.chess.piece;

import com.cs360.chess.Board;

public final class Rook extends Piece {

    public Rook(boolean isBlack) {
        super(1, isBlack, 50);
    }

    //forward, backward, left, or right in any direction at any amount as long as there no pieces in the way
    @Override
    public int[][] computePossible(Board board, int column, int row) {
        return new int[0][];
    }
}
