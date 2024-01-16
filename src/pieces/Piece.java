package pieces;

import jangl.graphics.textures.Texture;

public abstract class Piece {
    private final Texture texture;
    protected final boolean isWhite;

    public Piece(Texture texture, boolean isWhite) {
        this.isWhite = isWhite;
        this.texture = texture;
    }

    public Texture getTexture() {
        return this.texture;
    }
}
