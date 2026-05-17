// JOSE VELAZQUEZ
// MODULE 10.2 ASSIGNMENT 
// DATE: 05/17/2026
// This JavaFX application connects to a MySQL database to display and update fan records. 
// It includes error handling and test code to verify database connectivity and interface 
// functionality. The application uses prepared statements to prevent SQL injection and 
// provides user feedback through a message area.

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.*;

public class FanDatabaseApp extends Application {

    // Database connection information
    private static final String DB_URL = "jdbc:mysql://localhost:3306/databasedb";
    private static final String DB_USER = "student1";
    private static final String DB_PASSWORD = "pass";

    // TextFields for user input and display
    private TextField idField;
    private TextField firstNameField;
    private TextField lastNameField;
    private TextField favoriteTeamField;
    private TextArea messageArea;

    @Override
    public void start(Stage primaryStage) {

        Label idLabel = new Label("ID:");
        Label firstNameLabel = new Label("First Name:");
        Label lastNameLabel = new Label("Last Name:");
        Label favoriteTeamLabel = new Label("Favorite Team:");

        idField = new TextField();
        firstNameField = new TextField();
        lastNameField = new TextField();
        favoriteTeamField = new TextField();

        Button displayButton = new Button("Display");
        Button updateButton = new Button("Update");

        messageArea = new TextArea();
        messageArea.setEditable(false);
        messageArea.setPrefHeight(100);

        // Lambda expressions for button actions
        displayButton.setOnAction(e -> displayFanRecord());
        updateButton.setOnAction(e -> updateFanRecord());

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(idLabel, 0, 0);
        gridPane.add(idField, 1, 0);

        gridPane.add(firstNameLabel, 0, 1);
        gridPane.add(firstNameField, 1, 1);

        gridPane.add(lastNameLabel, 0, 2);
        gridPane.add(lastNameField, 1, 2);

        gridPane.add(favoriteTeamLabel, 0, 3);
        gridPane.add(favoriteTeamField, 1, 3);

        gridPane.add(displayButton, 0, 4);
        gridPane.add(updateButton, 1, 4);

        gridPane.add(messageArea, 0, 5, 2, 1);

        Scene scene = new Scene(gridPane, 450, 350);

        primaryStage.setTitle("Fan Database App");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Test code
        runTests();
    }

    private Connection getConnection() throws SQLException {    
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found.");
        }
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    private void displayFanRecord() {
        try {
            int id = Integer.parseInt(idField.getText());

            String sql = "SELECT * FROM fans WHERE ID = ?";

            try (
                    Connection connection = getConnection();
                    PreparedStatement statement = connection.prepareStatement(sql)
            ) {
                statement.setInt(1, id);

                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    firstNameField.setText(resultSet.getString("firstname"));
                    lastNameField.setText(resultSet.getString("lastname"));
                    favoriteTeamField.setText(resultSet.getString("favoriteteam"));

                    messageArea.setText("Record found and displayed.");
                } else {
                    clearFieldsExceptId();
                    messageArea.setText("No record found with ID: " + id);
                }
            }

        } catch (NumberFormatException e) {
            messageArea.setText("Please enter a valid numeric ID.");
        } catch (SQLException e) {
            messageArea.setText("Database error while displaying record:\n" + e.getMessage());
        }
    }

    private void updateFanRecord() {
        try {
            int id = Integer.parseInt(idField.getText());

            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String favoriteTeam = favoriteTeamField.getText();

            if (firstName.isEmpty() || lastName.isEmpty() || favoriteTeam.isEmpty()) {
                messageArea.setText("Please fill in all fields before updating.");
                return;
            }

            String sql = "UPDATE fans SET firstname = ?, lastname = ?, favoriteteam = ? WHERE ID = ?";

            try (
                    Connection connection = getConnection();
                    PreparedStatement statement = connection.prepareStatement(sql)
            ) {
                statement.setString(1, firstName);
                statement.setString(2, lastName);
                statement.setString(3, favoriteTeam);
                statement.setInt(4, id);

                int rowsUpdated = statement.executeUpdate();

                if (rowsUpdated > 0) {
                    messageArea.setText("Record updated successfully.");
                } else {
                    messageArea.setText("No record found to update with ID: " + id);
                }
            }

        } catch (NumberFormatException e) {
            messageArea.setText("Please enter a valid numeric ID.");
        } catch (SQLException e) {
            messageArea.setText("Database error while updating record:\n" + e.getMessage());
        }
    }

    private void clearFieldsExceptId() {
        firstNameField.clear();
        lastNameField.clear();
        favoriteTeamField.clear();
    }

    private void runTests() {
        System.out.println("Running test code...");

        // Test database URL
        if (DB_URL.contains("databasedb")) {
            System.out.println("Test Passed: Database URL references databasedb.");
        } else {
            System.out.println("Test Failed: Database URL is incorrect.");
        }

        // Test username
        if (DB_USER.equals("student1")) {
            System.out.println("Test Passed: Database username is student1.");
        } else {
            System.out.println("Test Failed: Database username is incorrect.");
        }

        // Test password
        if (DB_PASSWORD.equals("pass")) {
            System.out.println("Test Passed: Database password is pass.");
        } else {
            System.out.println("Test Failed: Database password is incorrect.");
        }

        // Test database connection
        try (Connection connection = getConnection()) {
            if (connection != null && !connection.isClosed()) {
                System.out.println("Test Passed: Database connection successful.");
            }
        } catch (SQLException e) {
            System.out.println("Test Warning: Could not connect to database.");
            System.out.println("Reason: " + e.getMessage());
        }

        // Test interface fields
        if (idField != null && firstNameField != null && lastNameField != null && favoriteTeamField != null) {
            System.out.println("Test Passed: Interface text fields created.");
        } else {
            System.out.println("Test Failed: One or more interface text fields are missing.");
        }

        System.out.println("Test code completed.");
    }

    public static void main(String[] args) {
        launch(args);
    }
}