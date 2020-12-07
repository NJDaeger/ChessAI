package com.cs360.chess.piece;

import com.cs360.chess.Board;

public final class Queen extends Piece {

    private static final int points = 9;
    private static final int id = 5;

    public Queen(boolean isBlack, int column, int row) {
        super(isBlack, column, row);
    }

    public Queen(boolean black, boolean hasMoved, int column, int row) {
        super(black, hasMoved, column, row);
    }

    @Override
    public Piece clone() {
        return new Queen(isBlack(), hasMoved(), getColumn(), getRow());
    }

    @Override
    public int getPoints() {
        return points;
    }

    @Override
    public int getId() {
        return id;
    }

    //can move in any (forward, backward, left, right, diagonal) direction at any distance as long as the path is clear
    @Override
    public int[][] computePossible(Board board) {
        if (isBlack()) {
            if (!board.isWhiteToMove() && board.isBlackInCheck()) return new int[0][0];
        } else {
            if (board.isWhiteToMove() && board.isWhiteInCheck()) return new int[0][0];
        }
        int[][] xPattern = diagonalMoves(board, this);
        int[][] plusPattern = straightMoves(board, this);
        int[][] moves = new int[xPattern.length + plusPattern.length][2];
        System.arraycopy(xPattern, 0, moves, 0, xPattern.length);
        System.arraycopy(plusPattern, 0, moves, xPattern.length, plusPattern.length);
        return moves;
    }

    @Override
    public String toString() {
        return "Queen[black=" + isBlack() + ",points=" + getPoints() + ",moved=" + hasMoved() + ",col=" + getColumn() + ",row=" + getRow() + ",BINARY=" + Integer.toBinaryString(data) + "]";
    }

}
