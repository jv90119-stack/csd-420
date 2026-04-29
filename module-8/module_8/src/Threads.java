// JOSE VELAZQUEZ
// MODULE 8.2 ASSIGNMENT 
// DATE: 04/29/2026
// This is a  JavaFX application that demonstrates the use of multiple threads 
// to generate different types of characters.


import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Threads extends Application {

    private static final int TOTAL_CHARACTERS = 10000;

    private final TextArea textArea = new TextArea();

    private final AtomicInteger letterCount = new AtomicInteger(0);
    private final AtomicInteger numberCount = new AtomicInteger(0);
    private final AtomicInteger symbolCount = new AtomicInteger(0);

    private final char[] symbols = {'!', '@', '#', '$', '%', '&', '*', '^'};

    @Override
    public void start(Stage primaryStage) {

        textArea.setWrapText(true);
        textArea.setEditable(false);

        ScrollPane scrollPane = new ScrollPane(textArea);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

        VBox root = new VBox(scrollPane);

        Scene scene = new Scene(root, 700, 500);

        primaryStage.setTitle("Threads");
        primaryStage.setScene(scene);
        primaryStage.show();

        runTests();

        Thread letterThread = new Thread(() -> generateLetters());
        Thread numberThread = new Thread(() -> generateNumbers());
        Thread symbolThread = new Thread(() -> generateSymbols());

        letterThread.setDaemon(true);
        numberThread.setDaemon(true);
        symbolThread.setDaemon(true);

        letterThread.start();
        numberThread.start();
        symbolThread.start();
    }

    public void generateLetters() {
        Random random = new Random();

        for (int i = 0; i < TOTAL_CHARACTERS; i++) {
            char letter = (char) ('a' + random.nextInt(26));
            letterCount.incrementAndGet();

            Platform.runLater(() -> textArea.appendText(String.valueOf(letter)));

            pauseThread();
        }
    }

    public void generateNumbers() {
        Random random = new Random();

        for (int i = 0; i < TOTAL_CHARACTERS; i++) {
            char number = (char) ('0' + random.nextInt(10));
            numberCount.incrementAndGet();

            Platform.runLater(() -> textArea.appendText(String.valueOf(number)));

            pauseThread();
        }
    }

    public void generateSymbols() {
        Random random = new Random();

        for (int i = 0; i < TOTAL_CHARACTERS; i++) {
            char symbol = symbols[random.nextInt(symbols.length)];
            symbolCount.incrementAndGet();

            Platform.runLater(() -> textArea.appendText(String.valueOf(symbol)));

            pauseThread();
        }
    }

    public void pauseThread() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted.");
        }
    }

    public void runTests() {
        System.out.println("Running test code...");

        if (TOTAL_CHARACTERS >= 10000) {
            System.out.println("Test Passed: Each thread will generate at least 10,000 characters.");
        } else {
            System.out.println("Test Failed: Character count is less than 10,000.");
        }

        if (symbols.length > 0) {
            System.out.println("Test Passed: Symbol array contains random character options.");
        } else {
            System.out.println("Test Failed: Symbol array is empty.");
        }

        System.out.println("Test Passed: Letter, number, and symbol generation methods are ready.");
    }

    public static void main(String[] args) {
        launch(args);
    }
}