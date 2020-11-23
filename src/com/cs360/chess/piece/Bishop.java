package com.cs360.chess.piece;

import com.cs360.chess.Board;

public final class Bishop extends Piece {
    int value = 3;
    public Bishop(boolean isBlack) {
        super(3, isBlack, 3);
    }

    @Override
    public int[][] computePossible(Board board, int column, int row) {
        return diagonalMoves(board, column, row, this);
    }
}
