import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import io.MapLoader;
import simulation.Constants;


public class TrafficSimulator extends Application {

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage)
    {
        String path = "map1.txt";
        MapLoader map = new MapLoader();
        map.load(path);

        AnimationTimer timer = new AnimationTimer()
        {
            @Override
            public void handle(long now)
            {
                for (int i = 0; i < map.j; i++)
                    map.junctions[i].lights.update();

                for (int i = 0; i < map.c; i++)
                    map.cars[i].update(map);
            }
        };


        Button button1 = new Button("Play");
        button1.setLayoutX(450);
        button1.setOnAction(e -> {
            timer.start();
        });
        Button button2 = new Button("Pause");
        button2.setLayoutX(570);
        button2.setOnAction(e ->
        {
            timer.stop();
        });
        Button button3 = new Button("Load Map1");
        button3.setLayoutX(25);
        button3.setOnAction(e ->
        {
            timer.stop();
            map.load("map1.txt");
        });
        Button button4 = new Button("Load Map2");
        button4.setLayoutX(25);
        button4.setLayoutY(30);
        button4.setOnAction(e ->
        {
            timer.stop();
            map.load("map2.txt");
        });


        Pane layout = new Pane();
        layout.getChildren().addAll(map.getLayout(), button1, button2,button3,button4);

        Scene scene = new Scene(layout, Constants.windowWidth, Constants.windowHeight);

        primaryStage.setTitle("Traffic simulation");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}