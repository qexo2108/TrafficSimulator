package simulation;

import graphics.*;

public class Parking
{
    public Parking(int x, int y)
    {
        this.x = x;
        this.y = y;

        graphics = new ParkingGraphics(x,y);
    }

    private int x, y;
    public ParkingGraphics graphics;


    int getLeftX()
    {
        return x;
    }
    int getRightX()
    {
        return x + Constants.parkingWidth;
    }
    int getUpperY()
    {
        return y;
    }
    int getLowerY()
    {
        return y + Constants.parkingHeight/2;
    }
}