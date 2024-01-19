import board.Board;
import jangl.Jangl;
import jangl.coords.WorldCoords;
import jangl.io.Window;
import jangl.io.mouse.Mouse;
import jangl.io.mouse.MouseEvent;
import org.lwjgl.glfw.GLFW;

public class Chess {
    private final Board board;
    private final boolean whiteTurn;

    public Chess() {
        this.board = new Board();
        this.board.parseFen("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR");
        this.whiteTurn = true;
    }

    public void draw() {
        Window.clear();
        this.board.draw();
    }

    private void select(WorldCoords coords) {
        this.board.select(coords);
    }

    public void update() {
        this.board.update();

        for (MouseEvent event : Mouse.getEvents()) {
            if (event.action == GLFW.GLFW_PRESS) {
                this.select(Mouse.getMousePos());
            }
        }
    }

    public void run() {
        while (Window.shouldRun()) {
            this.update();
            this.draw();

            Jangl.update();
        }
    }
}
