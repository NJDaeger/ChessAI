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
     * @param parent The board to base the current board off of.
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
     * Moves a piece from column,row to newColumn, newRow. Top left is 0, 0, bottom right is 7, 7
     * @param column The X position of the piece
     * @param row The Y position of the piece
     * @param newColumn The newColumn position of the piece
     * @param newRow The newRow position of the piece
     * @return The piece which was kicked out of the new spot, or null if there was no piece occupying newColumn, newRow
     */
    public Piece movePiece(int column, int row, int newColumn, int newRow) {
        if (column > 7 || row > 7 || newColumn > 7 || newRow > 7) throw new RuntimeException("Piece out of bounds");
        Piece oldPiece = board[newColumn][newRow];
        board[newColumn][newRow] = board[column][row];
        board[column][row] = null;
        return oldPiece;
    }

    /**
     * Get a piece at a specific location on the board.
     * @param column The column of the piece you want
     * @param row The row of the piece you want
     * @return The piece at the given spot, or null if there is no piece in the spot.
     */
    public Piece getPieceAt(int column, int row) {
        return board[column][row];
    }

    /**
     * Check if a piece is at a given location on the board
     * @param column The column of the piece you want to check
     * @param row The row of the piece you want to check
     * @return True if a piece was at the given location, false otherwise
     */
    public boolean isPieceAt(int column, int row) {
        return board[column][row] != null;
    }

    /**
     * Check if the given column and row is within the bounds of the board
     * @return True if the location is within the board, false otherwise.
     */
    public boolean isInBounds(int column, int row) {
        return column <= 7 && row <= 7 && column >= 0 && row >= 0;
    }

    /**
     *
     * @return The piece array board
     */
    public Piece[][] getBoard() {
        return board;
    }

    /**
     * Get all the current black pieces on the board
     * @return An array of all the current black pieces on the board. Null indices indicates there are no more pieces found afterwards.
     */
    public Piece[] getBlackPieces() {
        Piece[] pieces = new Piece[16];
        int i = 0;
        for (int column = 0; column < 8; column++) {
            for (int row = 0; row < 8; row++) {
                if (i >= 16) break;
                if (board[column][row] != null && board[column][row].isBlack()) pieces[i++] = board[column][row];
            }
        }
        return pieces;
    }

    /**
     * Get all the current white pieces on the board
     * @return An array of all the current white pieces on the board. Null indices indicates there are no more pieces found afterwards.
     */
    public Piece[] getWhitePieces() {
        Piece[] pieces = new Piece[16];
        int i = 0;
        for (int column = 0; column < 8; column++) {
            for (int row = 0; row < 8; row++) {
                if (i >= 16) break;
                if (board[column][row] != null && !board[column][row].isBlack()) pieces[i++] = board[column][row];
            }
        }
        return pieces;
    }

}
