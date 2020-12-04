package com.cs360.chess;

import com.cs360.chess.piece.*;
import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

public final class IconMap {

    private static final Map<Integer, Image> whitePieces = new HashMap<>();
    private static final Map<Integer, Image> blackPieces = new HashMap<>();

    public static void loadIcons() {
        whitePieces.put(0, new Image(IconMap.class.getResourceAsStream("/images/pawn_light.svg")));
        whitePieces.put(1, new Image(IconMap.class.getResourceAsStream("/images/rook_light.svg")));
        whitePieces.put(2, new Image(IconMap.class.getResourceAsStream("/images/knight_light.svg")));
        whitePieces.put(3, new Image(IconMap.class.getResourceAsStream("/images/bishop_light.svg")));
        whitePieces.put(4, new Image(IconMap.class.getResourceAsStream("/images/king_light.svg")));
        whitePieces.put(5, new Image(IconMap.class.getResourceAsStream("/images/queen_light.svg")));
        
        blackPieces.put(0, new Image(IconMap.class.getResourceAsStream("/images/pawn_dark.svg")));
        blackPieces.put(1, new Image(IconMap.class.getResourceAsStream("/images/rook_dark.svg")));
        blackPieces.put(2, new Image(IconMap.class.getResourceAsStream("/images/knight_dark.svg")));
        blackPieces.put(3, new Image(IconMap.class.getResourceAsStream("/images/bishop_dark.svg")));
        blackPieces.put(4, new Image(IconMap.class.getResourceAsStream("/images/king_dark.svg")));
        blackPieces.put(5, new Image(IconMap.class.getResourceAsStream("/images/queen_dark.svg")));
    }

    public static Image getIcon(Piece piece) {
        if (piece instanceof Pawn) return (piece.isBlack() ? blackPieces : whitePieces).get(0);
        else if (piece instanceof Rook) return (piece.isBlack() ? blackPieces : whitePieces).get(1);
        else if (piece instanceof Knight) return (piece.isBlack() ? blackPieces : whitePieces).get(2);
        else if (piece instanceof Bishop) return (piece.isBlack() ? blackPieces : whitePieces).get(3);
        else if (piece instanceof King) return (piece.isBlack() ? blackPieces : whitePieces).get(4);
        else return (piece.isBlack() ? blackPieces : whitePieces).get(5);
    }

}
