package com.cs360.chess.piece;

import com.cs360.chess.Board;

public abstract class Piece {

    private final int iconId;
    private final boolean isBlack;
    
    public Piece(int iconId, boolean isBlack) {
        this.iconId = iconId;
        this.isBlack = isBlack;
    }
    
    public boolean isBlack() {
        return isBlack;
    }
    
    public int getIconId() {
        return iconId;
    }

    public abstract boolean[][] computePossible(Board board);

}
