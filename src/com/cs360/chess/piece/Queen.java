package com.cs360.chess.piece;

import com.cs360.chess.Board;

public final class Queen extends Piece {
    
    public Queen(boolean isBlack) {
        super(5, isBlack, 90);
    }
    
    //can move in any (forward, backward, left, right, diagonal) direction at any distance as long as the path is clear
    @Override
    public int[][] computePossible(Board board, int column, int row) {
        int[][] xPattern = diagonalMoves(board, column, row, this);
        int[][] plusPattern = straightMoves(board, column, row, this);
        int[][] moves = new int[2][xPattern.length + plusPattern.length];
        System.arraycopy(xPattern, 0, moves, 0, xPattern.length);
        System.arraycopy(plusPattern, 0, moves, xPattern.length, plusPattern.length);
        return moves;
    }
}
