package pieces;

import board.Board;
import jangl.graphics.textures.Texture;
import move.Move;
import move.MoveType;
import org.joml.Vector2i;

import java.util.ArrayList;
import java.util.List;

public abstract class Piece {
    private final Texture texture;
    protected final boolean isWhite;
    protected final MovementMethod[] movementMethods;

    public Piece(Texture texture, boolean isWhite, MovementMethod[] methods) {
        this.isWhite = isWhite;
        this.texture = texture;
        this.movementMethods = methods;
    }

    public Texture getTexture() {
        return this.texture;
    }

    public boolean isWhite() {
        return this.isWhite;
    }

    private List<Move> getMovesRepeating(Board board, int index, MovementMethod method) {
        List<Move> moves = new ArrayList<>();
        Vector2i start = Board.indexToXY(index);
        Vector2i[] offsets = method.getOffsets();

        for (Vector2i offset : offsets) {
            Vector2i pos = new Vector2i(start);
            pos.add(offset);

            while (pos.x >= 0 && pos.x < Board.SIZE && pos.y >= 0 && pos.y < Board.SIZE) {
                int i = Board.xyToIndex(pos);

                if (board.getPiece(i) != null) {
                    // Capture the piece if it is the opposite color
                    if (board.getPiece(i).isWhite() != this.isWhite) {
                        moves.add(new Move(index, i, MoveType.CAPTURE));
                    }

                    break;
                }

                moves.add(new Move(index, i));
                pos.add(offset);
            }
        }

        return moves;
    }

    private List<Move> getMovesNoRepeating(Board board, int index, MovementMethod method) {
        List<Move> moves = new ArrayList<>();
        Vector2i start = Board.indexToXY(index);
        Vector2i[] offsets = method.getOffsets();

        for (Vector2i offset : offsets) {
            Vector2i pos = new Vector2i(start);
            pos.add(offset);

            if (pos.x < 0 || pos.x >= Board.SIZE || pos.y < 0 || pos.y >= Board.SIZE) {
                continue;
            }

            int i = Board.xyToIndex(pos);

            if (board.getPiece(i) != null) {
                // Capture the piece if it is the opposite color
                if (board.getPiece(i).isWhite() != this.isWhite) {
                    moves.add(new Move(index, i, MoveType.CAPTURE));
                }

                continue;
            }

            moves.add(new Move(index, i));
            pos.add(offset);
        }

        return moves;
    }

    public List<Move> getMoves(Board board, int index) {
        List<Move> moves = new ArrayList<>();

        for (MovementMethod method : this.movementMethods) {
            if (method.repeats()) {
                moves.addAll(this.getMovesRepeating(board, index, method));
            } else {
                moves.addAll(this.getMovesNoRepeating(board, index, method));
            }
        }

        return moves;
    }
}
