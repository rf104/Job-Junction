package com.example.dtl;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.sql.*;
import java.io.IOException;
import java.util.Objects;

import static java.lang.Class.forName;

public class Main extends Application {

    public static Scene scene;
    @Override
    public void start(Stage stage) throws IOException {
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));


        try {
//            Parent root = FXMLLoader.load(getClass().getResource("Intro.fxml"));///Intro.fxml
            scene = new Scene(loadFXML("Intro"));
//            stage.initStyle(StageStyle.UNDECORATED);
//             scene = new Scene(root);
            stage.setTitle("JobJunction");


            stage.setScene(scene);
            ///stage.setFullScreen(true);
            stage.show();
        }catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(
                Main.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }
    public static Connection getConnection(){
        try{
            String driver = "com.mysql.cj.jdbc.Driver";
            String databaseUrl = "jdbc:mysql://localhost:3306/first";
            String userName = "root";
            String password = "aref_104";
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(databaseUrl,userName,password);

            System.out.println("Database connected!\n");
            return conn;
        }catch (Exception e)
        {
            e.printStackTrace();

        }
        return null;

    }

    public static void InsertData(String name,String mail,String mbl,String Add,String pass,String gen,String date){
        try {
            String insertQuery ="insert into emp (Name,Email,Mobile,Address,Password,Gender,DOB) values (?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = getConnection().prepareStatement(insertQuery);
            //preparedStatement.setInt(1,a);
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,mail);
            preparedStatement.setString(3,mbl);
            preparedStatement.setString(4,Add);
            preparedStatement.setString(5,pass);
            preparedStatement.setString(6,gen);
            preparedStatement.setString(7,date);
            int result = preparedStatement.executeUpdate();

            if(result==1){
                System.out.println("Data inserted!");
            }else System.out.println("Some Error!");

        }catch (Exception e)
        {
            System.out.println("Error!"+e);
        }
    }
    public static void InsertData2(String name,String pass,String CN ,String EST,String Add,String trade,String url,String type,String mail){
        try {
            String insertQuery ="insert into employer_1 (Name,Password,Comp_Name,ESTyears,Address,TradeLc,URL,Type,Email) values (?,?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = getConnection().prepareStatement(insertQuery);
            //preparedStatement.setInt(1,a);
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,pass);
            preparedStatement.setString(3,CN);
            preparedStatement.setString(4,EST);
            preparedStatement.setString(5,Add);
            preparedStatement.setString(6,trade);
            preparedStatement.setString(7,url);
            preparedStatement.setString(8,type);
            preparedStatement.setString(9,mail);
            int result = preparedStatement.executeUpdate();

            if(result==1){
                System.out.println("Data inserted!");
            }else System.out.println("Some Error!");

        }catch (Exception e)
        {
            System.out.println("Error!"+e);
        }
    }

    public static int Search(String name,String pass)
    {
        String Query  = "SELECT * FROM emp";
        try(PreparedStatement preparedStatement = getConnection().prepareStatement(Query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            int f = 1;
            while(resultSet.next())
            {
                String nam = resultSet.getString("Name");
                String password = resultSet.getString("Password");
                if(name.equals(nam)&&pass.equals(password)){
                    //System.out.println("LOGIN korete parben!");
                    f = f + 1;
                    return 1;
                }
            }
            if(f==1)
            {
               //System.out.println("Wrong Name!");
                return 0;
            }
        }catch (SQLException ev)
        {
            ev.printStackTrace();
        }
        return 0;

    }

    public static int Search1(String name,String pass)
    {
        String Query  = "SELECT * FROM employer_1";
        try(PreparedStatement preparedStatement = getConnection().prepareStatement(Query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            int f = 1;
            while(resultSet.next())
            {
                String nam = resultSet.getString("Name");
                String password = resultSet.getString("Password");
                if(name.equals(nam)&&pass.equals(password)){
                    //System.out.println("LOGIN korete parben!");
                    f = f + 1;
                    return 1;


                }
            }
            if(f==1)
            {
                //System.out.println("Wrong Name!");
                return 0;
            }
        }catch (SQLException ev)
        {
            ev.printStackTrace();
        }
        return 0;

    }




    public static int Search2(String email)
    {
        String Query  = "SELECT * FROM emp";
        try(PreparedStatement preparedStatement = getConnection().prepareStatement(Query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            int f = 1;
            while(resultSet.next())
            {
                String Email = resultSet.getString("Email");
                if(email.equals(Email)){
                    int id = resultSet.getInt("ID");
                    f = f + 1;
                    return id;
                }
            }
            if(f==1)
            {
                System.out.println("Wrong Email!");
                return 0;
            }
        }catch (SQLException ev)
        {
            ev.printStackTrace();
        }
        return 0;

    }
    public static int Search3(String email)
    {
        String Query  = "SELECT * FROM employer_1";
        try(PreparedStatement preparedStatement = getConnection().prepareStatement(Query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            int f = 1;
            while(resultSet.next())
            {
                String Email = resultSet.getString("Email");
                if(email.equals(Email)){
                    int id = resultSet.getInt("ID");
                    f = f + 1;
                    return id;
                }
            }
            if(f==1)
            {
                System.out.println("Wrong Email!");
                return 0;
            }
        }catch (SQLException ev)
        {
            ev.printStackTrace();
        }
        return 0;

    }

    public static void Update(String pass, int id) {
        try (Connection connection = getConnection()) {
            Statement st = connection.createStatement();
            int res = st.executeUpdate("UPDATE emp SET Password='" + pass + "' WHERE ID=" + id);
            if (res == 1) {
                System.out.println("Password updated successfully.");
            } else {
                System.out.println("Failed to update password.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void Update1(String pass, int id) {
        try (Connection connection = getConnection()) {
            Statement st = connection.createStatement();
            int res = st.executeUpdate("UPDATE employer_1 SET Password='" + pass + "' WHERE ID=" + id);
            if (res == 1) {
                System.out.println("Password updated successfully.");
            } else {
                System.out.println("Failed to update password.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }








    /*public static void insertData(){
        try{
            Statement statement = getConnection().createStatement();
            int result  = statement.executeUpdate("insert into user (ID,Name,Password ) values('7','REZA','202214045')");
            if(result==1) {
                System.out.println("Data inserted");
            }
            else System.out.println("Some error");
        }catch (Exception e)
        {
            System.out.println("Error!"+e);
        }
    }*/

    /*public static void main(String[] args) {

        //getConnection();
        //insertData();
        launch();

    }*/
}