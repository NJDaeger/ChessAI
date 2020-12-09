package com.cs360.chess;

import com.cs360.chess.ai.Minimax;
import com.cs360.chess.piece.King;
import com.cs360.chess.piece.Piece;

import java.io.Serializable;
import java.util.Stack;

/**
 * The Game class is the primary controller behind the whole game. It manages the Minimax tree and the primary board.
 * This will also serve as the game "instance" object, which can be saved to a file at any given time during a game.
 *
 * //todo any additional attributes should be described up here.
 */
public class Game implements Serializable {

    private int depth = 5;
    private Board currentBoard;
    private Stack<Board> undoStack;
    private Stack<Board> redoStack;
    private int[] selected;

    public Game() {
        //The last move we just did
        this.undoStack = new Stack<>();
        //The last move we just undid
        this.redoStack = new Stack<>();
        this.currentBoard = new Board();
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

    public int[][] getValidSpotsFrom(int column, int row) {
        Piece piece = currentBoard.getPieceAt(column, row);
        int[][] moves = piece instanceof King ? piece.findNonIntersecting(currentBoard) : piece.computePossible(currentBoard);
        int[][] valid = new int[moves.length][2];
        int index = 0;
        for(int[] coord : moves) {
            Board clone = new Board(currentBoard);
            clone.movePiece(column, row, coord[0], coord[1]);
            if (currentBoard.isWhiteToMove() && !clone.isWhiteInCheck()) {
                valid[index] = coord;
                index++;
            }
        }
        int[][] trimmedMoves = new int[index][2];
        System.arraycopy(valid, 0, trimmedMoves, 0, index);
        return trimmedMoves;
    }

    public void aiTurn(){
        /*try {
            this.currentBoard = executor.submit(() -> {
                Minimax ai = new Minimax(currentBoard,depth);
                Board newBoard = ai.bestMove();
                if (newBoard == null) {
                    System.out.println("YOU WIN");
                    return null;
                }
                else return newBoard;
            }).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }*/
        Minimax ai = new Minimax(currentBoard,depth);
        Board newBoard = ai.bestMove();
        if (newBoard == null) System.out.println("YOU WIN");
        else currentBoard = newBoard;
    }

}
