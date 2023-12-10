package com.example.dtl;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Update_profile implements Initializable {
    @FXML
    private TextArea edu_back , info , skill , work;

    @FXML
    private TextField location , mail , name , phone;

    @FXML
    private Button save_button , no_button , yes_button;

    @FXML
    private Label sure;

    @FXML
    private ImageView imageContainer;

    @FXML
    private VBox Container;
    private final String savePath = "E:\\Job_junction\\Employee\\Ibrahim_reza\\profile_image";

    public File uploadedFile; // Use File for a single file

    private final String savePaths = "E:\\Job_junction\\Employee\\Ibrahim_reza\\Normal_image";

    private List<File> uploadedFiles ;

    private final List<File> second_uploaded_Files = new ArrayList<>();



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Allow the VBox to accept dragged files
        imageContainer.setOnDragOver(event -> {
            if (event.getGestureSource() != imageContainer &&
                    event.getDragboard().hasFiles()) {
                event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
            }
            event.consume();
        });

        // Handle the dropping of files
        imageContainer.setOnDragDropped(event -> {
            Dragboard dragboard = event.getDragboard();
            boolean success = false;
            if (dragboard.hasFiles()) {
                success = true;
                File file = dragboard.getFiles().get(0); // Assuming only one file is dropped
                setAndDisplayImage(file);
            }
            event.setDropCompleted(success);
            event.consume();
        });
        Container.setOnDragOver(event -> {
            if (event.getGestureSource() != Container &&
                    event.getDragboard().hasFiles()) {
                event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
            }
            event.consume();
        });

        // Handle the dropping of files
        Container.setOnDragDropped(event -> {
            Dragboard dragboard = event.getDragboard();
            boolean success = false;
            if (dragboard.hasFiles()) {
                success = true;
                List<File> files = dragboard.getFiles();
                displayImages(files);
                second_uploaded_Files.addAll(files);
            }
            event.setDropCompleted(success);
            event.consume();
        });
    }

    @FXML
    public void uploadImages(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image Files");
        List<File> selectedFiles = fileChooser.showOpenMultipleDialog(null);

        if (selectedFiles != null) {
            displayImages(selectedFiles);
            second_uploaded_Files.addAll(selectedFiles);
        }
    }
    @FXML
    public void saveImages(ActionEvent actionEvent) {
        for (File file : second_uploaded_Files) {
            try {
                // Construct the destination path
                String destinationPath = savePaths + File.separator + file.getName();

                // Copy the file to the specified savePath
                Files.copy(file.toPath(), new File(destinationPath).toPath(), StandardCopyOption.REPLACE_EXISTING);

                System.out.println("Saving: " + destinationPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void displayImages(List<File> files) {
        for (File file : files) {
            ImageView imageView = createImageView(file);
            Container.getChildren().add(imageView);
        }
    }

    private ImageView createImageView(File file) {
        ImageView imageView = new ImageView(new Image(file.toURI().toString()));
        imageView.setFitWidth(250);
        imageView.setFitHeight(200);
        return imageView;
    }
    @FXML
    public void uploadImage(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image File");
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            setAndDisplayImage(selectedFile);
        }
    }

    private void setAndDisplayImage(File file) {
        imageContainer.setImage(new Image(file.toURI().toString()));
        uploadedFile = file;
    }

    @FXML
    void saveImage(ActionEvent event) {
        try {
            // Construct the destination path
            String destinationFolderPath = savePath;
            File destinationFolder = new File(destinationFolderPath);

            // Delete all existing files in the destination folder
            if (destinationFolder.exists() && destinationFolder.isDirectory()) {
                File[] existingFiles = destinationFolder.listFiles();
                if (existingFiles != null) {
                    for (File file : existingFiles) {
                        boolean deleted = file.delete();
                        if (!deleted) {
                            System.err.println("Failed to delete existing file: " + file.getAbsolutePath());
                        }
                    }
                }
            }

            // Copy the new file to the specified savePath
            if (uploadedFile != null) {
                String destinationPath = destinationFolderPath + File.separator + uploadedFile.getName();
                Files.copy(uploadedFile.toPath(), new File(destinationPath).toPath(), StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Saving: " + destinationPath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void upload() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Files");
        uploadedFiles = fileChooser.showOpenMultipleDialog(null);
    }

    @FXML
    public void save1(ActionEvent event) {
        String path = "E:\\Job_junction\\Employee\\Ibrahim_reza\\CV";
        save(path);
    }

    @FXML
    public void save2(ActionEvent event) {
        String path = "E:\\Job_junction\\Employee\\Ibrahim_reza\\Certificates";
        save(path);
    }

    private void save(String savePath) {
        try {
            if (uploadedFiles != null && !uploadedFiles.isEmpty()) {
                for (File uploadedFile : uploadedFiles) {
                    String destinationPath = savePath + File.separator + uploadedFile.getName();
                    Files.copy(uploadedFile.toPath(), new File(destinationPath).toPath(), StandardCopyOption.REPLACE_EXISTING);
                    System.out.println("Saving: " + destinationPath);
                }
            } else {
                System.out.println("No files selected for saving.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void info_save(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
        }
        try (FileWriter writer = new FileWriter(filePath)) {
            // writer.append("Column1,Column2,Column3\n");

            // Write data rows
            String rowData = String.format("%s,%s,%s,%s\n", name.getText(), mail.getText(), phone.getText(), location.getText());
            writer.append(rowData);

            System.out.println("CSV file created successfully.");

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error creating CSV file.");
        }
    }

    private void brief_save(String filePath,TextArea name)
    {
        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
        }
        String content = name.getText();
        if (content.trim().isEmpty()) {
            // Don't save empty content
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void saveToCSV(ActionEvent event)
    {
        String path1 = "E:\\Job_junction\\Employee\\Ibrahim_reza\\CSV\\info.csv";
        String path2 = "E:\\Job_junction\\Employee\\Ibrahim_reza\\CSV\\brief.txt";
        String path3 = "E:\\Job_junction\\Employee\\Ibrahim_reza\\CSV\\skills.txt";
        String path4 = "E:\\Job_junction\\Employee\\Ibrahim_reza\\CSV\\edu_info.txt";
        String path5 = "E:\\Job_junction\\Employee\\Ibrahim_reza\\CSV\\work_exp.txt";

        info_save(path1);
        brief_save(path2,info);
        brief_save(path3,skill);
        brief_save(path4,edu_back);
        brief_save(path5,work);
    }

    @FXML
    void dont_save(ActionEvent event) {
        save_button.setVisible(true);
        sure.setVisible(false);
        no_button.setVisible(false);
        yes_button.setVisible(false);
    }

    @FXML
    void are_you_sure(MouseEvent event) {
        save_button.setVisible(false);
        sure.setVisible(true);
        no_button.setVisible(true);
        yes_button.setVisible(true);
    }
}
