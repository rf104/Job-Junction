package com.example.dtl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Graph {

    @FXML
    private PieChart pieChart;

    @FXML
    private LineChart<String, Number> lineChart;

    @FXML
    private BarChart<String, Number> barChart;

    @FXML
    private AreaChart<String, Number> areaChart;

    @FXML
    private void initialize() {
        // Load data from CSV file
        File file = chooseFile();
        if (file != null) {
            ObservableList<PieChart.Data> pieChartData = loadPieChartData(file);
            XYChart.Series<String, Number> lineChartData = loadLineChartData(file);
            XYChart.Series<String, Number> barChartData = loadBarChartData(file);
            XYChart.Series<String, Number> areaChartData = loadAreaChartData(file);

            // Set data to charts
            pieChart.setData(pieChartData);

            lineChart.getData().add(lineChartData);

            barChart.getData().add(barChartData);

            areaChart.getData().add(areaChartData);
        }
    }

    private File chooseFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose CSV File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
        return fileChooser.showOpenDialog(new Stage());
    }

    private ObservableList<PieChart.Data> loadPieChartData(File file) {
        ObservableList<PieChart.Data> data = FXCollections.observableArrayList();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String category = parts[0].trim();
                double value = Double.parseDouble(parts[1].trim());
                data.add(new PieChart.Data(category, value));
            }
        } catch (IOException | NumberFormatException e) {
            showAlert("Error", "Error loading data from CSV file.");
        }

        return data;
    }

    private XYChart.Series<String, Number> loadLineChartData(File file) {
        return loadData(file);
    }

    private XYChart.Series<String, Number> loadBarChartData(File file) {
        return loadData(file);
    }

    private XYChart.Series<String, Number> loadAreaChartData(File file) {
        return loadData(file);
    }

    private XYChart.Series<String, Number> loadData(File file) {
        XYChart.Series<String, Number> series = new XYChart.Series<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String category = parts[0].trim();
                double value = Double.parseDouble(parts[1].trim());
                series.getData().add(new XYChart.Data<>(category, value));
            }
        } catch (IOException | NumberFormatException e) {
            showAlert("Error", "Error loading data from CSV file.");
        }

        return series;
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
