package auxiliary.demos;

import javafx.animation.Animation;
import javafx.animation.FillTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class RectangleAnimation extends Application
{

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {
        /*
        cir.relocate(0,0);
        cir.setLayoutX(50);
        cir.setLayoutY(50);
        */

        Rectangle car1 = new Rectangle(50,30,100,60);
        car1.setFill(Color.CORAL);


        TranslateTransition transition = new TranslateTransition();
        transition.setNode(car1);
        transition.setDuration(Duration.seconds(3));
        transition.setByX(500);
        transition.setByY(0);
        transition.setAutoReverse(true);
        //transition.setCycleCount(Animation.INDEFINITE);
        transition.play();


        Pane layout = new Pane();

        Scene scene = new Scene(layout, 700, 300);
        layout.getChildren().add(car1);

        primaryStage.setTitle("Car movement model");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
