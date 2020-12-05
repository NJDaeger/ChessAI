package com.cs360.chess;

import com.cs360.chess.ai.*;
import java.io.Serializable;
import java.util.Stack;

/**
 * The Game class is the primary controller behind the whole game. It manages the Minimax tree and the primary board.
 * This will also serve as the game "instance" object, which can be saved to a file at any given time during a game.
 *
 * //todo any additional attributes should be described up here.
 */
public class Game implements Serializable {

    private int depth = 0;
    private Board currentBoard;
    private Minimax currentTree;
    private Stack<Board> undoStack;
    private Stack<Board> redoStack;

    private int[] selected;
    //todo handle minimax in this class.

    public Game() {
        //The last move we just did
        this.undoStack = new Stack<>();
        //The last move we just undid
        this.redoStack = new Stack<>();
        this.currentBoard = new Board();


        this.currentTree = new Minimax(currentBoard, depth);
    }

    public Board getCurrentBoard() {
        return currentBoard;
    }

    public int[] getSelectedLocation() {
        return selected;
    }

    public void setSelectedLocation(int[] piece) {
        this.selected = piece;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }//refactor

    /**
     * Undoes the current board to the last board state
     * @return The board before being undone.
     */
    public Board undo() {
        Board lastMove = undoStack.pop();
        redoStack.push(currentBoard);
        this.currentBoard = lastMove;
        return redoStack.peek();
    }

    /**
     * Redoes the current board to the previous state
     * @return The board before being redone
     */
    public Board redo() {
        Board nextMove = redoStack.pop();
        undoStack.push(currentBoard);
        this.currentBoard = nextMove;
        return undoStack.peek();
    }

    public void close() {
        currentTree.shutdownThreads();
    }

    public void turn(int column, int row, int newColumn, int newRow){
        if(!currentBoard.isWhiteToMove()) {

            currentBoard.movePiece(column,row,newColumn,newRow);//replace with ai move method
        }
        else{
            currentBoard.movePiece(column,row,newColumn,newRow);
            currentTree.killTree();
            currentTree.recomputeTree(currentBoard);
        }


    }

}
