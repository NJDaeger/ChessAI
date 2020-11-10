package com.cs360.chess.piece;

import com.cs360.chess.Board;

public final class King extends Piece {
    
    public King(boolean isBlack) {
        super(4, isBlack, 900);
    }
    
    //all directions, max of 1 space at a time
    @Override
    public int[][] computePossible(Board board, int column, int row) {
        int[][] moves = new int[8][2];
        int index = 0;
        /*

        This is the diagram of the pieces in this. It is all hard coded and each point is marked by the respective letter.

          G H A
          F X B
          E D C

         */

        //Spot A
        if (board.isInBounds(column + 1, row - 1)) {
            //If the spot is empty or if the piece at the given spot is the opposite color, we can go there.
            if (!board.isPieceAt(column + 1, row - 1) || board.getPieceAt(column + 1, row - 1).isBlack() != isBlack()) {
                moves[index] = new int[]{column + 1, row - 1};
                index++;
            }
        }

        //Spot B
        if (board.isInBounds(column + 1, row)) {
            if (!board.isPieceAt(column + 1, row) || board.getPieceAt(column + 1, row).isBlack() != isBlack()) {
                moves[index] = new int[]{column + 1, row};
                index++;
            }
        }

        //Spot C
        if (board.isInBounds(column + 1, row + 1)) {
            if (!board.isPieceAt(column + 1, row + 1) || board.getPieceAt(column + 1, row + 1).isBlack() != isBlack()) {
                moves[index] = new int[]{column + 1, row + 1};
                index++;
            }
        }

        //Spot D
        if (board.isInBounds(column, row + 1)) {
            if (!board.isPieceAt(column, row + 1) || board.getPieceAt(column, row + 1).isBlack() != isBlack()) {
                moves[index] = new int[]{column, row + 1};
                index++;
            }
        }

        //Spot E
        if (board.isInBounds(column - 1, row + 1)) {
            if (!board.isPieceAt(column - 1, row + 1) || board.getPieceAt(column - 1, row + 1).isBlack() != isBlack()) {
                moves[index] = new int[]{column - 1, row + 1};
                index++;
            }
        }

        //Spot F
        if (board.isInBounds(column - 1, row)) {
            if (!board.isPieceAt(column - 1, row) || board.getPieceAt(column - 1, row).isBlack() != isBlack()) {
                moves[index] = new int[]{column - 1, row};
                index++;
            }
        }

        //Spot G
        if (board.isInBounds(column - 1, row - 1)) {
            if (!board.isPieceAt(column - 1, row - 1) || board.getPieceAt(column - 1, row - 1).isBlack() != isBlack()) {
                moves[index] = new int[]{column - 1, row - 1};
                index++;
            }
        }

        //Spot H
        if (board.isInBounds(column, row - 1)) {
            if (!board.isPieceAt(column, row - 1) || board.getPieceAt(column, row - 1).isBlack() != isBlack()) {
                moves[index] = new int[]{column, row - 1};
            }
        }

        int[][] trimmedMoves = new int[index][2];
        System.arraycopy(moves, 0, trimmedMoves, 0, index);
        return trimmedMoves;
    }
}
