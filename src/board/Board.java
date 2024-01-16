package board;

import helper.TextureMap;
import jangl.coords.WorldCoords;
import jangl.graphics.textures.Image;
import jangl.shapes.Rect;
import move.Move;
import org.joml.Vector2i;
import pieces.Piece;

public class Board {
    public static final int SIZE = 8;
    private final Piece[] board;
    private final Rect[] rects;

    /**
     * The top right corner of the screen, in WorldCoords. Used to change the board offset if the window is resized
     */
    private WorldCoords topRight;
    private final Image background;
    private float prevXOffset;

    public Board() {
        this.board = new Piece[SIZE * SIZE];
        this.rects = new Rect[SIZE * SIZE];
        this.topRight = WorldCoords.getTopRight();
        this.prevXOffset = 0;

        float rectSize = WorldCoords.getTopRight().y / SIZE;

        for (int i = 0; i < this.rects.length; i++) {
            int x = i % SIZE;
            int y = i / SIZE;

            WorldCoords topLeft = new WorldCoords(
                    x * rectSize,
                    (SIZE - y) * rectSize
            );

            this.rects[i] = new Rect(topLeft, rectSize, rectSize);
        }

        this.background = new Image(
                new Rect(new WorldCoords(0, 1), 1, 1),
                TextureMap.get("board")
        );

        this.applyOffset();
    }

    public void parseFen(String fen) {
        String[] split = fen.split("/");

        for (int y = 0; y < split.length; y++) {
            String row = split[y];
            int index = 0;

            for (int x = 0; x < row.length(); x++) {
                char c = row.charAt(x);

                if (Character.isDigit(c)) {
                    index += Character.getNumericValue(c);
                    continue;
                }

                boolean isWhite = Character.isUpperCase(c);

                Piece piece = switch (Character.toLowerCase(c)) {
                    case 'p' -> new pieces.Pawn(isWhite);
                    case 'r' -> new pieces.Rook(isWhite);
                    case 'n' -> new pieces.Knight(isWhite);
                    case 'b' -> new pieces.Bishop(isWhite);
                    case 'q' -> new pieces.Queen(isWhite);
                    case 'k' -> new pieces.King(isWhite);
                    default -> null;
                };

                this.board[index + y * SIZE] = piece;
                index++;
            }
        }
    }

    private void applyOffset() {
        float rectSize = WorldCoords.getTopRight().y / SIZE;
        float xOffset = WorldCoords.getMiddle().x - rectSize * SIZE / 2;
        float delta = xOffset - this.prevXOffset;

        this.background.shape().getTransform().shift(delta, 0);

        for (Rect rect : this.rects) {
            rect.getTransform().shift(delta, 0);
        }

        this.prevXOffset = xOffset;
        this.topRight = WorldCoords.getTopRight();
    }

    public void update() {
        WorldCoords topRight = WorldCoords.getTopRight();

        if (this.topRight.x != topRight.x || this.topRight.y != topRight.y) {
            this.applyOffset();
        }
    }

    public void draw() {
        this.background.draw();

        for (int i = 0; i < this.rects.length; i++) {
            Piece piece = this.getPiece(i);
            if (piece == null) {
                continue;
            }

            this.rects[i].draw(piece.getTexture());
        }
    }

    public Piece getPiece(int x, int y) {
        return this.board[x + y * SIZE];
    }

    public Piece getPiece(Vector2i pos) {
        return this.getPiece(pos.x, pos.y);
    }

    public Piece getPiece(int index) {
        return this.board[index];
    }

    public static Vector2i indexToXY(int index) {
        return new Vector2i(index % SIZE, index / SIZE);
    }

    public static int xyToIndex(int x, int y) {
        return x + y * SIZE;
    }

    public static int xyToIndex(Vector2i pos) {
        return Board.xyToIndex(pos.x, pos.y);
    }

    public void runMove(Move move) {
        int initial = move.initial();
        int target = move.target();

        this.board[target] = this.board[initial];
        this.board[initial] = null;
    }
}
