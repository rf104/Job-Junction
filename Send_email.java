package com.example.dtl;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Send_email {

    @FXML
    private TextField e_name;

    @FXML
    private TextField e_sub;

    @FXML
    private TextArea e_text;
    @FXML
    void send_first(ActionEvent event) {
        String recipient,subject,info;
        recipient=e_name.getText();
        subject=e_sub.getText();
        info=e_text.getText();
        sendEmail("jobjunction.official.mist@gmail.com","eqxcldbgkkjfplpa",recipient,subject,info);
    }
    private void sendEmail(String senderEmail, String password, String recipientEmail, String subject, String message) {

        // Set up properties for the mail session
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com"); // Replace with your email provider's SMTP host
        properties.put("mail.smtp.port", "587"); // Replace with the appropriate port

        // Create a Session object
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, password);
            }
        });

        try {
            Message mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress(senderEmail));
            mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            mimeMessage.setSubject(subject);
            mimeMessage.setText(message);
            Transport.send(mimeMessage);

            System.out.println("Email sent successfully!");

        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("Error sending email: " + e.getMessage());
        }
    }
}
