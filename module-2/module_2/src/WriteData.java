import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class WriteData extends Application {

    @Override
    public void start(Stage stage) {
        TextArea output = new TextArea();
        output.setEditable(false);

        Button writeButton = new Button("Generate & Save Data");

        // Lambda expression
        writeButton.setOnAction(e -> {
            Random rand = new Random();

            int[] intArray = new int[5];
            double[] doubleArray = new double[5];

            StringBuilder data = new StringBuilder();

            data.append("Integers: ");
            for (int i = 0; i < 5; i++) {
                intArray[i] = rand.nextInt(100);
                data.append(intArray[i]).append(" ");
            }

            data.append("\nDoubles: ");
            for (int i = 0; i < 5; i++) {
                doubleArray[i] = rand.nextDouble() * 100;
                data.append(String.format("%.2f ", doubleArray[i]));
            }

            data.append("\n----------------------\n");

            // Write to file (append mode = true)
            try (FileWriter writer = new FileWriter("JoseVelazquez_datafile.dat", true)) {
                writer.write(data.toString());
                output.appendText("Data saved to file.\n\n" + data.toString());
            } catch (IOException ex) {
                output.appendText("Error writing file.\n");
            }
        });

        VBox root = new VBox(10, writeButton, output);
        root.setAlignment(Pos.CENTER);

        Scene scene = new Scene(root, 400, 300);
        stage.setTitle("Write Data Program");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}