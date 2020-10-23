package com.cs360.chess.piece;

import com.cs360.chess.Board;

public final class Pawn extends Piece {

    public Pawn(boolean isBlack) {
        super(0, isBlack);
    }
    
    /**
     * @param board The game board to use
     * @param column Column of said piece
     * @param row Row of said piece
     * @return A 2D array of integer coordinates eg. {{0,0},{1,1},{2,2},{3,3}}. If the piece cannot move to a given space, -1 is returned for both new coordinates.
     */
    //2 spaces forward first shot if clear
    //1 space forward from then on if clear
    //can move diagonally 1 space (forward) only if there is a piece in the said diagonal spot
    @Override
    public int[][] computePossible(Board board, int column, int row) {
        int direction = isBlack() ? 1 : -1;
        int[][] moves = new int[4][2];
    
        //No attack (move forward 1 space)
        if (board.isInBounds(column, row + direction) && !board.isPieceAt(column, row + direction)) {
            moves[0] = new int[]{column, row + direction};
        } else moves[0] = new int[]{-1, -1};
        
        //attack left (diagonal)
        if (board.isInBounds(column - 1, row + direction) && board.isPieceAt(column - 1, row + direction)) {
            moves[1] = new int[]{column - 1, row + direction}; //attack left
        } else moves[1] = new int[]{-1, -1};
        
        //attack right (diagonal)
        if (board.isInBounds(column + 1, row + direction) && board.isPieceAt(column + 1, row + direction)) {
            moves[2] = new int[]{column + 1, row + direction}; //attack right
        } else moves[2] = new int[]{-1, -1};
        
        //No attack, move forward 2 spaces if the piece has not moved ever
        if (!hasMoved() && board.isInBounds(column, row + direction) && !board.isPieceAt(column, row + (2 * direction))) {
            moves[3] = new int[]{column, row + (2 * direction)};
        } else moves[3] = new int[]{-1, -1};
        
        return moves;
    }
}
