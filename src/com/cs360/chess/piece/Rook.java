package com.cs360.chess.piece;

import com.cs360.chess.Board;

public final class Rook extends Piece {

    public Rook(boolean isBlack, int column, int row) {
        super(isBlack, 5, column, row);
    }

    @Override
    public Rook clone() {
        return new Rook(isBlack(), getColumn(), getRow());
    }

    //forward, backward, left, or right in any direction at any amount as long as there no pieces in the way
    @Override
    public int[][] computePossible(Board board) {
        return straightMoves(board, this);
    }

    @Override
    public String toString() {
        return "Rook[black=" + isBlack() + ",points=" + getPoints() + ",moved=" + hasMoved() + ",col=" + getColumn() + ",row=" + getRow() + ",BINARY=" + Integer.toBinaryString(data) + "]";
    }

}
