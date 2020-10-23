package com.cs360.chess.piece;

import com.cs360.chess.Board;

public abstract class Piece {

    private boolean hasMoved;
    private final int iconId;
    private final boolean isBlack;
    
    public Piece(int iconId, boolean isBlack) {
        this.iconId = iconId;
        this.isBlack = isBlack;
    }

    public void setHasMoved(boolean moved) {
        this.hasMoved = moved;
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

}
