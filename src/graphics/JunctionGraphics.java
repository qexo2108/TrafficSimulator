package graphics;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

import simulation.Constants;

public class JunctionGraphics
{
    public JunctionGraphics(int x, int y, Pane lightsLayout)
    {
        Circle cir = new Circle(x, y, Constants.junctionRadius, Constants.junctionColor);
        layout = new Pane(cir, lightsLayout);
    }

    private Pane layout;

    public Pane getLayout()
    {
        return layout;
    }
}
