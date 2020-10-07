package com.cs360.chess;

import com.cs360.chess.piece.*;

/**
 * The grid of pieces which can be manipulated. This can represent a primary board, or a future board depending on its context.
 */
public class Board {

    private Piece[][] board;
    
    /**
     * Creates a new board instance with a fresh set of pieces
     */
    public Board() {
        board = new Piece[8][8];
        //[COLUMN][ROW]

        //The black pieces (on top)
        board[0][0] = new Rook(true);
        board[1][0] = new Knight(true);
        board[2][0] = new Bishop(true);
        board[3][0] = new Queen(true);
        board[4][0] = new King(true);
        board[5][0] = new Bishop(true);
        board[6][0] = new Knight(true);
        board[7][0] = new Rook(true);
        board[0][1] = new Pawn(true);
        board[1][1] = new Pawn(true);
        board[2][1] = new Pawn(true);
        board[3][1] = new Pawn(true);
        board[4][1] = new Pawn(true);
        board[5][1] = new Pawn(true);
        board[6][1] = new Pawn(true);
        board[7][1] = new Pawn(true);

        //The white pieces (on bottom)
        board[0][7] = new Rook(false);
        board[1][7] = new Knight(false);
        board[2][7] = new Bishop(false);
        board[3][7] = new Queen(false);
        board[4][7] = new King(false);
        board[5][7] = new Bishop(false);
        board[6][7] = new Knight(false);
        board[7][7] = new Rook(false);
        board[0][6] = new Pawn(false);
        board[1][6] = new Pawn(false);
        board[2][6] = new Pawn(false);
        board[3][6] = new Pawn(false);
        board[4][6] = new Pawn(false);
        board[5][6] = new Pawn(false);
        board[6][6] = new Pawn(false);
        board[7][6] = new Pawn(false);
    }
    
    /**
     * Creates a new board based on a previous board. (DEEP COPY)
     * @param parent
     */
    public Board(Board parent) {
        this.board = new Piece[8][8];
        //Just using 8 since the board wont change size
        for (int column = 0; column < 8; column++) {
            for (int row = 0; row < 8; row++) {
                board[column][row] = parent.board[column][row];
            }
        }
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
        if (x > 7 || y > 7 || newX > 7 || newY > 7) throw new RuntimeException("Piece out of bounds");
        Piece oldPiece = board[newX][newY];
        board[newX][newY] = board[x][y];
        board[x][y] = null;
        return oldPiece;
    }

    /**
     *
     * @return The piece array board
     */
    public Piece[][] getBoard() {
        return board;
    }
}
