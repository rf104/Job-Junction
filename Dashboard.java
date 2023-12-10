package com.example.dtl;
//import com.example.final_loader_mist.Update_profile;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.control.Label;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;

public class Dashboard implements Initializable {


    @FXML
    private ImageView eagle_pic;

    @FXML
    private Label l1;

    @FXML
    private ImageView my_pic;
    @FXML
    private Button jn;

    @FXML
    private Button home;

    @FXML
    private Button jo;

    @FXML
    private Button up;

    @FXML
    private Button am;

    @FXML
    private Button fa;

    @FXML
    private StackPane stack_pane;

    @FXML
    private ImageView am_image1;

    @FXML
    private ImageView am_image2;

    @FXML
    private ImageView faq_image1;

    @FXML
    private ImageView faq_image2;

    @FXML
    private ImageView jo_image1;

    @FXML
    private ImageView jo_image2;

    @FXML
    private ImageView up_image1;

    @FXML
    private ImageView up_image2;

    public int c=1;
    @FXML
    private ImageView jn_image1;

    @FXML
    private ImageView jn_image2;

    @FXML
    public ChoiceBox<String> choiceBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //Update_profile updateProfile = new Update_profile();
        File Scroll = new File("E:\\Job_junction\\News\\Department");
        if (Scroll.exists() && Scroll.isDirectory()) {
            File[] subdirectories = Scroll.listFiles(File::isDirectory);
            List<String> folderNames = new ArrayList<>();
            for (File subdirectory : subdirectories) {
                folderNames.add(subdirectory.getName());
            }
            folderNames = new ArrayList<>(new HashSet<>(folderNames));
            choiceBox.getItems().addAll(folderNames);
        }

        String folderPath = "E:\\Job_junction\\Employee\\Ibrahim_reza\\profile_image";

        File folder = new File(folderPath);

