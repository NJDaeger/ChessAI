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
        int[][] moves = new int[2][16];

        for (int )
    }

    protected static int[][] straightMoves(Board board, int column, int row, Piece piece) {
        int[][] moves = new int[2][16];
        int index = 0;
        int direction = piece.isBlack() ? 1 : -1;

        //Check towards respective side
        for (int dy = direction; board.isInBounds(column + dy, row); dy += direction) {
            if (board.isPieceAt(column + dy, row)) {
                if (board.getPieceAt(column + dy, row).isBlack() != piece.isBlack()) moves[index] = new int[]{column + dy, row};
                break;
            } else {
                moves[index] = new int[]{column + dy, row};
                index++;
            }
        }

        //Check away from respective side
        for (int dy = -direction; board.isInBounds(column + dy, row); dy += -direction) {
            if (board.isPieceAt(column + dy, row)) {
                if (board.getPieceAt(column + dy, row).isBlack() != piece.isBlack()) moves[index] = new int[]{column + dy, row};
                break;
            } else {
                moves[index] = new int[]{column + dy, row};
                index++;
            }
        }
    }

}
