package simulation;

import javafx.scene.paint.Color;

public final class Constants
{
    public static final int windowWidth = 1100;
    public static final int windowHeight = 800;

    public static final int carLength = 40;
    public static final int carWidth = 20;
    public static final int accelerationLatency = 9;


    public static final int junctionRadius = 30;
    public static final Color junctionColor = Color.BLUE;
    public static final int safetySpace = 14;
    // has to be bigger than sum (sp+..+3+2+1) of max speed of any vehicle
    // (because of each car slowing down before stopping)

    public static final int laneSpacing = 10;
    public static final int laneSafety = 150;


    public static final int parkingWidth  = 100;
    public static final int parkingHeight = 100;
    public static final Color parkingColor = Color.DIMGRAY;


    private Constants(){}
}