        if (folder.exists() && folder.isDirectory()) {
            File[] files = folder.listFiles();

            if (files != null && files.length > 0) {
                // Select the first file found in the folder
                File selectedFile = files[0];

                // Convert File to Image
                try {
                    Image image = new Image(selectedFile.toURI().toURL().toExternalForm());
                    my_pic.setImage(image);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
        }

        FadeTransition fade = new FadeTransition();
        fade.setNode(eagle_pic);
        fade.setDuration(Duration.seconds(4));
        fade.setCycleCount(TranslateTransition.INDEFINITE);
        fade.setInterpolator(Interpolator.LINEAR);
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.play();
        FadeTransition fade2 = new FadeTransition();
        fade2.setNode(my_pic);
        fade2.setDuration(Duration.seconds(4));
        fade2.setCycleCount(TranslateTransition.INDEFINITE);
        fade2.setInterpolator(Interpolator.LINEAR);
        fade2.setFromValue(0);
        fade2.setToValue(1);
        fade2.play();
        ScaleTransition scale = new ScaleTransition();
        scale.setNode(l1);
        scale.setDuration(Duration.seconds(4));
        scale.setCycleCount(TranslateTransition.INDEFINITE);
        scale.setInterpolator(Interpolator.LINEAR);
        scale.setByX(0.2);
        scale.setByY(0.2);
        scale.setAutoReverse(true);
        scale.play();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Main_dash.fxml"));
            ScrollPane root = loader.load();
            stack_pane.getChildren().add(root);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @FXML
    void switch_jn(MouseEvent event) {
        jn_image1.setVisible(false);
        jn_image2.setVisible(true);
    }
    @FXML
    void off_switch_jn(MouseEvent event) {
        jn_image1.setVisible(true);
        jn_image2.setVisible(false);
    }
    @FXML
    void switch_jo(MouseEvent event) {
        jo_image1.setVisible(false);
        jo_image2.setVisible(true);
    }
    @FXML
    void off_switch_jo(MouseEvent event) {
        jo_image1.setVisible(true);
        jo_image2.setVisible(false);
    }
    @FXML
    void switch_am(MouseEvent event) {
        am_image1.setVisible(false);
        am_image2.setVisible(true);
    }
    @FXML
    void off_switch_am(MouseEvent event) {
        am_image1.setVisible(true);
        am_image2.setVisible(false);
    }
    @FXML
    void switch_up(MouseEvent event) {
        up_image1.setVisible(false);
        up_image2.setVisible(true);
    }
    @FXML
    void off_switch_up(MouseEvent event) {
        up_image1.setVisible(true);
        up_image2.setVisible(false);
    }
    @FXML
    void switch_faq(MouseEvent event) {
        faq_image1.setVisible(false);
        faq_image2.setVisible(true);
    }
    @FXML
    void off_switch_faq(MouseEvent event) {
        faq_image1.setVisible(true);
        faq_image2.setVisible(false);
    }

    @FXML
    private void go_choice(ActionEvent event)
    {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Search_job.fxml"));
            Parent root = loader.load();

            Stage newStage = new Stage();
            newStage.setTitle("Searched");
            newStage.setScene(new Scene(root));

            FadeTransition fade = new FadeTransition(Duration.seconds(3), root);
            fade.setFromValue(0);
            fade.setToValue(1);
            fade.play();

            newStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void job_news(ActionEvent event) throws IOException {
            int numChildren = stack_pane.getChildren().size();
            if (numChildren > 0) {
                Node frontNode = stack_pane.getChildren().get(numChildren - 1);
                stack_pane.getChildren().remove(frontNode);
            }
            Parent root = FXMLLoader.load(getClass().getResource("Job_news.fxml"));
            Scene scene = jn.getScene();

            root.translateYProperty().set(scene.getHeight());
            stack_pane.getChildren().add(root);

            Timeline timeline = new Timeline();
            KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
            KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
            timeline.getKeyFrames().add(kf);
            timeline.play();
        }



    @FXML
    void job_offers(ActionEvent event) {
        int numChildren = stack_pane.getChildren().size();
        if (numChildren > 0) {
            Node frontNode = stack_pane.getChildren().get(numChildren - 1);
            stack_pane.getChildren().remove(frontNode);
        }
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("Job_offers.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = jo.getScene();

        root.translateXProperty().set(scene.getHeight());
        stack_pane.getChildren().add(root);

        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(root.translateXProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
        timeline.getKeyFrames().add(kf);
        timeline.play();
    }

    @FXML
    public String get_choice()
    {
        return choiceBox.getValue();
    }

    @FXML
    void update_profile(ActionEvent event) {
        int numChildren = stack_pane.getChildren().size();
        if (numChildren > 0) {
            Node frontNode = stack_pane.getChildren().get(numChildren - 1);
            stack_pane.getChildren().remove(frontNode);
        }
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("Update_profile.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = up.getScene();

        root.translateYProperty().set(-scene.getHeight());

        stack_pane.getChildren().add(root);

        FadeTransition fade = new FadeTransition(Duration.seconds(6), root); // Set the duration here
        fade.setFromValue(0);
        fade.setToValue(1);

        TranslateTransition slideIn = new TranslateTransition(Duration.seconds(1), root);
        slideIn.setFromY(scene.getHeight());
        slideIn.setToY(0);

        // Parallel transition for simultaneous fade-in and slide-in
        ParallelTransition parallelTransition = new ParallelTransition(root, fade, slideIn);
        parallelTransition.play();
    }
    @FXML
    void about_me(ActionEvent event) {
        int numChildren = stack_pane.getChildren().size();
        if (numChildren > 0) {
            Node frontNode = stack_pane.getChildren().get(numChildren - 1);
            stack_pane.getChildren().remove(frontNode);
        }
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("About_me.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = am.getScene();

        root.translateYProperty().set(-scene.getHeight());
        stack_pane.getChildren().add(root);

        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
        timeline.getKeyFrames().add(kf);
        timeline.play();
    }

    @FXML
    void send_email(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Send_email.fxml"));
            Parent root = loader.load();

            Stage newStage = new Stage();
            newStage.setTitle("Send Email");
            newStage.setScene(new Scene(root));

            FadeTransition fade = new FadeTransition(Duration.seconds(3), root);
            fade.setFromValue(0);
            fade.setToValue(1);
            fade.play();

            newStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    @FXML
    void go_home(ActionEvent event) throws IOException {
        int numChildren = stack_pane.getChildren().size();
        if (numChildren > 0) {
            Node frontNode = stack_pane.getChildren().get(numChildren - 1);
            stack_pane.getChildren().remove(frontNode);
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Main_dash.fxml"));
        ScrollPane pane = loader.load();
        stack_pane.getChildren().add(stack_pane);
//        Parent root = null;
//        try {
//            root = FXMLLoader.load(getClass().getResource("Main_dash.fxml"));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        Scene scene = new Scene(root);
//
//
////        root.translateXProperty().set(-scene.getHeight());
////        stack_pane.getChildren().add(root);
////
////        Rectangle clip = new Rectangle(stack_pane.getWidth(), stack_pane.getHeight());
////        stack_pane.setClip(clip);
////
////        Timeline timeline = new Timeline();
////        KeyValue kv = new KeyValue(root.translateXProperty(), 0, Interpolator.EASE_IN);
////        KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
////        timeline.getKeyFrames().add(kf);
////        timeline.play();

    }
}



