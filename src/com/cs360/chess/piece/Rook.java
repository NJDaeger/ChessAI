package com.cs360.chess.piece;

import com.cs360.chess.Board;

public final class Rook extends Piece {

    private static final int points = 5;
    private static final int id = 1;

    public Rook(boolean isBlack, int column, int row) {
        super(isBlack, column, row);
    }

    public Rook(boolean black, boolean hasMoved, int column, int row) {
        super(black, hasMoved, column, row);
    }

    @Override
    public Rook clone() {
        return new Rook(isBlack(), hasMoved(), getColumn(), getRow());
    }

    @Override
    public int getPoints() {
        return points;
    }

    @Override
    public int getId() {
        return id;
    }

    //forward, backward, left, or right in any direction at any amount as long as there no pieces in the way
    @Override
    public int[][] computePossible(Board board) {
        /*if (isBlack()) {
            if (!board.isWhiteToMove() && board.isBlackInCheck()) return new int[0][0];
        } else {
            if (board.isWhiteToMove() && board.isWhiteInCheck()) return new int[0][0];
        }*/
        return straightMoves(board, this);
    }

    @Override
    public String toString() {
        return "Rook[black=" + isBlack() + ",points=" + getPoints() + ",moved=" + hasMoved() + ",col=" + getColumn() + ",row=" + getRow() + ",BINARY=" + Integer.toBinaryString(data) + "]";
    }

}
