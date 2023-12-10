package com.example.dtl;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.*;
import java.util.Optional;

public class Employer_work {
    @FXML
    private TextField em_catagory,em_dignity,em_hardness,em_name,em_title,em_workplace,em_mail;

    @FXML
    private TextArea em_info;
    @FXML
    void send_the_post(ActionEvent event) {
        String path1 = "E:\\Job_junction\\News\\All_news";
        String path2 = "E:\\Job_junction\\News\\Department" + em_catagory.getText();
        String path3 = "E:\\Job_junction\\News\\Hard";
        String path4 = "E:\\Job_junction\\News\\Medium";

        try {
            main_send_function(path1);
            main_send_function(path2);
            if ("Hard".equals(em_hardness.getText())) {
                main_send_function(path3);
            } else {
                main_send_function(path4);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void main_send_function(String filepath) throws IOException {
        File file = new File(filepath);

        if (!file.exists()) {
            file.mkdirs();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write("Job Title: " + em_title.getText() + "\n\n");
            writer.write("Job Details: " + em_info.getText() + "\n\n");
            writer.write("People are welcomed from " + em_catagory.getText() + "\n\n");
            writer.write("Remember, the job is " + em_hardness.getText() + "\n");
            writer.write("Regards, " + em_name.getText() + "\n");
            writer.write(em_dignity.getText() + "\n");
            writer.write(em_mail.getText() + "\n");
            writer.write(em_workplace.getText() + "\n");

        } catch (IOException e) {
            //throw new RuntimeException(e);
            e.printStackTrace();
        }
    }
    @FXML
    private Button logout;
    public void LOGOUT1(ActionEvent e){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Message");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to logout?");
        Optional<ButtonType> option = alert.showAndWait();
        try {
            if (option.get().equals(ButtonType.OK)) {

                logout.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("Intro.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.initStyle(StageStyle.TRANSPARENT);

                stage.setScene(scene);
                stage.show();
            }
        } catch (Exception ee) {
            ee.printStackTrace();
        }

    }

}
