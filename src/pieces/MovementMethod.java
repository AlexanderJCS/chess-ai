package pieces;

import org.joml.Vector2i;

public enum MovementMethod {
    PAWN, KNIGHT, CARDINALS, DIAGONALS, KING;

    public Vector2i[] getOffsets() {
        return getOffsets(this);
    }

    public boolean repeats() {
        return repeats(this);
    }

    public static boolean repeats(MovementMethod method) {
        return switch (method) {
            case PAWN, KING, KNIGHT -> false;
            case CARDINALS, DIAGONALS -> true;
        };
    }

    public static Vector2i[] getOffsets(MovementMethod method) {
        return switch (method) {
            case PAWN -> new Vector2i[] { new Vector2i(0, 1) };
            case KNIGHT -> new Vector2i[] {
                    new Vector2i(1, 2),
                    new Vector2i(2, 1),
                    new Vector2i(2, -1),
                    new Vector2i(1, -2),
                    new Vector2i(-1, -2),
                    new Vector2i(-2, -1),
                    new Vector2i(-2, 1),
                    new Vector2i(-1, 2)
                };
            case CARDINALS -> new Vector2i[] {
                        new Vector2i(0, 1),
                        new Vector2i(1, 0),
                        new Vector2i(0, -1),
                        new Vector2i(-1, 0)
                };
            case DIAGONALS -> new Vector2i[] {
                        new Vector2i(1, 1),
                        new Vector2i(1, -1),
                        new Vector2i(-1, -1),
                        new Vector2i(-1, 1)
                };
            case KING -> new Vector2i[] {
                        new Vector2i(0, 1),
                        new Vector2i(1, 1),
                        new Vector2i(1, 0),
                        new Vector2i(1, -1),
                        new Vector2i(0, -1),
                        new Vector2i(-1, -1),
                        new Vector2i(-1, 0),
                        new Vector2i(-1, 1)
                };
        };
    }
}
