package com.cs360.chess.piece;

import com.cs360.chess.Board;

public final class Knight extends Piece {
    
    public Knight(boolean isBlack) {
        super(2, isBlack, 30);
    }
    
    // L L L L L L L L L
    //can skip
    @Override
    public int[][] computePossible(Board board, int column, int row) {
        int[][] moves = new int[8][2];
        int index = 0;
        /*

        This is the diagram of the pieces in this. It is all hard coded and each point is marked by the respective letter.

          H   A
        G       B
            X
        F       C
          E   D

         */

        //Spot A
        if (board.isInBounds(column + 1, row - 2)) {
            //If the spot is empty or if the piece at the given spot is the opposite color, we can go there.
            if (!board.isPieceAt(column + 1, row - 2) || board.getPieceAt(column + 1, row - 2).isBlack() != isBlack()) {
                moves[index] = new int[]{column + 1, row - 2};
                index++;
            }
        }

        //Spot B
        if (board.isInBounds(column + 2, row - 1)) {
            if (!board.isPieceAt(column + 2, row - 1) || board.getPieceAt(column + 2, row - 1).isBlack() != isBlack()) {
                moves[index] = new int[]{column + 2, row - 1};
                index++;
            }
        }

        //Spot C
        if (board.isInBounds(column + 2, row + 1)) {
            if (!board.isPieceAt(column + 2, row + 1) || board.getPieceAt(column + 2, row + 1).isBlack() != isBlack()) {
                moves[index] = new int[]{column + 2, row + 1};
                index++;
            }
        }

        //Spot D
        if (board.isInBounds(column + 1, row + 2)) {
            if (!board.isPieceAt(column + 1, row + 2) || board.getPieceAt(column + 1, row + 2).isBlack() != isBlack()) {
                moves[index] = new int[]{column + 1, row + 2};
                index++;
            }
        }

        //Spot E
        if (board.isInBounds(column - 1, row + 2)) {
            if (!board.isPieceAt(column - 1, row + 2) || board.getPieceAt(column - 1, row + 2).isBlack() != isBlack()) {
                moves[index] = new int[]{column - 1, row + 2};
                index++;
            }
        }

        //Spot F
        if (board.isInBounds(column - 2, row + 1)) {
            if (!board.isPieceAt(column - 2, row + 1) || board.getPieceAt(column - 2, row + 1).isBlack() != isBlack()) {
                moves[index] = new int[]{column - 2, row + 1};
                index++;
            }
        }

        //Spot G
        if (board.isInBounds(column - 2, row - 1)) {
            if (!board.isPieceAt(column - 2, row - 1) || board.getPieceAt(column - 2, row - 1).isBlack() != isBlack()) {
                moves[index] = new int[]{column - 2, row - 1};
                index++;
            }
        }

        //Spot H
        if (board.isInBounds(column - 1, row - 2)) {
            if (!board.isPieceAt(column - 1, row - 2) || board.getPieceAt(column - 1, row - 2).isBlack() != isBlack()) {
                moves[index] = new int[]{column - 1, row - 2};
            }
        }

        int[][] trimmedMoves = new int[index][2];
        System.arraycopy(moves, 0, trimmedMoves, 0, index);
        return trimmedMoves;
    }
}
