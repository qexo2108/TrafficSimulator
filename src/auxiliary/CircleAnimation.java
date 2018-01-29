package auxiliary;

import javafx.animation.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class CircleAnimation extends Application
{

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {
        Circle cir = new Circle();
        cir.setFill(Color.BLUE);
        cir.setRadius(40);
        cir.relocate(0,0);
        cir.setLayoutX(50);
        cir.setLayoutY(50);

        TranslateTransition transition = new TranslateTransition();
        transition.setNode(cir);
        transition.setDuration(Duration.seconds(3));
        transition.setByX(500);
        transition.setByY(500);
        transition.setAutoReverse(true);
        transition.setCycleCount(1);
        //transition.play();

        ScaleTransition scaleTrans = new ScaleTransition(Duration.seconds(3), cir);
        scaleTrans.setCycleCount(1);
        scaleTrans.setAutoReverse(true);
        scaleTrans.setToX(0.5);
        scaleTrans.setToY(0.5);
        //scaleTrans.play();

        FillTransition fillTrans = new FillTransition(Duration.seconds(3),cir);
        fillTrans.setCycleCount(1);
        fillTrans.setAutoReverse(true);
        fillTrans.setFromValue(Color.BLUE);
        fillTrans.setToValue(Color.RED);
        //fillTrans.play();

        SequentialTransition st = new SequentialTransition(cir, transition, scaleTrans, fillTrans);
        st.play();

        Pane layout = new Pane();

        Scene scene = new Scene(layout, 700, 700);
        layout.getChildren().add(cir);

        primaryStage.setTitle("Transition of the blue circle");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
