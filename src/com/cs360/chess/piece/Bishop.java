package com.cs360.chess.piece;

import com.cs360.chess.Board;

public final class Bishop extends Piece {

    public Bishop(boolean isBlack) {
        super(3, isBlack, 30);
    }

    @Override
    public int[][] computePossible(Board board, int column, int row) {
        return diagonalMoves(board, column, row, this);
    }
}
