package move;

public record Move(int initial, int target, MoveType type) {
    public Move(int initial, int target) {
        this(initial, target, MoveType.NORMAL);
    }
}
