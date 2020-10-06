package com.cs360.chess.piece;

import com.cs360.chess.Board;

public final class Pawn extends Piece {

    public Pawn(boolean isBlack) {
        super(0, isBlack);
    }

    //2 spaces forward first shot if clear
    //1 space forward from then on if clear
    //can move diagonally 1 space (forward) only if there is a piece in the said diagonal spot
    @Override
    public boolean[][] computePossible(Board board) {
        return new boolean[0][];
    }
}
