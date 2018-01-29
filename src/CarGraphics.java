import javafx.animation.RotateTransition;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class CarGraphics
{

    CarGraphics(int x, int y, Color color, int vx)
    {
        if(vx == 0)
            rotate();

        rect.relocate(x,y);
        rect.setFill(color);

        layout = new Pane(rect);
    }

    private Rectangle rect = new Rectangle(Constants.carLength, Constants.carWidth);

    void rotate()
    {
        RotateTransition rt = new RotateTransition();
        rt.setNode(rect);
        rt.setToAngle(90);
        rt.play();
    }
    void relocate(int x, int y)
    {
        rect.relocate(x,y);
    }

    private Pane layout;
    public Pane getLayout()
    {
        return layout;
    }
}