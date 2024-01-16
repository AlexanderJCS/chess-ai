package helper;

import jangl.graphics.textures.Texture;
import jangl.graphics.textures.TextureBuilder;

import java.util.HashMap;
import java.util.Map;

public class TextureMap {
    private static final Map<String, Texture> textures = new HashMap<>(){{
        put("board", new TextureBuilder().setImagePath("resources/board.png").setPixelatedScaling().toTexture());

        put("white_king", new Texture("resources/pieces/white_king.png"));
        put("white_queen", new Texture("resources/pieces/white_queen.png"));
        put("white_rook", new Texture("resources/pieces/white_rook.png"));
        put("white_bishop", new Texture("resources/pieces/white_bishop.png"));
        put("white_knight", new Texture("resources/pieces/white_knight.png"));
        put("white_pawn", new Texture("resources/pieces/white_pawn.png"));

        put("black_king", new Texture("resources/pieces/black_king.png"));
        put("black_queen", new Texture("resources/pieces/black_queen.png"));
        put("black_rook", new Texture("resources/pieces/black_rook.png"));
        put("black_bishop", new Texture("resources/pieces/black_bishop.png"));
        put("black_knight", new Texture("resources/pieces/black_knight.png"));
        put("black_pawn", new Texture("resources/pieces/black_pawn.png"));
    }};

    public static Texture get(String name) {
        return textures.get(name);
    }
}
