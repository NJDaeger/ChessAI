package com.cs360.chess.piece;

import com.cs360.chess.Board;

public final class Bishop extends Piece {

    public Bishop(boolean isBlack, int column, int row) {
        super(3, isBlack, 3, column, row);
    }

    @Override
    public Bishop clone() {
        return new Bishop(isBlack(), getColumn(), getRow());
    }

    @Override
    public int[][] computePossible(Board board) {
        return diagonalMoves(board, this);
    }
}
