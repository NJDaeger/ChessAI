package com.cs360.chess.piece;

import com.cs360.chess.Board;

public final class Bishop extends Piece {

    private static final int points = 3;

    public Bishop(boolean isBlack, int column, int row) {
        super(isBlack, column, row);
    }

    @Override
    public Bishop clone() {
        return new Bishop(isBlack(), getColumn(), getRow());
    }

    @Override
    public int getPoints() {
        return points;
    }

    @Override
    public int[][] computePossible(Board board) {
        if (board.isWhiteToMove() && isBlack()) return new int[0][0];
        return diagonalMoves(board, this);
    }

    @Override
    public String toString() {
        return "Bishop[black=" + isBlack() + ",points=" + getPoints() + ",moved=" + hasMoved() + ",col=" + getColumn() + ",row=" + getRow() + ",BINARY=" + Integer.toBinaryString(data) + "]";
    }
}
