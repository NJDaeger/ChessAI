package com.cs360.chess.piece;

import com.cs360.chess.Board;

public final class Pawn extends Piece {

    public Pawn(boolean isBlack) {
        super(0, isBlack, 10);
    }
    
    /**
     * @param board The game board to use
     * @param column Column of said piece
     * @param row Row of said piece
     * @return A 2D array of integer coordinates eg. {{0,0},{1,1},{2,2},{3,3}}.
     */
    //2 spaces forward first shot if clear
    //1 space forward from then on if clear
    //can move diagonally 1 space (forward) only if there is a piece in the said diagonal spot
    @Override
    public int[][] computePossible(Board board, int column, int row) {
        int direction = isBlack() ? 1 : -1;
        int[][] moves = new int[4][2];
        int index = 0;
    
        //No attack (move forward 1 space)
        if (board.isInBounds(column, row + direction) && !board.isPieceAt(column, row + direction)) {
            moves[index] = new int[]{column, row + direction};
            index++;
        }
        
        //attack left (diagonal)
        if (board.isInBounds(column - 1, row + direction) && board.isPieceAt(column - 1, row + direction) && (board.getPieceAt(column - 1, row + direction).isBlack() != isBlack())) {
            moves[index] = new int[]{column - 1, row + direction}; //attack left
            index++;
        }
        
        //attack right (diagonal)
        if (board.isInBounds(column + 1, row + direction) && board.isPieceAt(column + 1, row + direction) && (board.getPieceAt(column + 1, row + direction).isBlack() != isBlack())) {
            moves[index] = new int[]{column + 1, row + direction}; //attack right
            index++;
        }
        
        //No attack, move forward 2 spaces if the piece has not moved ever
        if (!hasMoved() && board.isInBounds(column, row + direction) && !board.isPieceAt(column, row + (2 * direction))) {
            moves[index] = new int[]{column, row + (2 * direction)};
        }

        int[][] trimmedMoves = new int[index][2];
        System.arraycopy(moves, 0, trimmedMoves, 0, index);
        return trimmedMoves;
    }
}
