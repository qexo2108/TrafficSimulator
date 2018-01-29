import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class RoadGraphics
{
    RoadGraphics(int x, int y, Road.types type)
    {
        Rectangle lane1Rect;
        Rectangle lane2Rect;

        if(type == Road.types.horizontal)
        {
            lane1Rect = new Rectangle(x, y, Constants.windowWidth, Constants.carWidth);
            lane2Rect = new Rectangle(x, y + Constants.laneSpacing + Constants.carWidth, Constants.windowWidth, Constants.carWidth);
        }
        else
        {
            lane1Rect = new Rectangle(x, y, Constants.carWidth, Constants.windowHeight);
            lane2Rect = new Rectangle(x + Constants.laneSpacing + Constants.carWidth, y, Constants.carWidth, Constants.windowHeight);
        }
        layout = new Pane(lane1Rect, lane2Rect);
    }

    private Pane layout;

    public Pane getLayout()
    {
        return layout;
    }

}
