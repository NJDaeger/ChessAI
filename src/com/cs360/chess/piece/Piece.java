package com.cs360.chess.piece;

import com.cs360.chess.Board;

public abstract class Piece implements Cloneable {

    private final int points;
    private boolean hasMoved;
    private final boolean isBlack;

    /*
      Okay, so this is a very heavily packed integer in an effort to remove as many data fields as we can.
     */
    protected byte data;
//    protected int column;
//    protected int row;
    
    public Piece(boolean isBlack, int points, int column, int row) {
        this.isBlack = isBlack;
        this.points = points;
        this.data = (byte)(column << 4 | row);
    }

    @Override
    public abstract Piece clone();

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

    /**
     * @param board The game board to use
     * @return A 2D array of integer coordinates eg. {{0,0},{1,1},{2,2},{3,3}}.
     */
    public abstract int[][] computePossible(Board board);

    protected static int[][] diagonalMoves(Board board, Piece piece) {
        int[][] moves = new int[13][2];//The most diagonal moves we can have is 13
        int index = 0;
        int column = piece.getColumn();
        int row = piece.getRow();

        boolean br = true;
        boolean bl = true;
        boolean tr = true;
        boolean tl = true;
        for (int dx = 1; dx < 8; dx++) {
            //Bottom right of piece
            if (br && board.isInBounds(column + dx, row + dx)) {
                if (board.isPieceAt(column + dx, row + dx)) {
                    br = false; //We want to stop going to the bottom right
                    if (board.getPieceAt(column + dx, row + dx).isBlack() != piece.isBlack()) {
                        moves[index] = new int[]{column + dx, row + dx};
                        index++;
                    }
                } else {
                    moves[index] = new int[]{column + dx, row + dx};
                    index++;
                }
            }

            //Bottom left of piece
            if (bl && board.isInBounds(column - dx, row + dx)) {
                if (board.isPieceAt(column - dx, row + dx)) {
                    bl = false; //We want to stop going to the bottom right
                    if (board.getPieceAt(column - dx, row + dx).isBlack() != piece.isBlack()) {
                        moves[index] = new int[]{column - dx, row + dx};
                        index++;
                    }
                } else {
                    moves[index] = new int[]{column - dx, row + dx};
                    index++;
                }
            }

            //Top right of piece
            if (tr && board.isInBounds(column + dx, row - dx)) {
                if (board.isPieceAt(column + dx, row - dx)) {
                    tr = false; //We want to stop going to the bottom right
                    if (board.getPieceAt(column + dx, row - dx).isBlack() != piece.isBlack()) {
                        moves[index] = new int[]{column + dx, row - dx};
                        index++;
                    }
                } else {
                    moves[index] = new int[]{column + dx, row - dx};
                    index++;
                }
            }

            //Top left of piece
            if (tl && board.isInBounds(column - dx, row - dx)) {
                if (board.isPieceAt(column - dx, row - dx)) {
                    tl = false; //We want to stop going to the bottom right
                    if (board.getPieceAt(column - dx, row - dx).isBlack() != piece.isBlack()) {
                        moves[index] = new int[]{column - dx, row - dx};
                        index++;
                    }
                } else {
                    moves[index] = new int[]{column - dx, row - dx};
                    index++;
                }
            }
        }

        int[][] trimmedMoves = new int[index][2];
        System.arraycopy(moves, 0, trimmedMoves, 0, index);
        return trimmedMoves;
    }

    protected static int[][] straightMoves(Board board, Piece piece) {
        int[][] moves = new int[14][2];
        int index = 0;
        int column = piece.getColumn();
        int row = piece.getRow();

        boolean up = true;
        boolean down = true;
        boolean left = true;
        boolean right = true;

        for (int dx = 1; dx < 8; dx++) {

            //Search below
            if (down && board.isInBounds(column + dx, row)) {
                if (board.isPieceAt(column + dx, row)) {
                    down = false;
                    if (board.getPieceAt(column + dx, row).isBlack() != piece.isBlack()) {
                        moves[index] = new int[]{column + dx, row};
                        index++;
                    }
                } else {
                    moves[index] = new int[]{column + dx, row};
                    index++;
                }
            }

            //Search above
            if (up && board.isInBounds(column - dx, row)) {
                if (board.isPieceAt(column - dx, row)) {
                    up = false;
                    if (board.getPieceAt(column - dx, row).isBlack() != piece.isBlack()) {
                        moves[index] = new int[]{column - dx, row};
                        index++;
                    }
                } else {
                    moves[index] = new int[]{column - dx, row};
                    index++;
                }
            }

            //Search right
            if (right && board.isInBounds(column, row + dx)) {
                if (board.isPieceAt(column, row + dx)) {
                    right = false;
                    if (board.getPieceAt(column, row + dx).isBlack() != piece.isBlack()) {
                        moves[index] = new int[]{column, row + dx};
                        index++;
                    }
                } else {
                    moves[index] = new int[]{column, row + dx};
                    index++;
                }
            }

            //Search left
            if (left && board.isInBounds(column, row - dx)) {
                if (board.isPieceAt(column, row - dx)) {
                    left = false;
                    if (board.getPieceAt(column, row - dx).isBlack() != piece.isBlack()) {
                        moves[index] = new int[]{column, row - dx};
                        index++;
                    }
                } else {
                    moves[index] = new int[]{column, row - dx};
                    index++;
                }
            }
        }

        int[][] trimmedMoves = new int[index][2];
        System.arraycopy(moves, 0, trimmedMoves, 0, index);
        return trimmedMoves;

    }

    public int getColumn() {
        return (data >> 4) & 0xF;
    }

    public void setColumn(int column) {
        this.data = (byte)(column << 4 | getRow());
    }

    public int getRow() {
        return data & 0xF;
    }

    public void setRow(int row) {
        this.data = (byte)(getColumn() << 4 | row);
    }
}
