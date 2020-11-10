package com.cs360.chess.piece;

import com.cs360.chess.Board;

public abstract class Piece {

    private final int points;
    private boolean hasMoved;
    private final int iconId;
    private final boolean isBlack;
    
    public Piece(int iconId, boolean isBlack, int points) {
        this.iconId = iconId;
        this.isBlack = isBlack;
        this.points = (isBlack ? -1 : 1) * points;
    }

    public void setHasMoved(boolean moved) {
        this.hasMoved = moved;
    }

    public int getPoints() {
        return points;
    }

    public boolean hasMoved() {
        return hasMoved;
    }

    public boolean isBlack() {
        return isBlack;
    }
    
    public int getIconId() {
        return iconId;
    }

    public abstract int[][] computePossible(Board board, int column, int row);

    protected static int[][] diagonalMoves(Board board, int column, int row, Piece piece) {
        int[][] moves = new int[2][13];//The most diagonal moves we can have is 13
        int index = 0;

        boolean br = true;
        boolean bl = true;
        boolean tr = true;
        boolean tl = true;
        for (int dx = 1; dx < 8; dx++) {
            //Bottom right of piece
            if (br && board.isInBounds(column + dx, row + dx)) {
                if (board.isPieceAt(column + dx, row + dx)) {
                    br = false; //We want to stop going to the bottom right
                    if (board.getPieceAt(column + dx, row + dx).isBlack() != piece.isBlack()) moves[index] = new int[]{column + dx, row + dx};
                } else moves[index] = new int[]{column + dx, row + dx};
                index++;
            }

            //Bottom left of piece
            if (bl && board.isInBounds(column - dx, row + dx)) {
                if (board.isPieceAt(column - dx, row + dx)) {
                    bl = false; //We want to stop going to the bottom right
                    if (board.getPieceAt(column - dx, row + dx).isBlack() != piece.isBlack()) moves[index] = new int[]{column - dx, row + dx};
                } else moves[index] = new int[]{column - dx, row + dx};
                index++;
            }

            //Top right of piece
            if (tr && board.isInBounds(column + dx, row - dx)) {
                if (board.isPieceAt(column + dx, row - dx)) {
                    tr = false; //We want to stop going to the bottom right
                    if (board.getPieceAt(column + dx, row - dx).isBlack() != piece.isBlack()) moves[index] = new int[]{column + dx, row - dx};
                } else moves[index] = new int[]{column + dx, row - dx};
                index++;
            }

            //Top left of piece
            if (tl && board.isInBounds(column - dx, row - dx)) {
                if (board.isPieceAt(column - dx, row - dx)) {
                    tl = false; //We want to stop going to the bottom right
                    if (board.getPieceAt(column - dx, row - dx).isBlack() != piece.isBlack()) moves[index] = new int[]{column - dx, row - dx};
                } else moves[index] = new int[]{column - dx, row - dx};
                index++;
            }
        }

        int[][] trimmedMoves = new int[index][2];
        System.arraycopy(moves, 0, trimmedMoves, 0, index);
        return trimmedMoves;
    }

    protected static int[][] straightMoves(Board board, int column, int row, Piece piece) {
        int[][] moves = new int[14][2];
        int index = 0;

        boolean up = true;
        boolean down = true;
        boolean left = true;
        boolean right = true;

        for (int dx = 1; dx < 8; dx++) {

            //Search below
            if (down && board.isInBounds(column + dx, row)) {
                if (board.isPieceAt(column + dx, row)) {
                    down = false;
                    if (board.getPieceAt(column + dx, row).isBlack() != piece.isBlack()) moves[index] = new int[]{column + dx, row};
                } else moves[index] = new int[]{column + dx, row};
                index++;
            }

            //Search above
            if (up && board.isInBounds(column - dx, row)) {
                if (board.isPieceAt(column - dx, row)) {
                    up = false;
                    if (board.getPieceAt(column - dx, row).isBlack() != piece.isBlack()) moves[index] = new int[]{column - dx, row};
                } else moves[index] = new int[]{column - dx, row};
                index++;
            }

            //Search right
            if (right && board.isInBounds(column, row + dx)) {
                if (board.isPieceAt(column, row + dx)) {
                    right = false;
                    if (board.getPieceAt(column, row + dx).isBlack() != piece.isBlack()) moves[index] = new int[]{column, row + dx};
                } else moves[index] = new int[]{column, row + dx};
                index++;
            }

            //Search left
            if (left && board.isInBounds(column, row - dx)) {
                if (board.isPieceAt(column, row - dx)) {
                    left = false;
                    if (board.getPieceAt(column, row - dx).isBlack() != piece.isBlack()) moves[index] = new int[]{column, row - dx};
                } else moves[index] = new int[]{column, row - dx};
                index++;
            }
        }
        int[][] trimmedMoves = new int[index][2];
        System.arraycopy(moves, 0, trimmedMoves, 0, index);
        return trimmedMoves;

    }

}
