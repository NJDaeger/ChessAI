package com.cs360.chess;

import com.cs360.chess.piece.*;

/**
 * The grid of pieces which can be manipulated. This can represent a primary board, or a future board depending on its context.
 */
public class Board {
    private int boardValue =0; //the difference of white - black piece values, used for minimax.
    private Piece[] pieces;
    boolean whiteToMove = true; //flag that controls who's turn it is
    /**
     * Creates a new board instance with a fresh set of pieces
     */
    public Board() {
        pieces = new Piece[32];
        //[COLUMN][ROW]


        for (int i = 0; i < 8; i++) {
            pieces[i] = new Pawn(false, i, 6);
            pieces[i+8] = new Pawn(true, i, 1);
        }

        //The black pieces (on top)
        pieces[16] = new Rook(true, 0, 0);
        pieces[17] = new Knight(true, 1, 0);
        pieces[18] = new Bishop(true, 2, 0);
        pieces[19] = new Queen(true, 3, 0);
        pieces[20] = new King(true, 4, 0);
        pieces[21] = new Bishop(true, 5, 0);
        pieces[22] = new Knight(true, 6, 0);
        pieces[23] = new Rook(true, 7, 0);

        //The white pieces (on bottom)
        pieces[24] = new Rook(false, 0, 7);
        pieces[25] = new Knight(false, 1, 7);
        pieces[26] = new Bishop(false, 2, 7);
        pieces[27] = new Queen(false, 3, 7);
        pieces[28] = new King(false, 4, 7);
        pieces[29] = new Bishop(false, 5, 7);
        pieces[30] = new Knight(false, 6, 7);
        pieces[31] = new Rook(false, 7, 7);

        for (Piece piece : pieces) {
            System.out.println(piece);
        }
    }
    
    /**
     * Creates a new board based on a previous board. (DEEP COPY)
     * @param parent The board to base the current board off of.
     */
    public Board(Board parent) {
        this.pieces = new Piece[32];
        this.whiteToMove = parent.whiteToMove;

        int i = 0; //Keeping an index of the next available position in array
        for (Piece piece : parent.pieces) {
            if (piece == null) continue;
            this.pieces[i] = piece.clone();
            i++;
        }
    }

    /**
     *
     * @return Returns the value of who is next to move
     */
    public boolean isWhiteToMove() {
        return whiteToMove;
    }

    /**
     * Moves a piece from column,row to newColumn, newRow. Top left is 0, 0, bottom right is 7, 7
     * @param column The X position of the piece
     * @param row The Y position of the piece
     * @param newColumn The newColumn position of the piece
     * @param newRow The newRow position of the piece
     */
    public void movePiece(int column, int row, int newColumn, int newRow) {
        if (column > 7 || row > 7 || newColumn > 7 || newRow > 7) throw new RuntimeException("Piece out of bounds");

        for (Piece piece : pieces) {
            if (piece != null && piece.getColumn() == column && piece.getRow() == row) {
                clearPieceAt(newColumn, newRow);
                piece.setColumn(newColumn);
                piece.setRow(newRow);
                piece.setHasMoved(true);
                break;
            }
        }
        changeTurn();
        calcBoardScore();
    }
    public void aiMove(Board board){//for AI

    }

    /**
     * Get a piece at a specific location on the board.
     * @param column The column of the piece you want
     * @param row The row of the piece you want
     * @return The piece at the given spot, or null if there is no piece in the spot.
     */
    public Piece getPieceAt(int column, int row) {
        for (Piece piece : pieces) {
            if (piece != null && piece.getColumn() == column && piece.getRow() == row) return piece;
        }
        return null;
    }

    /**
     * Removes a piece from this board at a given location
     * @param column Column of the piece you want to remove
     * @param row Row of the piece you want to remove
     */
    public void clearPieceAt(int column, int row) {
        for (int i = 0; i < pieces.length; i++) {
            if (pieces[i] != null && pieces[i].getColumn() == column && pieces[i].getRow() == row) {
                pieces[i] = null;
                break;
            }
        }
    }

    /**
     * Check if a piece is at a given location on the board
     * @param column The column of the piece you want to check
     * @param row The row of the piece you want to check
     * @return True if a piece was at the given location, false otherwise
     */
    public boolean isPieceAt(int column, int row) {
        return getPieceAt(column, row) != null;
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
    public Piece[] getPieces() {
        return pieces;
    }

    /**
     * Get all the current black pieces on the board
     * @return An array of all the current black pieces on the board.
     */
    public Piece[] getBlackPieces() {
        Piece[] pieces = new Piece[16];
        int i = 0;
        for (Piece piece : this.pieces) {
            if (piece != null && piece.isBlack()) pieces[i++] = piece;
        }
        Piece[] resized = new Piece[i];
        System.arraycopy(pieces, 0, resized, 0, i);
        return resized;
    }

    /**
     * Get all the current white pieces on the board
     * @return An array of all the current white pieces on the board. Null indices indicates there are no more pieces found afterwards.
     */
    public Piece[] getWhitePieces() {
        Piece[] pieces = new Piece[16];
        int i = 0;
        for (Piece piece : this.pieces) {
            if (piece != null && !piece.isBlack()) pieces[i++] = piece;
        }
        Piece[] resized = new Piece[i];
        System.arraycopy(pieces, 0, resized, 0, i);
        return resized;
    }

    public int calcBoardScore(){
        Piece[] whitePieces = getWhitePieces();
        Piece[] blackPieces = getBlackPieces();
        int whiteScore =0;
        int blackScore =0;
        for (Piece whitePiece : whitePieces) {
            //System.out.print(whitePiece);
            whiteScore += whitePiece.getPoints();
        }
        for (Piece blackPiece : blackPieces) {
            blackScore += blackPiece.getPoints();
        }

        int score = whiteScore-blackScore;
        return boardValue;
    }
    void changeTurn(){whiteToMove=!whiteToMove;}

}
