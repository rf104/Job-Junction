package com.example.dtl;

import javafx.animation.RotateTransition;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {


    @FXML
    private Circle c1;

    @FXML
    private Circle c2;

    @FXML
    private Circle c3;

    @FXML
    private ProgressBar prog_bar;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setrotate(c1,true,270,10);
        setrotate(c2,true,180,18);
        setrotate(c3,true,360,24);

        Task<Void> task = new Task<>() {
            @Override
            protected Void call() throws Exception {
                for (int i = 0; i <= 100; i+=2) {
                    updateProgress(i, 100);
                    Thread.sleep(50);
                }
                return null;
            }
        };

        prog_bar.progressProperty().bind(task.progressProperty());

        task.setOnSucceeded(event -> {
            loadNewScene();

        });

        new Thread(task).start();
    }

    private void loadNewScene() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Dashboard.fxml"));
            Parent newRoot = loader.load();
            Stage stage = (Stage) prog_bar.getScene().getWindow();
            stage.setScene(new Scene(newRoot));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void setrotate(Circle c, boolean reverse, int angle, int duration)
        {
            RotateTransition rt = new RotateTransition(Duration.seconds(duration),c);
            rt.setAutoReverse(reverse);
            rt.setByAngle(angle);
            rt.setDelay(Duration.seconds(0));
            rt.setRate(3);
            rt.setCycleCount(18);
            rt.play();
        }
}