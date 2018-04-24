package io;

import javafx.scene.paint.Color;

public final class ColorPicker
{
    public static Color pick(String str)
    {
        switch(str)
        {
            case " go":
                return Color.GOLD;
            case " gr":
                return Color.GREEN;
            case " ma":
                return Color.MAGENTA;
            case " dv":
                return Color.DARKVIOLET;
            case " dc":
                return Color.DARKCYAN;
            case " re":
                return Color.RED;
            case " skyblue":
                return Color.SKYBLUE;
            case " sienna":
                return Color.SIENNA;
            case " palevioletred":
                return Color.PALEVIOLETRED;
            default:
                return Color.ORANGE;
        }
    }


    private ColorPicker(){}
}
