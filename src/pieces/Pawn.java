package pieces;

import helper.TextureMap;

public class Pawn extends Piece {
    public Pawn(boolean isWhite) {
        super(
                TextureMap.get((isWhite ? "white" : "black") + "_pawn"),
                isWhite,
                new MovementMethod[]{ MovementMethod.PAWN }
        );
    }
}
