package simulation;

import graphics.*;

public class Road
{
    public Road(int x, int y, types type)
    {
        this.x = x;
        this.y = y;

        if(type == types.horizontal)
        {
            lanes[0] = new boolean[Constants.windowWidth + Constants.laneSafety];
            lanes[1] = new boolean[Constants.windowWidth + Constants.laneSafety];
            for(int i=0; i< Constants.windowWidth + Constants.laneSafety; i++)
            {
                lanes[0][i] = false;
                lanes[1][i] = false;
            }
        }
        else
        {
            lanes[0] = new boolean[Constants.windowHeight + Constants.laneSafety];
            lanes[1] = new boolean[Constants.windowHeight + Constants.laneSafety];
            for(int i=0; i< Constants.windowHeight + Constants.laneSafety; i++)
            {
                lanes[0][i] = false;
                lanes[1][i] = false;
            }
        }

        graphics = new RoadGraphics(x,y, type);
    }

    public enum types
    {
        vertical,
        horizontal
    }

    private int x,y;
    boolean[][] lanes = new boolean[2][];
    public RoadGraphics graphics;


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}