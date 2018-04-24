package auxiliary.demos;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MyPaint extends Application
{
/*
    public static void main(String[] args)
    {
        launch(args);
    }
*/
    @Override
    public void start(Stage primaryStage)
    {
        gc = canvas.getGraphicsContext2D();
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1);

        cp.setValue(Color.BLACK);
        cp.setOnAction(e->
        {
            gc.setStroke(cp.getValue());
        });

        thicknessSlider.setMin(1);
        thicknessSlider.setMax(100);
        thicknessSlider.setShowTickLabels(true);
        thicknessSlider.setShowTickMarks(true);
        thicknessSlider.valueProperty().addListener(e->
        {
            double value = thicknessSlider.getValue();
            String str = String.format("%.1f", value);
            label.setText(str);
            gc.setLineWidth(value);
        });

        layout2.addRow(0, cp, thicknessSlider, label);
        layout2.setHgap(20);
        layout2.setAlignment(Pos.TOP_CENTER);
        layout2.setPadding(new Insets(20,0,0,0));

        scene.setOnMousePressed( e->
        {
             gc.beginPath();
             gc.lineTo(e.getSceneX(),e.getSceneY());
             gc.stroke();
        });

        scene.setOnMouseDragged(e->
        {
            gc.lineTo(e.getSceneX(), e.getSceneY());
            gc.stroke();
        });

        layout.getChildren().addAll(canvas, layout2);

        primaryStage.setTitle("Mój własny paint");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    Canvas canvas = new Canvas(1366,768);    // Na tym się rysuje
    GraphicsContext gc;                                 // Tym się rysuje
    ColorPicker cp = new ColorPicker();
    Slider thicknessSlider = new Slider();
    Label label = new Label("1.0");

    StackPane layout = new StackPane();
    Scene scene = new Scene(layout, 1366, 768);
    GridPane layout2 = new GridPane();
}