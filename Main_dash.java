package com.example.dtl;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;


import java.awt.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

public class Main_dash implements Initializable {
    @FXML
    private ImageView j1;

    @FXML
    private ImageView news;

    @FXML
    private ImageView j2;

    @FXML
    private ImageView j3;

    @FXML
    private ImageView j4;

    @FXML
    private ImageView j5;

    @FXML
    private ImageView j6;

    @FXML
    private ImageView j7;

    @FXML
    private ImageView j8;

    @FXML
    private ImageView j9;

    @FXML
    private StackPane stackpane;


    private Image[] images;
    private int currentIndex = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FadeTransition fade1 = new FadeTransition();
        fade1.setNode(news);
        fade1.setDuration(Duration.seconds(4));
        fade1.setCycleCount(TranslateTransition.INDEFINITE);
        fade1.setInterpolator(Interpolator.LINEAR);
        fade1.setFromValue(0);
        fade1.setToValue(1);
        fade1.play();
        images = new Image[]{
                new Image(getClass().getResourceAsStream("job1.jpg")),
                new Image(getClass().getResourceAsStream("job2.jpg")),
                new Image(getClass().getResourceAsStream("job3.jpg")),
                new Image(getClass().getResourceAsStream("job4.jpg")),
                new Image(getClass().getResourceAsStream("job5.jpg")),
                new Image(getClass().getResourceAsStream("job6.jpg")),
                new Image(getClass().getResourceAsStream("job7.jpg")),
                new Image(getClass().getResourceAsStream("job8.jpg")),
                new Image(getClass().getResourceAsStream("job9.jpg"))
        };

        j1.setImage(images[0]);
        j2.setImage(images[1]);
        j3.setImage(images[2]);
        j4.setImage(images[3]);
        j5.setImage(images[4]);
        j6.setImage(images[5]);
        j7.setImage(images[6]);
        j8.setImage(images[7]);
        j9.setImage(images[8]);

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(4), this::nextImage));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
    private void nextImage(ActionEvent event) {
        currentIndex = (currentIndex + 1) % images.length;
        ImageView currentImageView = getCurrentImageView();

        // Create a clip to constrain the transition within the StackPane
        Rectangle clip = new Rectangle(stackpane.getWidth(), stackpane.getHeight());
        stackpane.setClip(clip);

        stackpane.getChildren().remove(currentImageView);
        currentImageView.setImage(images[currentIndex]);

        TranslateTransition transition = new TranslateTransition(Duration.seconds(1), currentImageView);
        transition.setFromX(stackpane.getWidth());
        transition.setToX(0);

        // Reset clip after the transition
        transition.setOnFinished(e -> stackpane.setClip(null));

        transition.play();
        stackpane.getChildren().add(currentImageView);
    }

    private ImageView getCurrentImageView() {
        switch (currentIndex) {
            case 0:
                return j2;
            case 1:
                return j3;
            case 2:
                return j4;
            case 3:
                return j5;
            case 4:
                return j6;
            case 5:
                return j7;
            case 6:
                return j8;
            case 7:
                return j9;
            case 8:
                return j1;
            default:
                return null;
        }
    }
    @FXML
    void Art1(ActionEvent event) throws URISyntaxException, IOException {
        Desktop.getDesktop().browse(new URI("https://www.businessnewsdaily.com/75-autonomy-keeps-employees-happy-study-finds.html"));
    }

    @FXML
    void Art2(ActionEvent event) throws URISyntaxException , IOException{
        Desktop.getDesktop().browse(new URI("https://hbswk.hbs.edu/item/kids-of-working-moms-grow-into-happy-adults"));
    }

    @FXML
    void Art3(ActionEvent event) throws URISyntaxException , IOException{
        Desktop.getDesktop().browse(new URI("https://www.businessnewsdaily.com/2955-best-pos-systems.html"));
    }

    @FXML
    void Art4(ActionEvent event) throws URISyntaxException , IOException{
        Desktop.getDesktop().browse(new URI("https://hbswk.hbs.edu/item/the-portfolio-life-how-to-future-proof-your-career-avoid-burnout-and-build-a-life-bigger-than-your-business-card"));
    }

    @FXML
    void Art5(ActionEvent event) throws URISyntaxException , IOException{
        Desktop.getDesktop().browse(new URI("https://www.tribeandseek.com/blog/job-ideas"));
    }

    @FXML
    void Art6(ActionEvent event) throws URISyntaxException , IOException{
        Desktop.getDesktop().browse(new URI("https://www.businessnewsdaily.com/security/ransomware"));
    }

    @FXML
    void calculator(ActionEvent event) {
        try {
            Class<?> mainClass = Class.forName("com.example.final_loader_mist.hello_calculator"); // Replace with your main class name
            Method startMethod = mainClass.getMethod("start", Stage.class);
            Stage stage = new Stage();
            startMethod.invoke(mainClass.getDeclaredConstructor().newInstance(), stage);
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            // currentStage.close();
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            e.printStackTrace();
        }
    }
}
