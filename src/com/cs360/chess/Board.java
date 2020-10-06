package com.cs360.chess;

import com.cs360.chess.piece.Piece;

/**
 * The grid of pieces which can be manipulated. This can represent a primary board, or a future board depending on its context.
 */
public class Board {

    private Piece[][] board;
    
    /**
     * Creates a new board instance with a fresh set of pieces
     */
    public Board() {
        //todo generate a prepopulated new chess game board
    }
    
    /**
     * Creates a new board based on a previous board.
     * @param parent
     */
    public Board(Board parent) {
    
    }
    
    /**
     * Moves a piece from x,y to newX, newY. Top left is 0, 0, bottom right is 9, 9
     * @param x The X position of the piece
     * @param y The Y position of the piece
     * @param newX The newX position of the piece
     * @param newY The newY position of the piece
     * @return The piece which was kicked out of the new spot, or null if there was no piece occupying newX, newY
     */
    public Piece movePiece(int x, int y, int newX, int newY) {
        if (x > 9 || y > 9 || newX > 9 || newY > 9) throw new RuntimeException("Piece out of bounds");
        Piece oldPiece = board[newX][newY];
        board[newX][newY] = board[x][y];
        board[x][y] = null;
        return oldPiece;
    }
    
}
