// JOSE VELAZQUEZ
// MODULE 7.2 ASSIGNMENT 
// DATE: 04/29/2026
// This is a JavaFX application to test circle styles using a reference to a CSS file.


import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class CircleStyleTest extends Application {

    @Override
    public void start(Stage primaryStage) {

        Circle circle1 = new Circle(50);
        Circle circle2 = new Circle(50);
        Circle circle3 = new Circle(50);
        Circle circle4 = new Circle(50);

        // Apply CSS style class
        circle1.getStyleClass().add("whitecircle");
        circle2.getStyleClass().add("whitecircle");

        // Apply CSS IDs
        circle3.setId("redcircle");
        circle4.setId("greencircle");

        HBox pane = new HBox(20);
        pane.setAlignment(Pos.CENTER);
        pane.getChildren().addAll(circle1, circle2, circle3, circle4);

        Scene scene = new Scene(pane, 500, 200);

        // Link external CSS file
        scene.getStylesheets().add("mystyle.css");

        primaryStage.setTitle("Module 7 Circle Style Test");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Test code
        testCircleStyles(circle1, circle2, circle3, circle4);
    }

    public void testCircleStyles(Circle c1, Circle c2, Circle c3, Circle c4) {
        System.out.println("Running tests...");

        if (c1.getStyleClass().contains("whitecircle")) {
            System.out.println("Test Passed: Circle 1 uses whitecircle class.");
        }

        if (c2.getStyleClass().contains("whitecircle")) {
            System.out.println("Test Passed: Circle 2 uses whitecircle class.");
        }

        if ("redcircle".equals(c3.getId())) {
            System.out.println("Test Passed: Circle 3 uses redcircle ID.");
        }

        if ("greencircle".equals(c4.getId())) {
            System.out.println("Test Passed: Circle 4 uses greencircle ID.");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}