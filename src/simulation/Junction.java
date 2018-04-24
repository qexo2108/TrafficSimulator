package simulation;

import graphics.JunctionGraphics;

public class Junction
{
    public Junction(int x, int y, int interval)
    {
        this.x = x;
        this.y = y;

        lights = new TrafficLights(interval,  x,  y);

        graphics = new JunctionGraphics(x, y, lights.graphics.getLayout());
    }

    public JunctionGraphics graphics;
    public TrafficLights lights;

    private int x;
    private int y;

    int getLeftBorder()
    {
        return x - Constants.junctionRadius;
    }
    int getRightBorder()
    {
        return x + Constants.junctionRadius;
    }
    int getUpperBorder()
    {
        return y - Constants.junctionRadius;
    }
    int getLowerBorder()
    {
        return y + Constants.junctionRadius;
    }
}