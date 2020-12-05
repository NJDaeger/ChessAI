package com.cs360.chess.piece;

import com.cs360.chess.Board;

public final class King extends Piece {

    private static final int points = 2000;

    public King(boolean isBlack, int column, int row) {
        super(isBlack, column, row);
    }

    public King(boolean black, boolean hasMoved, int column, int row) {
        super(black, hasMoved, column, row);
    }

    @Override
    public King clone() {
        return new King(isBlack(), hasMoved(), getColumn(), getRow());
    }

    @Override
    public int getPoints() {
        return points;
    }

    //all directions, max of 1 space at a time
    @Override
    public int[][] computePossible(Board board) {
        if (board.isWhiteToMove() && isBlack()) return new int[0][0];
        int[][] moves = new int[8][2];
        int index = 0;
        /*

        This is the diagram of the pieces in this. It is all hard coded and each point is marked by the respective letter.

          G H A
          F X B
          E D C

         */

        //Spot A
        if (board.isInBounds(getColumn() + 1, getRow() - 1)) {
            //If the spot is empty or if the piece at the given spot is the opposite color, we can go there.
            if (!board.isPieceAt(getColumn() + 1, getRow() - 1) || board.getPieceAt(getColumn() + 1, getRow() - 1).isBlack() != isBlack()) {
                moves[index] = new int[]{getColumn() + 1, getRow() - 1};
                index++;
            }
        }

        //Spot B
        if (board.isInBounds(getColumn() + 1, getRow())) {
            if (!board.isPieceAt(getColumn() + 1, getRow()) || board.getPieceAt(getColumn() + 1, getRow()).isBlack() != isBlack()) {
                moves[index] = new int[]{getColumn() + 1, getRow()};
                index++;
            }
        }

        //Spot C
        if (board.isInBounds(getColumn() + 1, getRow() + 1)) {
            if (!board.isPieceAt(getColumn() + 1, getRow() + 1) || board.getPieceAt(getColumn() + 1, getRow() + 1).isBlack() != isBlack()) {
                moves[index] = new int[]{getColumn() + 1, getRow() + 1};
                index++;
            }
        }

        //Spot D
        if (board.isInBounds(getColumn(), getRow() + 1)) {
            if (!board.isPieceAt(getColumn(), getRow() + 1) || board.getPieceAt(getColumn(), getRow() + 1).isBlack() != isBlack()) {
                moves[index] = new int[]{getColumn(), getRow() + 1};
                index++;
            }
        }

        //Spot E
        if (board.isInBounds(getColumn() - 1, getRow() + 1)) {
            if (!board.isPieceAt(getColumn() - 1, getRow() + 1) || board.getPieceAt(getColumn() - 1, getRow() + 1).isBlack() != isBlack()) {
                moves[index] = new int[]{getColumn() - 1, getRow() + 1};
                index++;
            }
        }

        //Spot F
        if (board.isInBounds(getColumn() - 1, getRow())) {
            if (!board.isPieceAt(getColumn() - 1, getRow()) || board.getPieceAt(getColumn() - 1, getRow()).isBlack() != isBlack()) {
                moves[index] = new int[]{getColumn() - 1, getRow()};
                index++;
            }
        }

        //Spot G
        if (board.isInBounds(getColumn() - 1, getRow() - 1)) {
            if (!board.isPieceAt(getColumn() - 1, getRow() - 1) || board.getPieceAt(getColumn() - 1, getRow() - 1).isBlack() != isBlack()) {
                moves[index] = new int[]{getColumn() - 1, getRow() - 1};
                index++;
            }
        }

        //Spot H
        if (board.isInBounds(getColumn(), getRow() - 1)) {
            if (!board.isPieceAt(getColumn(), getRow() - 1) || board.getPieceAt(getColumn(), getRow() - 1).isBlack() != isBlack()) {
                moves[index] = new int[]{getColumn(), getRow() - 1};
                index++;
            }
        }

        int[][] trimmedMoves = new int[index][2];
        System.arraycopy(moves, 0, trimmedMoves, 0, index);
        return trimmedMoves;
    }

    @Override
    public String toString() {
        return "King[black=" + isBlack() + ",points=" + getPoints() + ",moved=" + hasMoved() + ",col=" + getColumn() + ",row=" + getRow() + ",BINARY=" + Integer.toBinaryString(data) + "]";
    }

}
