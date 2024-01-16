package pieces;

import helper.TextureMap;

public class Bishop extends Piece {
    public Bishop(boolean isWhite) {
        super(
                TextureMap.get((isWhite ? "white" : "black") + "_bishop"),
                isWhite,
                new MovementMethod[]{ MovementMethod.DIAGONALS }
        );
    }
}
