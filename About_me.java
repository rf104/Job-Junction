package com.example.dtl;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.io.BufferedReader;
import java.io.File;
import java.awt.Desktop;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class About_me implements Initializable {

    @FXML
    private Label a_address , a_mail , a_name , a_phone;

    @FXML
    private TextArea a_brief , a_edu , a_skill , a_work ;

    @FXML
    private GridPane gridPane;
    @FXML
    private final String imagesDirectory = "E:\\Job_junction\\Employee\\Ibrahim_reza\\Normal_image";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Specify the file path
        String filePath = "E:\\Job_junction\\Employee\\Ibrahim_reza\\CSV\\info.csv";

        // Check if the file exists
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("CSV file does not exist.");
            return;
        }

        // Read the CSV file and store cell values in a list of arrays
        List<String[]> data = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            // Read the header line (skip if you don't want to include headers in the data)
            // String headerLine = reader.readLine();

            // Read data rows
            String line;
            while ((line = reader.readLine()) != null) {
                String[] cells = line.split(",");
                data.add(cells);
            }

            System.out.println("CSV file read successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error reading CSV file.");
        }
        if (!data.isEmpty()) {
            // Access the first row of data
            String[] firstRow = data.get(0);

            // Set values in the text fields
            a_name.setText(firstRow[0]);
            a_mail.setText(firstRow[1]);
            a_phone.setText(firstRow[2]);
            a_address.setText(firstRow[3]);
        }
        String path2 = "E:\\Job_junction\\Employee\\Ibrahim_reza\\CSV\\brief.txt";
        String path3 = "E:\\Job_junction\\Employee\\Ibrahim_reza\\CSV\\skills.txt";
        String path4 = "E:\\Job_junction\\Employee\\Ibrahim_reza\\CSV\\edu_info.txt";
        String path5 = "E:\\Job_junction\\Employee\\Ibrahim_reza\\CSV\\work_exp.txt";
        place_text(path2, a_brief);
        place_text(path3, a_skill);
        place_text(path4, a_edu);
        place_text(path5, a_work);

        File directory = new File(imagesDirectory);

        if (directory.isDirectory()) {
            File[] imageFiles = directory.listFiles(
                    (dir, name) -> name.toLowerCase().endsWith(".jpg") ||
                            name.toLowerCase().endsWith(".png") ||
                            name.toLowerCase().endsWith(".jpeg") ||
                            name.toLowerCase().endsWith(".gif"));

            if (imageFiles != null) {
                int row = 0;
                int col = 0;

                for (File imageFile : imageFiles) {
                    String imagePath = imageFile.toURI().toString();
                    ImageView imageView = createImageView(imagePath);

                    // Add ImageView to the GridPane
                    gridPane.add(imageView, col, row);

                    // Adjust row and column indices
                    col++;
                    if (col == 3 && row < 2) {
                        col = 0;
                        row++;
                    }
                }
            }
        }

    }
    private ImageView createImageView(String imagePath) {
        Image image = new Image(imagePath);

        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(320);
        imageView.setFitHeight(150);
        imageView.setPreserveRatio(false);
        return imageView;
    }

    private void place_text(String filePath,TextArea textArea)
    {
        // Check if the file exists
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("Text file does not exist.");
            return;
        }

        // Read the text file and set its content to the TextArea
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            StringBuilder content = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }

            textArea.setText(content.toString());
            System.out.println("Text file read successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            textArea.setText("Error reading text file.");
        }
    }

    @FXML
    void show1(ActionEvent event) {
        String path = "E:\\Job_junction\\Employee\\Ibrahim_reza\\CV";
        show(path);
    }

    @FXML
    void show2(ActionEvent event) {
        String path = "E:\\Job_junction\\Employee\\Ibrahim_reza\\Certificates";
        show(path);
    }

    private void show(String folderPath) {
        File folder = new File(folderPath);

        if (folder.exists() && folder.isDirectory()) {
            File[] files = folder.listFiles();

            if (files != null) {
                for (File file : files) {
                    openFile(file);
                }
            }
        }
    }

    private void openFile(File file) {
        if (Desktop.isDesktopSupported()) {
            try {
                Desktop.getDesktop().open(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
