package board;

import jangl.color.Color;
import jangl.color.ColorFactory;
import jangl.graphics.textures.MutableTexture;
import jangl.graphics.textures.TextureBuilder;
import move.Move;
import org.joml.Vector2i;


public class BoardTexture extends MutableTexture {
    private static final Color LIGHT_COLOR = ColorFactory.from255(170, 200, 180, 255);
    private static final Color DARK_COLOR = ColorFactory.from255(60, 120, 70, 255);
    private static final Color SELECT_COLOR = ColorFactory.from255(170, 170, 80, 255);


    public BoardTexture() {
        super(genBuilder());
        this.resetBoard();
    }

    private static TextureBuilder genBuilder() {
        return new TextureBuilder()
                .fill(ColorFactory.BLACK, Board.SIZE, Board.SIZE)
                .setPixelatedScaling();
    }

    /**
     * Removes all selection
     */
    public void resetBoard() {
        for (int x = 0; x < Board.SIZE; x++) {
            for (int y = 0; y < Board.SIZE; y++) {
                this.setPixelAt(x, y, (x + y) % 2 == 0 ? LIGHT_COLOR : DARK_COLOR);
            }
        }
    }

    public void showMove(Move move) {
        Vector2i initial = Board.indexToXY(move.initial());
        Vector2i target = Board.indexToXY(move.target());

        this.setPixelAt(initial.x, initial.y, SELECT_COLOR);
        this.setPixelAt(target.x, target.y, SELECT_COLOR);
    }
}
