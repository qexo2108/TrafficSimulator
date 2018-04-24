package graphics;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import simulation.Constants;
import simulation.TrafficLights;

public class TrafficLightsGraphics
{

    public TrafficLightsGraphics(int x, int y)
    {
        hor = new Indicator(x-2* Constants.junctionRadius, y, Color.GREEN);
        hor2 = new Indicator(x+2*Constants.junctionRadius, y, Color.GREEN);
        ver = new Indicator(x, y-2*Constants.junctionRadius, Color.RED);
        ver2 = new Indicator(x, y+2*Constants.junctionRadius, Color.RED);
        layout = new Pane(hor.layout, hor2.layout, ver.layout, ver2.layout);
    }

    private Indicator hor;
    private Indicator hor2;
    private Indicator ver;
    private Indicator ver2;

    private Pane layout;

    public void update(TrafficLights.states horState)
    {
        if(horState == TrafficLights.states.green)
        {
            hor.changeColor(Color.GREEN);
            hor2.changeColor(Color.GREEN);
            ver.changeColor(Color.RED);
            ver2.changeColor(Color.RED);
        }
        else if(horState == TrafficLights.states.yellow)
        {
            hor.changeColor(Color.YELLOW);
            hor2.changeColor(Color.YELLOW);
            ver.changeColor(Color.YELLOW);
            ver2.changeColor(Color.YELLOW);
        }
        else
        {
            hor.changeColor(Color.RED);
            hor2.changeColor(Color.RED);
            ver.changeColor(Color.GREEN);
            ver2.changeColor(Color.GREEN);
        }
    }

    public Pane getLayout()
    {
        return layout;
    }

}
