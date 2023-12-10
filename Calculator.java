package com.example.dtl;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class Calculator  {

    @FXML
    private Label display;

    private String currentNumber = "";
    private String operator = "";
    private double result = 0;

    @FXML
    void handleButtonAction(ActionEvent event) {
        Button btn = (Button) event.getSource();
        String buttonText = btn.getText();

        if (buttonText.matches("[0-9]")) {
            currentNumber += buttonText;
            display.setText(currentNumber);
        } else if (buttonText.matches("[+\\-*/=]")) {
            if (!currentNumber.isEmpty()) {
                double num = Double.parseDouble(currentNumber);
                currentNumber = "";

                switch (operator) {
                    case "":
                        result = num;
                        break;
                    case "+":
                        result += num;
                        break;
                    case "-":
                        result -= num;
                        break;
                    case "*":
                        result *= num;
                        break;
                    case "/":
                        result /= num;
                        break;
                }

                if (!buttonText.equals("=")) {
                    operator = buttonText;
                } else {
                    operator = "";
                }

                display.setText(String.valueOf(result));
            }
        } else if (buttonText.equals("C")) {
            currentNumber = "";
            operator = "";
            result = 0;
            display.setText("");
        }
    }
}

