import javafx.scene.paint.Color;

public class Car
{
    Car(int x, int y, int vx, int vy, int speed, int road, int lane, Color desiredColor)
    {
        this.x=x;
        this.y=y;
        currentSpeed = speed;
        maxSpeed = speed;
        this.vx = vx;
        this.vy = vy;
        initialX = x;
        initialY = y;
        this.road = road;
        this.lane = lane;

        graphics = new CarGraphics(x,y, desiredColor, vx);
    }

    CarGraphics graphics;


    private int x, y, vx, vy;
    private int dx, dy;
    private int currentSpeed, maxSpeed;
    private int initialX, initialY;
    private int road, lane;

    private int i=0;
    public void update(MapLoader map)
    {
        dx = vx * currentSpeed;
        dy = vy * currentSpeed;

        if(collisionDetection(map.junctions, map.j) || parkingDetection(map.parkings, map.p) || carDetection(map, 0))
        {
            i = 0;
            slowDown();
        }
        else
        {
            i++;
            if (i >= Constants.accelerationLatency && !carDetection(map, Constants.safetySpace/2))
            {
                speedUp();
                i = 0;
            }
        }

        dx = vx * currentSpeed;
        dy = vy * currentSpeed;


        // Updating lane occupancy table
        if(dx != 0)
        {
            if(dx > 0)
            {
                for (int i = getLeftX(); i <= getLeftX() + dx; i++)
                    map.roads[road].lanes[lane][i] = false;
                if(getRightX() < Constants.windowWidth)
                    for (int i = getRightX(); i < getRightX() + dx; i++)
                        map.roads[road].lanes[lane][i] = true;
            }
            else // (dx < 0)
            {
                if (getRightX() + dx >= 0)
                    for (int i = getRightX(); i >= getRightX() + dx; i--)
                        map.roads[road].lanes[lane][i] = false;
                if (getLeftX() + dx >= 0)
                    for (int i = getLeftX(); i >= getLeftX() + dx; i--)
                        map.roads[road].lanes[lane][i] = true;
            }
        }
        else // (dy != 0)
        {
            if(dy > 0)
            {
                for (int i = getUpperY(); i < getUpperY() + dy; i++)
                    map.roads[road].lanes[lane][i] = false;
                if(getLowerY() < Constants.windowHeight)
                    for (int i = getLowerY(); i <= getLowerY() + dy; i++)
                        map.roads[road].lanes[lane][i] = true;
            }
            else // (dy < 0)
            {
                if (getLowerY() + dy >= 0)
                    for (int i = getLowerY(); i >= getLowerY() + dy; i--)
                        map.roads[road].lanes[lane][i] = false;
                if (getUpperY() + dy >= 0)
                    for (int i = getUpperY(); i >= getUpperY() + dy; i--)
                        map.roads[road].lanes[lane][i] = true;
            }
        }
        x += dx;
        y += dy;
        graphics.relocate(x, y);

        // Check whether to relocate car from "dead zone" back to map
        if(x > Constants.windowWidth + Constants.carLength + Constants.safetySpace || x < -(Constants.carLength + Constants.safetySpace) ||
                y > Constants.windowHeight + Constants.carLength + Constants.safetySpace || y < -(Constants.carLength + Constants.safetySpace) ||
                (parkingDetection(map.parkings, map.p) && currentSpeed == 0) )
        {
            if(vx!=0){
                if(!map.roads[road].lanes[lane][initialX] && !map.roads[road].lanes[lane][initialX+Constants.carLength])
                {
                    if(parkingDetection(map.parkings, map.p))
                        for(int i=getLeftX(); i<=getRightX(); i++)
                            map.roads[road].lanes[lane][i] = false;
                    x = initialX;
                    y = initialY;
                } }
            else {
                if(!map.roads[road].lanes[lane][initialY] && !map.roads[road].lanes[lane][initialY+Constants.carLength])
                {
                    if(parkingDetection(map.parkings, map.p))
                        for(int i=getUpperY(); i<=getLowerY(); i++)
                            map.roads[road].lanes[lane][i] = false;
                    x = initialX;
                    y = initialY;
                } }

        }
    }

    private void slowDown()
    {
        if(currentSpeed > 0)
            currentSpeed--;
    }

    private void speedUp()
    {
        if(currentSpeed < maxSpeed)
            currentSpeed++;
    }

    private boolean carDetection(MapLoader map, int additionalSpace)
    {
        int safetySpace = Constants.safetySpace + additionalSpace;
        if(vx != 0)
        {
            if(vx > 0)
                return(map.roads[road].lanes[lane][getRightX()+dx+safetySpace]);
            else
                if(getLeftX()+dx-safetySpace > 0)
                    return(map.roads[road].lanes[lane][getLeftX()+dx-safetySpace]);
                else
                    return false;
        }
        else // vy != 0
        {
            if(vy > 0)
                return(map.roads[road].lanes[lane][ getLowerY() + dy + safetySpace]);
            else
            if(getUpperY() + dy - safetySpace  >  0)
                return(map.roads[road].lanes[lane][getUpperY() + dy - safetySpace]);
            else
                return false;
        }
    }

    private boolean parkingDetection(Parking[] parkings, int p)
    {
        for(int i=0; i<p; i++)
            if( parkingDetector(parkings[i]))
                return true;
        return false;
    }

    private boolean parkingDetector(Parking parking)
    {
        if(getLeftX() > parking.getLeftX() && getRightX() < parking.getRightX() &&
                getUpperY() > parking.getUpperY() && getLowerY() < parking.getLowerY())
            return true;
        return false;
    }

    private boolean collisionDetection(Junction[] junctions, int n)
    {
        for(int i=0; i<n; i++)
        {
            if(collisionDetector(junctions[i]))
                return true;
        }
        return false;
    }

    private boolean collisionDetector(Junction junction)
    {
        int carBord, jBord;
        if(vy == 0 && getUpperY() > junction.getUpperBorder() && getUpperY() < junction.getLowerBorder())
        {
            if(vx == 1)
            {
                carBord = getRightX();
                jBord = junction.getLeftBorder();
                return ( carBord > jBord - Constants.safetySpace && carBord < jBord && junction.lights.getHorState()!= TrafficLights.states.green);

            }
            else // vx = -1
            {
                carBord = getLeftX();
                jBord = junction.getRightBorder();
                return (carBord < jBord + Constants.safetySpace && carBord>jBord && junction.lights.getHorState()!= TrafficLights.states.green);
            }
        }
        else // vx == 0
            if(getLeftX() > junction.getLeftBorder() && getLeftX() < junction.getRightBorder())
            {
                if(vy == 1)
                {
                    carBord = getLowerY();
                    jBord = junction.getUpperBorder();
                    return(carBord > jBord - Constants.safetySpace && carBord < jBord && junction.lights.getVerState()!= TrafficLights.states.green);
                }
                else // vy == -1
                {
                    carBord = getUpperY();
                    jBord = junction.getLowerBorder();
                    return(carBord < jBord + Constants.safetySpace && carBord > jBord && junction.lights.getVerState()!= TrafficLights.states.green);
                }
            }

        return false;
    }


    private int getLeftX()
    {
        if(vy == 0)
            return x;
        else
            return x + 10;
    }
    private int getRightX()
    {
        if(vy == 0)
            return x+40;
        else
            return x+30;
    }
    private int getUpperY()
    {
        if(vy == 0)
            return y;
        else
            return y-10;
    }
    private int getLowerY()
    {
        if(vy == 0)
            return y+20;
        else
            return y+30;
    }
}