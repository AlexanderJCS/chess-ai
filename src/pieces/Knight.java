package pieces;

import helper.TextureMap;

public class Knight extends Piece {
    public Knight(boolean isWhite) {
        super(
                TextureMap.get((isWhite ? "white" : "black") + "_knight"),
                isWhite,
                new MovementMethod[]{ MovementMethod.KNIGHT }
        );
    }
}
