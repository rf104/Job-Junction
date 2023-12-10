package com.example.dtl;


import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.*;

public class OTP_2
{
    private Stage stage;
    private Scene scene;
    public TextField provide_mail, auth_mail, auth_code, user_N;

    public PasswordField new_pass, confirm_pass;
    public static Button log_in;
    public Label message, new_pass_message, auth_mail_message, code_message;
    public static int code;

    static String fp ;

    @FXML
    private Label Mesage;
    @FXML
    private TextField givenOTP;
    @FXML
    private StackPane loading;

    static int id ;
    static  int flag ;


//    static check_email checkEmail = new check_email();

    public void switchForget_2(ActionEvent event) throws IOException {
//        boolean check = checkEmail.isEmailValid(auth_mail.getText());
//        if(auth_mail.getText().isBlank() && user_N.getText().isBlank())
//        {
//            message.setText("Fill out all the fields to proceed.");
//            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000), ev-> message.setText("")));
//            timeline.play();
//        } else if (auth_mail.getText().isBlank() || !check) {
//            message.setText(" Provide an authentication mail");
//            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000), ev-> message.setText("")));
//            timeline.play();
//        }  else if (user_N.getText().isBlank()) {
//            message.setText("Provide your Username");
//            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000), ev-> message.setText("")));
//            timeline.play();
//        }
//        else
//        {
//            try
//            {
        System.out.println(auth_mail.getText());

               Task<Void> task1 = new Task<Void>() {
                   @Override
                   protected Void call() throws Exception {
                       OTP_1 mail_background_task = new OTP_1();
                       code = mail_background_task.sendOTP(auth_mail.getText());
                       return null;
                   }
               };
               task1.setOnSucceeded(e->{
                   Platform.runLater(()->{
                       Parent root = null;
                       try {
                           root = FXMLLoader.load(Main.class.getResource("Forget_1.fxml"));
                       } catch (IOException ex) {
                           throw new RuntimeException(ex);
                       }
                       if(auth_mail.getText().isBlank()){
                           Mesage.setText("Enter your Email Address!");
                       }
                       else {
                            id = Main.Search2(auth_mail.getText());
                            if(id==0)
                            {
                                id = Main.Search3(auth_mail.getText());
                                if(id==0)  System.out.println("NOT Found!");
                                else {
                                    flag = 2;
                                    System.out.println(id+"\n");
                                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                    scene = new Scene(root);
                                    stage.setScene(scene);
                                    stage.show();
                                }
                            }
                            else {
                                flag = 1;
                                System.out.println(id+"\n");
                                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                scene = new Scene(root);
                                stage.setScene(scene);
                                stage.show();
                            }
                       }
                   });
               });
               task1.setOnRunning(e->{
                    Label lb = new Label("loading...");
                    //loading.getChildren().add(lb);
               });
               Thread th = new Thread(task1);
               th.start();
                //System.out.println("Bhai eita OTP "+code);
//                Thread thread = new Thread(mail_background_task);
//                thread.setDaemon(true);
//                thread.start();

//                Parent root = FXMLLoader.load(HelloApplication.class.getResource("FXML/VICTIM/FORGOT/OTP.fxml"));
                //Global.switch_scene(root,event);


//            }
//            catch (Exception e)
//            {
//                auth_mail_message.setText("Email not sent");
//                Timeline timeline = new Timeline(new KeyFrame(Duration.millis(500), ev-> auth_mail_message.setText("")));
//                timeline.play();
//            }
//        }
    }
    public void switchTOforget_3(ActionEvent e) throws IOException
    {
        //System.out.println("Bhai OTP apner OTP "+code);
        if(givenOTP.getText().equals(String.valueOf(code))) {
            Parent root = FXMLLoader.load(Main.class.getResource("Forget_2.fxml"));

            stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else if(!givenOTP.getText().equals(String.valueOf(code))){
            Mesage.setText("Wrong OTP,Try Again!");
        }
        else if(givenOTP.getText().isBlank()){
            Mesage.setText("Enter the given OTP");
        }
    }

    @FXML
    private TextField pass01;
    @FXML
    private TextField pass02;

    public void SwitchToDash(ActionEvent e) throws IOException
    {
        if(pass01.getText().isBlank()){
            Mesage.setText("Set your new password");
        }
        else if(pass02.getText().isBlank())
        {
            Mesage.setText("Confirm your password");
        }
        else if (pass01.getText().equals(pass02.getText())){
            if(flag==1)
            {
                Main.Update(pass01.getText(),id);
                ///Switch to Dashboard of Employee!
            }
            else if(flag==2){
                Main.Update1(pass01.getText(),id);
                ///Switch to Dashboard of Employer!
            }
            flag = 0;
            id = 0;
            ///Switch er kaaj hobe eikane
        }
        else{
            Mesage.setText("Passwords are not matced!");
        }
    }
    @FXML
    private Button cb;

    public void Can (ActionEvent e)
    {
        Stage stage = (Stage) cb.getScene().getWindow();
        stage.close();
    }
    /*@FXML
    private Button cancleButt1;

    public void CANCLE1(ActionEvent e)
    {
        Stage stage = (Stage) cancleButt1.getScene().getWindow();
        stage.close();
    }*/

   /** public void victim_create_pass(ActionEvent event) throws IOException {

        if(auth_code.getText().isBlank())
        {
            code_message.setText("Code cannot be blank");
            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(500), ev-> code_message.setText("")));
            timeline.play();
        }
        else
        {
            if(auth_code.getText().equals(String.valueOf(code)))
            {
                Parent root = FXMLLoader.load(HelloApplication.class.getResource("FXML/VICTIM/FORGOT/createNewPass.fxml"));
                Global.switch_scene(root,event);
            }
            else
            {
                code_message.setText("Code do not match");
                Timeline timeline = new Timeline(new KeyFrame(Duration.millis(500), ev-> code_message.setText("")));
                timeline.play();
            }
        }
    }

    public void close(ActionEvent event) throws IOException {

        String tnew = new_pass.getText();
        String tconfirm = confirm_pass.getText();

        if(new_pass.getText().isBlank() || confirm_pass.getText().isBlank())
        {
            new_pass_message.setText("New password cannot be empty");
            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(500), ev-> new_pass_message.setText("")));
            timeline.play();
        }

        else if(!new_pass.getText().equals(confirm_pass.getText()))
        {
            new_pass_message.setText("Passwords do not match");
            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(500), ev-> new_pass_message.setText("")));
            timeline.play();
        }
        else
        {
            try
            {
                /**File path = new File("E:/Java Code/Java Files/Sign Up/Victim Sign Up/" + fp + "/info.txt");
                Scanner sc = new Scanner(path);
                ArrayList<String> lines = new ArrayList<>();

                sc.useDelimiter("\n");

                String p = sc.next();
                String l1 = p;
                p = sc.next();
                String l2 = p;
                p = sc.next();
                String l3 = p;
                p = sc.next();
                String l4 = p;
                p = sc.next();
                String l5 = p;
                p = sc.next();
                String l6 = p;
                p = sc.next();
                String l7 = p;
                p = sc.next();
                String l8 = p;
                p = sc.next();
                String l9 = p;
                p = sc.next();
                String l10 = p;
                p = sc.next();
                String l11 = p;
                p = sc.next();
                String l12 = p;

                BufferedWriter writer = new BufferedWriter(new FileWriter(path));

                writer.write(l1 + "\n");
                writer.write(l2 + "\n");
                writer.write(l3 + "\n");
                writer.write(l4 + "\n");
                writer.write(l5 + "\n");
                writer.write(l6 + "\n");
                writer.write(l7 + "\n");
                writer.write(l8 + "\n");
                writer.write(tnew + "\n");
                writer.write(l10 + "\n");
                writer.write(l11 + "\n");
                writer.write(l12 + "\n");
                writer.close();

                Global.closeStage(CLOSE);

            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }*/
}

