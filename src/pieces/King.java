package pieces;

import helper.TextureMap;

public class King extends Piece {
    public King(boolean isWhite) {
        super(
                TextureMap.get((isWhite ? "white" : "black") + "_king"),
                isWhite,
                new MovementMethod[]{ MovementMethod.KING }
        );
    }
}
