package pieces;

import helper.TextureMap;

public class Rook extends Piece {
    public Rook(boolean isWhite) {
        super(
                TextureMap.get((isWhite ? "white" : "black") + "_rook"),
                isWhite,
                new MovementMethod[]{ MovementMethod.CARDINALS }
        );
    }
}
