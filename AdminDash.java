package com.example.dtl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.sql.*;
import java.util.Optional;
import java.util.ResourceBundle;

public class AdminDash implements Initializable {
    @FXML
    private AnchorPane AddEmpl_form;

    @FXML
    private AnchorPane Home_form;

    @FXML
    private Button addEmployee_AddBtn;

    @FXML
    private TableColumn<Employee, String> addEmployee_DOB;

    @FXML
    private TextField addEmployee_DOB_2;

    @FXML
    private TableColumn<Employee, String> addEmployee_Gender;

    @FXML
    private TextField addEmployee_Gender_2;

    @FXML
    private Button addEmployee_ImportBtn;

    @FXML
    private TableColumn<Employee, String> addEmployee_Name;

    @FXML
    private TextField addEmployee_Name_2;

    @FXML
    private Button addEmployee_UpdateBtn;

    @FXML
    private Button addEmployee_clrBtn;

    @FXML
    private Button addEmployee_dltBtn;

    @FXML
    private TableColumn<Employee, String> addEmployee_email;

    @FXML
    private TextField addEmployee_email_2;

    @FXML
    private TableColumn<Employee, Integer> addEmployee_employeeID;

    @FXML
    private ImageView addEmployee_img;

    @FXML
    private TableColumn<Employee, String> addEmployee_phn;

    @FXML
    private TextField addEmployee_phn_2;

    @FXML
    private TextField addEmployee_search;

    @FXML
    private TableView<Employee> addEmployee_table;

    @FXML
    private Label home_totalEmpl;

    @FXML
    private Label home_totalEmployer;

    @FXML
    private Button logout;
    private Connection connect;
    private Statement statement;
    private PreparedStatement prepare;
    private ResultSet result;
    @FXML
    private BarChart<?, ?> bar_chart;
    @FXML
    private CategoryAxis X;

    @FXML
    private NumberAxis Y;
    @FXML
    private AnchorPane Employer_home;
    @FXML
    private TableColumn<Employer, String> employer_Add;

    @FXML
    private TableColumn<Employer, String> employer_CpnyName;

    @FXML
    private TableColumn<Employer, String> employer_Email;

    @FXML
    private TableColumn<Employer, Integer> employer_ID;

    @FXML
    private TableColumn<Employer, String> employer_Name;

    @FXML
    private TextField employer_Search;

    @FXML
    private TableColumn<Employer, String> employer_Year;

    @FXML
    private TableView<Employer> employer_table;

    @FXML
    private TableColumn<Employer, String> employer_tradeLc;

    @FXML
    private TableColumn<Employer, String> employer_type;

    @FXML
    private Button EmployerBtn;




