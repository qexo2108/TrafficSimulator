public class TrafficLights
{
    public enum states {
        green,
        yellow,
        red
    }

    TrafficLights(int interval, int x, int y)
    {
        this.interval = interval;
        graphics = new TrafficLightsGraphics(x,y);

        horState = states.green;
        verState = states.red;

        i = 0;
    }

    private int i;
    private int interval;
    private states verState, horState;

    TrafficLightsGraphics graphics;

    public void update()
    {
        if(i==3*interval) {
            horState = states.yellow;
            verState = states.yellow;
            graphics.update(horState);
        }
        if(i==4*interval) {
            horState = states.red;
            verState = states.green;
            graphics.update(horState);
        }
        if(i==7*interval) {
            horState = states.yellow;
            verState = states.yellow;
            graphics.update(horState);
        }
        if(i==8*interval)
        {
            horState = states.green;
            verState = states.red;
            graphics.update(horState);
            i=0;
        }
        i++;
    }

    public states getHorState()
    {
        return horState;
    }
    public states getVerState()
    {
        return verState;
    }
}