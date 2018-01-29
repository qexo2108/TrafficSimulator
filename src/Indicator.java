import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Indicator
{
    Indicator(int x, int y, Color startColor)
    {
        Rectangle background = new Rectangle(x - 10, y - 10, 20, 20);

        cir = new Circle(x, y, 8, startColor);

        layout = new Pane(background,cir);
    }

    private Circle cir;
    Pane layout;

    void changeColor(Color color)
    {
        cir.setFill(color);
    }
}
