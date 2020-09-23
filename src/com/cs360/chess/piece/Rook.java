package com.cs360.chess.piece;

import com.cs360.chess.Board;

public final class Rook extends Piece {

    public Rook(boolean isBlack) {
        super(1, isBlack);
    }

    @Override
    public boolean[][] computePossible(Board board) {
        return new boolean[0][];
    }
}
