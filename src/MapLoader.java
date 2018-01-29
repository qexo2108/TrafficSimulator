import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.File;
import java.util.Scanner;

public class MapLoader
{
    int r,j,c,p;

    Road[] roads;
    Junction[] junctions;
    Parking[] parkings;
    Car[] cars;

    Pane layout = new Pane();

    public void load(String fileName)
    {
        try
        {
            Scanner reader = new Scanner(new File(fileName));
            r = reader.nextInt();
            j = reader.nextInt();
            c = reader.nextInt();
            p = reader.nextInt();

            roads = new Road[r];
            int x, y, vx, vy, sp, rd, ln;
            String str;
            for(int i = 0; i < r; i++)
            {
                x = reader.nextInt();
                y = reader.nextInt();
                str = reader.nextLine();
                if (str.equals(" h"))
                    roads[i] = new Road(x, y, Road.types.horizontal);
                else
                    roads[i] = new Road(x, y, Road.types.vertical);
            }


            junctions = new Junction[j];
            for(int i = 0; i < j; i++)
                junctions[i] = new Junction(reader.nextInt(), reader.nextInt(), reader.nextInt());


            cars = new Car[c];
            for(int i = 0; i < c; i++)
            {
                x = reader.nextInt();
                y = reader.nextInt();
                vx = reader.nextInt();
                vy = reader.nextInt();
                sp = reader.nextInt();
                rd = reader.nextInt();
                ln = reader.nextInt();
                str = reader.nextLine();
                cars[i] = new Car(x, y, vx, vy, sp, rd, ln, ColorPicker.pick(str));
            }

            parkings = new Parking[p];
            for(int i=0; i<p; i++)
                parkings[i] = new Parking(reader.nextInt(), reader.nextInt());
        }
        catch(java.io.FileNotFoundException e)
        {
            System.out.println("File does not exist.");
        }

        // Screen clearing
        Rectangle rect = new Rectangle(0,0,Constants.windowWidth,Constants.windowHeight);
        rect.setFill(Color.WHITE);
        layout.getChildren().add(rect);

        for (int i = 0; i < r; i++)
            layout.getChildren().add(roads[i].graphics.getLayout());
        for (int i = 0; i < j; i++)
            layout.getChildren().add(junctions[i].graphics.getLayout());
        for (int i = 0; i < c; i++)
            layout.getChildren().add(cars[i].graphics.getLayout());
        for(int i=0; i<p; i++)
            layout.getChildren().add(parkings[i].graphics.getLayout());
    }
}