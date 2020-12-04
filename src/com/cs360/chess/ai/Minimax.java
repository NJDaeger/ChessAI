package com.cs360.chess.ai;

import com.cs360.chess.Board;
import com.cs360.chess.piece.Piece;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class Minimax {

    private final int threads = 8;
    private boolean isWhite = false;
    private Node root;
    private int depth;

    private final ExecutorService executor = Executors.newFixedThreadPool(threads);

    /**
     * Generate a minimax tree
     * @param board The board after the opponents last move
     * @param depth How deep the minimax tree looks ahead
     */
    public Minimax(Board board, int depth) {
        this.depth = depth;
        recomputeTree(board);
    }

    public void changeDepth(int x){
        this.depth = x;
    }

    public void shutdownThreads() {
        killTree();
        executor.shutdownNow();
    }

    public void killTree() {
        root.children.clear();
        root = null;
    }

    public void recomputeTree(Board board) {
        root = new Node(null, board);
        root.calculateChildren(1); //Only compute the first level of children at first so we can simply get a list of all the root's children.
        int remainder = root.children.size() % threads;
        int offset = 0;
        List<Future<List<Node>>> futures = new ArrayList<>();
        for (int i = 0; i < threads; i++) {
            List<Node> children = root.children.stream().skip(threads*i + (remainder-- > 0 ? ++offset : 0)).limit((root.children.size()-root.children.size() % threads)/threads + (remainder > 0 ? 1 : 0)).collect(Collectors.toList());
            futures.add(executor.submit(new ComputeThread(children)));
        }
        root.children.clear();
        futures.forEach(f -> {
            try {
                root.children.addAll(f.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
        futures.clear();
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

    private class ComputeThread implements Callable<List<Node>> {

        private final List<Node> nodes;

        public ComputeThread(List<Node> nodes) {
            this.nodes = nodes;
        }

        @Override
        public List<Node> call() {
            for (Node node : nodes) {
                node.calculateChildren(depth - 1);
            }
            System.out.println("finished");
            return nodes;
        }
    }

    private class Node {

        private int score;
        private Node parent;
        private Board nodeBoard;
        private ArrayList<Node> children;

        public Node(Node parent,Board board) {
            nodeBoard = board;
            this.parent=parent;
        }

        /**
         * generates and populates the children nodes
         */
        public void calculateChildren(int depth){
            children = new ArrayList<>();
            for (Piece piece : nodeBoard.getPieces()) {
                if (piece != null && piece.isBlack() != nodeBoard.isWhiteToMove()) {
                    int[][] possible = piece.computePossible(nodeBoard);
                    for (int[] coord : possible) {
                        Board childBoard = new Board(nodeBoard);//clone
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
