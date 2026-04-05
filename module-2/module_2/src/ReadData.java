import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadData extends Application {

    @Override
    public void start(Stage stage) {
        TextArea display = new TextArea();
        display.setEditable(false);

        Button readButton = new Button("Read Data");

        // Lambda expression
        readButton.setOnAction(e -> {
            display.clear();

            try (BufferedReader reader = new BufferedReader(
                    new FileReader("JoseVelazquez_datafile.dat"))) {

                String line;
                while ((line = reader.readLine()) != null) {
                    display.appendText(line + "\n");
                }

            } catch (IOException ex) {
                display.setText("Error reading file.");
            }
        });

        VBox root = new VBox(10, readButton, display);
        root.setAlignment(Pos.CENTER);

        Scene scene = new Scene(root, 400, 300);
        stage.setTitle("Read Data Program");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}