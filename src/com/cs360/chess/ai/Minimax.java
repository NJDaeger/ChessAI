package com.cs360.chess.ai;

import com.cs360.chess.Board;
import com.cs360.chess.piece.Piece;

import java.util.ArrayList;

public class Minimax {
    private boolean isWhite = false;
    private Node root;
    private int depth;

    /**
     * Generate a minimax tree
     * @param board The board after the opponents last move
     * @param depth How deep the minimax tree looks ahead
     */
    public Minimax(Board board, int depth) {
        this.root = new Node(null,board);
        root.calculateChildren(depth);
        this.depth = depth;
    }
    public void changeDepth(int x){
        this.depth = x;
    }

    public void killTree() {
        root.children.clear();
        root = null;
    }

    public void recomputeTree(Board board) {
        root = new Node(null, board);
        root.calculateChildren(depth);
    }

    public Node getRoot() {
        return root;
    }

    /**returns the board after the best move for the ai
     *
     */
    void getBestMove(){

    }

    void pruneTree(){

    }

    void calulateNodeScores(){//recursive

    }

    private class Node {
        Node parent;
        int score =0;
        ArrayList<Node> children = new ArrayList<>();
        private Board boardFuture;

        public Node(Node parent,Board board) {
            boardFuture = board;
            this.parent=parent;
        }

        /**
         * generates and populates the children nodes
         */
        public void calculateChildren(int depth){

            for (Piece piece : boardFuture.getPieces()) {
                if (piece != null && piece.isBlack() != boardFuture.isWhiteToMove()) {
                    int[][] possible = piece.computePossible(boardFuture);
                    for (int[] coord : possible) {
                        Board childBoard = new Board(boardFuture);//clone
                        childBoard.movePiece(piece.getColumn(),piece.getRow(),coord[0],coord[1]);
                        Node childNode = new Node(this,childBoard);
                        if(depth>1){
                            childNode.calculateChildren(depth-1);
                        }
                        children.add(childNode);
                    }
                }
            }
        }
    }
}
