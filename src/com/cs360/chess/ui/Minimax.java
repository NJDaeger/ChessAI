package com.cs360.chess.ui;

import com.cs360.chess.Board;

public class Minimax {

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

    private int evaluate() {
        return 0;
    }

    private class Node {

        private Board boardFuture;

        public Node() {
        }

        private int max(int depth) {
            return 0;
        }

        private int min(int depth) {
            return 0;
        }

    }

}
