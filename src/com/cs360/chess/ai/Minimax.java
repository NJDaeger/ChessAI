package com.cs360.chess.ai;

import com.cs360.chess.Board;
import com.cs360.chess.piece.Piece;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.lang.*;
import java.util.stream.Collectors;

public class Minimax {

    private final int threads = 16;
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
        this.root = new Node(board);
    }

    public int minmax(Node node, int depth, int alpha, int beta){
        Board board = node.nodeBoard;
        if(depth == 0){
            return board.calcBoardScore();
        }
        if (depth != this.depth) node.calculateChildren(); //We only want to calculate children if we are NOT the root node. (the roots children is calculated in bestMove())

        if(board.isWhiteToMove()) {
            int maxScore = -2000;
            for(Node child: node.children){
                child.score = minmax(child, depth-1, alpha, beta);
                maxScore = Math.max(maxScore, child.score);
                alpha = Math.max(alpha, child.score);
                if(beta <= alpha)break;//pruning
            }
            //if AI white, move white here
            return maxScore;
        } else{
            int minScore = 2000;
            for(Node child: node.children){
                child.score = minmax(child, depth-1, alpha, beta);
                minScore = Math.min(minScore, child.score);
                beta = Math.min(alpha, child.score);
                if(beta <= alpha)break;//pruning
            }
            return minScore;
        }
    }

    /**
     * Only Call after MiniMax Has been Called or error
     * @return Board that represents the AI's next best move
     */
    public Board bestMove(){
        root.calculateChildren();
        System.out.println("root has: " + root.children.size() + " children");

        int remainder = root.children.size() % threads;
        int totalOffset = 0;
        int perThread = 1;

        List<Future<List<Node>>> futures = new ArrayList<>();
        if (root.children.size() > threads) perThread = (root.children.size() - remainder) / threads; //This is the number of nodes we need per thread to split equally;
        for (int i = 0; i < threads; i++) {
            //We are splitting the children into chunks for the threads
            if (remainder > totalOffset) totalOffset++;
            List<Node> children = root.children.stream().skip(perThread*i + totalOffset).limit(perThread + (remainder > totalOffset ? 1 : 0)).collect(Collectors.toList());
            System.out.println(i + " skipped:" + (perThread*i + totalOffset-1) + " computed: " + (perThread + (remainder > totalOffset ? 1 : 0)));
//            List<Node> children = root.children.stream().skip(threads*i + (remainder-- > 0 ? ++offset : 0)).limit((root.children.size()-root.children.size() % threads)/threads + (remainder > 0 ? 1 : 0)).collect(Collectors.toList());
            futures.add(executor.submit(new ComputeThread(children)));
        }
        //I clear the children and then add all the children from the futures (its the same children, but just computed this time)
        root.children.clear();
        futures.forEach(f -> {
            try {
                root.children.addAll(f.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
        futures.clear();
//        minmax(root, depth, -2000, 2000);


        if(!root.nodeBoard.isWhiteToMove()) {
            return minNode(root).nodeBoard;
        }
        else{
            //if white is to move, probably wont implement this for final submission
        }
        return null;
    }

    /**
     *
     * @return the node with the lowest score
     */
    private Node minNode(Node parent){
        List<Node> mins = new ArrayList<>();
        int min = 0;
        for (Node child : parent.children) {
            if (child.getScore() <= min) {
                if (child.getScore() < min) {
                    mins.clear();
                    mins.add(child);
                    min = child.getScore();
                } else mins.add(child);
            }
        }
        return mins.get(new Random().nextInt(mins.size()));
    }

    public void changeDepth(int x){
        this.depth = x;
    }

    public void shutdownThreads() {
        executor.shutdownNow();
    }

    public void killTree() {
        root.children.clear();
        root = null;
    }

    public void newRoot(Board board) {
        this.root = new Node(board);
    }

    public void recomputeTree(Board board) {
        root = new Node(board);
        bestMove();

        //Notes for Noah
        //
        //Completely redid tree building
        // The multi threading was also causing bugs anyways, but can probably be implemented best in calcChildren


//        System.out.println(root.children.size());
//        int remainder = root.children.size() % threads;
//        int offset = 0;
//        List<Future<List<Node>>> futures = new ArrayList<>();
//        for (int i = 0; i < threads; i++) {
//            List<Node> children = root.children.stream().skip(threads*i + (remainder-- > 0 ? ++offset : 0)).limit((root.children.size()-root.children.size() % threads)/threads + (remainder > 0 ? 1 : 0)).collect(Collectors.toList());
//            futures.add(executor.submit(new ComputeThread(children)));
//        }
//        root.children.clear();
//        futures.forEach(f -> {
//            try {
//                root.children.addAll(f.get());
//            } catch (InterruptedException | ExecutionException e) {
//                e.printStackTrace();
//            }
//        });
//        futures.clear();
    }

    public Node getRoot() {
        return root;
    }

    //since the nodes childrens are now ranked.... all you


    private class ComputeThread implements Callable<List<Node>> {

        private final List<Node> nodes;

        public ComputeThread(List<Node> nodes) {
            this.nodes = nodes;
        }

        @Override
        public List<Node> call() {
            long start = System.currentTimeMillis();
            for (Node node : nodes) {
                minmax(node, depth - 1, -2000, 2000);
                //node.calculateChildren(depth - 1);
            }
            System.out.println("finished " + nodes.size() + " nodes in " + (System.currentTimeMillis()-start) + "ms");
            return nodes;
        }
    }

    private class Node {

        private int score;
        private final Board nodeBoard;
        private ArrayList<Node> children;

        public Node(Board board) {
            nodeBoard = board;
        }

        public int getScore(){
            return score;
        }




        //To maximize MiniMax gains, Order the lists from best move to worst move for p

        /**
         * generates and populates the children nodes
         */
        public void calculateChildren(){
            children = new ArrayList<>();
            for (Piece piece : nodeBoard.getPieces()) {
                if (piece != null && piece.isBlack() != nodeBoard.isWhiteToMove()) {
                    int[][] possible = piece.computePossible(nodeBoard);
                    for (int[] coord : possible) {
                        Board childBoard = new Board(nodeBoard);//clone
                        childBoard.movePiece(piece.getColumn(),piece.getRow(),coord[0],coord[1]);
                        Node childNode = new Node(childBoard);
                        children.add(childNode);
                    }
                }
            }
            //sort here depending on white or black top optimize min max
            //children.sort();
        }
    }
}
