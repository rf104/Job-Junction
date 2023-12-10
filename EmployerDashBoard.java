package com.example.dtl;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class EmployerDashBoard {
    @FXML
    private Button LogOutBtn;

    @FXML
    private Button candidate_btn;

    @FXML
    private AnchorPane candidate_form;

    @FXML
    private Button post_btn;

    @FXML
    private AnchorPane post_form;

    @FXML
    private Button profile_btn;

    @FXML
    private AnchorPane profile_form;

    public void switchForm(ActionEvent e)
    {
        if(e.getSource()==post_btn)
        {
            post_form.setVisible(true);
            candidate_form.setVisible(false);
            profile_form.setVisible(false);

        }
        else if(e.getSource()==candidate_btn)
        {
            post_form.setVisible(false);
            candidate_form.setVisible(true);
            profile_form.setVisible(false);
        }
        else if(e.getSource()==profile_btn)
        {
            post_form.setVisible(false);
            candidate_form.setVisible(false);
            profile_form.setVisible(true);
        }
    }
}
