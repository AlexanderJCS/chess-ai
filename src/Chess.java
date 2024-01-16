import board.Board;
import jangl.Jangl;
import jangl.io.Window;

public class Chess {
    private final Board board;

    public Chess() {
        this.board = new Board();
        this.board.parseFen("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR");
    }

    public void draw() {
        Window.clear();
        this.board.draw();
    }

    public void update() {
        this.board.update();
    }

    public void run() {
        while (Window.shouldRun()) {
            this.update();
            this.draw();

            Jangl.update();
        }
    }
}
