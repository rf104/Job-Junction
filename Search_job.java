package com.example.dtl;

//import com.example.final_loader_mist.Dashboard.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Search_job implements Initializable {

    @FXML
    private Label s_job;

    @FXML
    private VBox vbox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Dashboard choicebox = new Dashboard();
        String path = "E:\\Job_junction\\News\\Department\\" + choicebox.choiceBox.getValue();
        pass_path(path);
    }

    private void pass_path(String folderPath) {
        // Load files from the folder
        File folder = new File(folderPath);
        File[] files = folder.listFiles();

        // Display text content and a button for each file in the VBox
        if (files != null) {
            for (File file : files) {
                String fileName = file.getName();
                String fileContent = readFileContent(file);
                Label label = new Label(fileContent);
                label.setStyle("-fx-font-size: 20px;");
                Button apply = new Button("Apply");
                apply.setStyle("-fx-font-size: 14px;"); // Increased font size for better visibility
                apply.setPrefHeight(60);
                apply.setPrefWidth(300);

                // Create an AnchorPane and add label and button to it
                AnchorPane anchor = new AnchorPane();
                anchor.getChildren().addAll(label, apply);

                // Generate a random color
                Color randomColor = Color.rgb((int) (Math.random() * 256),
                        (int) (Math.random() * 256),
                        (int) (Math.random() * 256));

                // Set the random color as the background color for the AnchorPane
                String backgroundColor = String.format("-fx-background-color: rgba(%d, %d, %d, 1);",
                        (int) (randomColor.getRed() * 255),
                        (int) (randomColor.getGreen() * 255),
                        (int) (randomColor.getBlue() * 255));
                anchor.setStyle(backgroundColor + "-fx-background-radius: 10;");

                // Add the AnchorPane to the VBox
                vbox.getChildren().add(anchor);
            }
        }

        // Add a listener to VBox height property to update the size
        vbox.heightProperty().addListener((observable, oldValue, newValue) ->
                vbox.setPrefHeight(newValue.doubleValue()));
    }

    private String readFileContent(File file) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            int character;
            while ((character = reader.read()) != -1) {
                content.append((char) character);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }
}
