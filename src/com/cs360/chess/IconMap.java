package com.cs360.chess;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public final class IconMap {

    private static final Map<Integer, BufferedImage> whitePieces = new HashMap<>();
    private static final Map<Integer, BufferedImage> blackPieces = new HashMap<>();

    public static void loadIcons() {
        //0 = pawn
        //1 = rook
        //2 = knight
        //3 = bishop
        //4 = king
        //5 = queen
        //TODO load the pieces from resources
    }

    public static BufferedImage getIcon(int id, boolean isBlack) {
        return isBlack ? blackPieces.get(id) : whitePieces.get(id);
    }

}
