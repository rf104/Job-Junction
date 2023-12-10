package com.example.dtl;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;

import java.net.URL;
import java.util.ResourceBundle;

public class SignupEmployeers implements Initializable {
    @FXML
    private ChoiceBox industry_box;

    private  String[] choice = {"Education","Hospital/Diagnostic Center","Hotel/Restaurant","Information Technology (IT)","Engineering","Others"};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        industry_box.getItems().addAll(choice);
    }
}
