package com.cs360.chess.ai;

import com.cs360.chess.Board;
import com.cs360.chess.piece.Piece;
import javafx.scene.Node;
import org.apache.xpath.objects.XNodeSet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

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
        root = new Node(null,board);
        this.depth = depth;
        root.calculateChildren(depth);
    }
    public void changeDepth(int x){
        depth = x;
    }
    private int evaluate() {
        return 0;
    }

    public Node getRoot() {
        return root;
    }

    private class Node {
        Node parent;
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
            Piece[][] pieces = boardFuture.getBoard();
            for (int column = 0; column < 8; column++) {
                for (int row = 0; row < 8; row++) {
                    if(pieces[column][row]!=null && pieces[column][row].isBlack()!=boardFuture.isWhiteToMove()){
                        int[][] posMoves=pieces[column][row].computePossible(boardFuture);
                        for(int[] coord : posMoves) {
                            Board childBoard = new Board(boardFuture);//clone
                            childBoard.movePiece(column,row,coord[0],coord[1]);
                            Node childNode = new Node(this,boardFuture);
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
}
