package pieces;

import helper.TextureMap;

public class Queen extends Piece {
    public Queen(boolean isWhite) {
        super(
                TextureMap.get((isWhite ? "white" : "black") + "_queen"),
                isWhite
        );
    }
}
