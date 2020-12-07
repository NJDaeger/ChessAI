package com.cs360.chess.piece;

import com.cs360.chess.Board;

public final class Bishop extends Piece {

    private static final int points = 3;
    private static final int id = 3;

    public Bishop(boolean isBlack, int column, int row) {
        super(isBlack, column, row);
    }

    public Bishop(boolean black, boolean hasMoved, int column, int row) {
        super(black, hasMoved, column, row);
    }

    @Override
    public Bishop clone() {
        return new Bishop(isBlack(), hasMoved(), getColumn(), getRow());
    }

    @Override
    public int getPoints() {
        return points;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public int[][] computePossible(Board board) {
        if (isBlack()) {
            if (!board.isWhiteToMove() && board.isBlackInCheck()) return new int[0][0];
        } else {
            if (board.isWhiteToMove() && board.isWhiteInCheck()) return new int[0][0];
        }
        return diagonalMoves(board, this);
    }

    @Override
    public String toString() {
        return "Bishop[black=" + isBlack() + ",points=" + getPoints() + ",moved=" + hasMoved() + ",col=" + getColumn() + ",row=" + getRow() + ",BINARY=" + Integer.toBinaryString(data) + "]";
    }
}
