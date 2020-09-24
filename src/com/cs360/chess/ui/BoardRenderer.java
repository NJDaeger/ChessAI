package com.cs360.chess.ui;

import com.cs360.chess.Board;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class BoardRenderer extends Scene {
    
    private final Board board;
    
    public BoardRenderer(Board board, Parent parent) {
        super(parent);
        this.board = board;
    }
    
    
    
}
