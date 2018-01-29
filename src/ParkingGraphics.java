import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class ParkingGraphics
{
    ParkingGraphics(int x, int y)
    {
        Rectangle rect = new Rectangle(x, y, Constants.parkingWidth, Constants.parkingHeight);
        rect.setFill(Constants.parkingColor);

        layout = new Pane(rect);
    }

    private Pane layout;

    public Pane getLayout()
    {
        return layout;
    }
}
