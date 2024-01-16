import jangl.Jangl;
import jangl.color.ColorFactory;
import jangl.io.Window;

public class Main {
    public static void main(String[] args) {
        Jangl.init(0.75f, 16f / 9);
        Window.setTitle("Chess");
        Window.setVsync(true);
        Window.setResizable(true);
        Window.setClearColor(ColorFactory.fromNorm(0.1f, 0.1f, 0.1f, 1));

        Chess chess = new Chess();
        chess.run();

        Window.close();
    }
}