    public void Chart() {
        try {
            XYChart.Series<String, Integer> set1 = new XYChart.Series<>();
            Home_total_Empl();
            Home_total_employer();
            set1.getData().add(new XYChart.Data<>("Employees", totlaEMPL));
            set1.getData().add(new XYChart.Data<>("Employer", totlaemployer));

            // Make sure bar_chart is properly typed
            BarChart<String, Integer> barChart = (BarChart<String, Integer>) bar_chart;

            // Add the series to the chart's data
            barChart.getData().add(set1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Employee> addEmployeeListData() {

        ObservableList<Employee> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM emp";
        connect = Main.getConnection();


        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            Employee employeeD;

            while (result.next()) {
                employeeD = new Employee(result.getInt("ID"),
                        result.getString("Name"),
                        result.getString("DOB"),
                        result.getString("Email"),
                        result.getString("Gender"),
                        result.getString("Mobile")
                        );
                listData.add(employeeD);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }


    private ObservableList<Employee> addEmployeeList;
    public void addEmployeeShowListData() {
        addEmployeeList = addEmployeeListData();

        addEmployee_employeeID.setCellValueFactory(new PropertyValueFactory<>("id"));
        addEmployee_Name.setCellValueFactory(new PropertyValueFactory<>("name"));
        addEmployee_DOB.setCellValueFactory(new PropertyValueFactory<>("DOB"));
        addEmployee_email.setCellValueFactory(new PropertyValueFactory<>("Email"));
        addEmployee_Gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        addEmployee_phn.setCellValueFactory(new PropertyValueFactory<>("phn"));

        addEmployee_table.setItems(addEmployeeList);

        FilteredList<Employee> filteredList = new FilteredList<>(addEmployeeList, b -> true);

        addEmployee_search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(employee -> {
                if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                    return true;
                }
                String searchKey = newValue.toLowerCase();

                if (employee.getName().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (employee.getDOB().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (employee.getEmail().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (employee.getGender().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (employee.getPhn().toLowerCase().contains(searchKey)) {
                    return true;
                } else {
                    return false;
                }
            });
        });

        SortedList<Employee> sortedList = new SortedList<>(filteredList);

        sortedList.comparatorProperty().bind(addEmployee_table.comparatorProperty());

        addEmployee_table.setItems(sortedList);
    }


    public ObservableList<Employer> addEmployerListData() {

     ObservableList<Employer> listData1 = FXCollections.observableArrayList();
     String sql = "SELECT * FROM employer_1";
     connect = Main.getConnection();


     try {
     prepare = connect.prepareStatement(sql);
     result = prepare.executeQuery();
     Employer employeeD;

     while (result.next()) {
     employeeD = new Employer(result.getInt("ID"),
     result.getString("Name"),
     result.getString("Comp_Name"),
     result.getString("ESTyears"),
     result.getString("Address"),
     result.getString("TradeLc"),
     result.getString("Email"),
     result.getString("Type")
     );
     listData1.add(employeeD);

     }

     } catch (Exception e) {
     e.printStackTrace();
     }
     return listData1;
     }

     private ObservableList<Employer> addEmployerList;
    public void addEmployerShowListData() {
        addEmployerList = addEmployerListData();

        employer_ID.setCellValueFactory(new PropertyValueFactory<>("id"));
        employer_Name.setCellValueFactory(new PropertyValueFactory<>("name"));
        employer_CpnyName.setCellValueFactory(new PropertyValueFactory<>("c_name"));
        employer_Year.setCellValueFactory(new PropertyValueFactory<>("est_yr"));
        employer_Add.setCellValueFactory(new PropertyValueFactory<>("Address"));
        employer_tradeLc.setCellValueFactory(new PropertyValueFactory<>("TradeLc"));
        employer_Email.setCellValueFactory(new PropertyValueFactory<>("Email"));
        employer_type.setCellValueFactory(new PropertyValueFactory<>("type"));

        employer_table.setItems(addEmployerList);

        FilteredList<Employer> filteredList = new FilteredList<>(addEmployerList, b -> true);

        employer_Search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(employer -> {
                if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                    return true;
                }
                String searchKey = newValue.toLowerCase();

                if (employer.getName().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (employer.getC_name().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (employer.getEmail().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (employer.getAddress().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (employer.getType().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (employer.getEst_yr().toLowerCase().contains(searchKey)) {
                    return true;
                } else {
                    return false;
                }
            });
        });

        SortedList<Employer> sortedList = new SortedList<>(filteredList);

        sortedList.comparatorProperty().bind(employer_table.comparatorProperty());

        employer_table.setItems(sortedList);
    }

    public void AddBtn(ActionEvent e)
    {

    }



    private double x = 0;
    private double y = 0;

    public void LOGOUT(ActionEvent e){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Message");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to logout?");
        Optional<ButtonType> option = alert.showAndWait();
        try {
            if (option.get().equals(ButtonType.OK)) {

                logout.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("Intro.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);

                stage.initStyle(StageStyle.TRANSPARENT);

                stage.setScene(scene);
                stage.show();
            }
        } catch (Exception ee) {
            ee.printStackTrace();
        }

    }

    public static int totlaEMPL = 0;
    public static int totlaemployer = 0;

    @FXML
    private Button home_btn;
    @FXML
    private Button Add_empl_Btn;
    public void Home_total_Empl()
    {
        String Query  = "SELECT * FROM emp";
        try(PreparedStatement preparedStatement = Main.getConnection().prepareStatement(Query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            int f = 0;
            while(resultSet.next())
            {
                f++;
            }
            totlaEMPL = f;
            home_totalEmpl.setText(String.valueOf(f));
        }catch (SQLException ev)
        {
            ev.printStackTrace();
        }

    }
    public void Home_total_employer()
    {
        String Query  = "SELECT * FROM employer_1";
        try(PreparedStatement preparedStatement = Main.getConnection().prepareStatement(Query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            int f = 0;
            while(resultSet.next())
            {
                f++;
            }
            totlaemployer = f;
            home_totalEmployer.setText(String.valueOf(f));
        }catch (SQLException ev)
        {
            ev.printStackTrace();
        }
    }




    public void switchForm(ActionEvent e)
    {
        if(e.getSource()==home_btn)
        {
            Home_form.setVisible(true);
            AddEmpl_form.setVisible(false);
            Employer_home.setVisible(false);
            Home_total_Empl();
            Home_total_employer();

            //home_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #28966c);");
            //Add_empl_Btn.setStyle("-fx-background-color:transparent");
        }
        else if(e.getSource()==Add_empl_Btn)
        {
            Home_form.setVisible(false);
            AddEmpl_form.setVisible(true);
            Employer_home.setVisible(false);
            //addEmployeeShowListData();

            //Add_empl_Btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #28966c);");
            //home_btn.setStyle("-fx-background-color:transparent");
        }
        else if(e.getSource()==EmployerBtn)
        {
            Home_form.setVisible(false);
            AddEmpl_form.setVisible(false);
            Employer_home.setVisible(true);
        }
    }


    public void close()
    {
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Chart();
        addEmployeeShowListData();
        addEmployerShowListData();
    }
}
