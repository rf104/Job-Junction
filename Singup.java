package com.example.dtl;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class Singup implements Initializable {
    @FXML
    private DatePicker myDatePicker;
    @FXML

    private ChoiceBox choicebox;
    private String[] choice={"Male","Female","Others"};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choicebox.getItems().addAll(choice);
    }
    public void getDate(ActionEvent e)
    {
        LocalDate myDate = myDatePicker.getValue();
        System.out.println(myDate.toString());
    }
}
