package com.cs360.chess.ai;

import com.cs360.chess.Board;
import com.cs360.chess.piece.Piece;

import java.util.Stack;

public class Minimax {
    private boolean isWhite = false;
    private Node root;
    private Board board;
    private int depth;

    /**
     * Generate a minimax tree
     * @param board The board after the opponents last move
     * @param depth How deep the minimax tree looks ahead
     */
    public Minimax(Board board, int depth) {
        this.board = board;
        this.depth = depth;
    }
    public void changeDepth(int x){
        depth = x;
    }
    private int evaluate() {
        return 0;
    }

    private class Node {
        Stack <Node> children = new Stack<Node>();
        private Board boardFuture;

        public Node(Node parent,Board board) {
        }

        /**
         * generates and populates the children nodes
         */
        public void calculateChildren(){
            Piece[] pieces;
            if(boardFuture.isWhiteToMove()){
                pieces=board.getWhitePieces();
            }
            else{
                pieces = board.getBlackPieces();
            }
            //loop through pieces and create a new board for each move, and make a node with that board
            for(int i=0;i<pieces.length;i++){
                //loop through the pieces possible moves
                //for()
            }

        }
        private int max(int depth) {
            return 0;
        }

        private int min(int depth) {
            return 0;
        }

    }

}
