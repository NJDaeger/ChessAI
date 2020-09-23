package com.cs360.chess.piece;

import com.cs360.chess.Board;

public final class Pawn extends Piece {

    public Pawn(boolean isBlack) {
        super(0, isBlack);
    }

    @Override
    public boolean[][] computePossible(Board board) {
        return new boolean[0][];
    }
}
