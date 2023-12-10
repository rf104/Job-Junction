package com.example.dtl;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class Control implements Initializable{
        @FXML
        private Label loginMassageLabel;
        @FXML
        private TextField username;
        @FXML
        private PasswordField password;
        @FXML
        private ChoiceBox<String> choicebox;
        private String[] choice={"Male","Female","Others"};

        @FXML
        private ChoiceBox<String> CH;
        private String[] cH = {"Educational","Food Industry","Hospital & Health Care","Construction","IT Industry/Farm","Others"};

        @FXML
        private DatePicker mydatepicker;

       @FXML
       private TextField UserName;

       @FXML
       private TextField UserEmail;

       @FXML
       private TextField UserMbl;
       @FXML
       private TextField UseAddEmpl;
       @FXML
       private TextField UserPass;

       @FXML
       private Button cancleButt;
       @FXML
       private TextField confirmPass;
       @FXML
       private TextField bMail;


       @FXML
       private Label MessLabel;
        //String name,mail,mbl,Add,pass;

       static String[] Info = new String[7];
       static String[] LogInfo = new String[9];


        public void CANCLE(ActionEvent e)
        {
            Stage stage = (Stage) cancleButt.getScene().getWindow();
            stage.close();
        }

       public void SIGNinfo(ActionEvent e){
           if(!UserName.getText().isBlank()&&!UserEmail.getText().isBlank()&& !UserMbl.getText().isBlank()&&!UseAddEmpl.getText().isBlank()){
                 String name = UserName.getText();
                 String mail = UserEmail.getText();
                 String mbl = UserMbl.getText();
                 String Add = UseAddEmpl.getText();
                 Info[0] = name;
                 Info[1] = mail;
                 Info[2] = mbl;
                 Info[3] = Add;

                // String pass = UserPass.getText();
                //HelloApplication.InsertData(name,mail,mbl,Add);
               System.out.println("Done!!");
           }
           else if(UserName.getText().isBlank()){
               MessLabel.setText("Enter your name!");
           }
           else if(UserEmail.getText().isBlank()){
               MessLabel.setText("Enter your email.");
           }
           else if(UserMbl.getText().isBlank()){
               MessLabel.setText("Enter your phone number!");
           }
           else if (UseAddEmpl.getText().isBlank()) {
               MessLabel.setText("Enter your Address!");
           }
           else {

               MessLabel.setText("Enter the every information box in the form!"+"");
           }
       }

       public void SUB_EMP(ActionEvent e) throws IOException {
           Log3(e);
           if(Trade.getText().isBlank()){
               MessLabel.setText("Enter your Trade No");
           }
           else if(!Trade.getText().isBlank()&&!bMail.getText().isBlank())
           {
                    Main.InsertData2(LogInfo[0],LogInfo[1],LogInfo[2],LogInfo[3],LogInfo[4],LogInfo[5],LogInfo[6],LogInfo[7],LogInfo[8]);

               Parent root = FXMLLoader.load(getClass().getResource("Page_1.fxml"));
               stage = (Stage)((Node)e.getSource()).getScene().getWindow();
               scene = new Scene(root);
               stage.setScene(scene);
               stage.show();

                    LogInfo[0] = null;
                    LogInfo[1] = null;
               LogInfo[2] = null;
               LogInfo[3] = null;
               LogInfo[4] = null;
               LogInfo[5] = null;
               LogInfo[6] = null;
               LogInfo[7] = null;
               LogInfo[8] = null;
               System.out.println("DONE EMP");
           }
           else{
               System.out.println("Error!");
           }
       }

    public void submit(ActionEvent e) throws IOException {
        if(UserPass.getText().isBlank()){
            MessLabel.setText("Enter Password!");
        }
        else if(UserPass.getText().equals(confirmPass.getText()))
        {

            String pass = UserPass.getText();
            String gen = choicebox.getValue();
            Info[5] = gen;
            Info[4] = pass;
            createFolders(Info[0]);
            System.out.println(Info[0]+" "+Info[1]+" "+Info[2]+" "+Info[3]);
            Main.InsertData(Info[0],Info[1],Info[2],Info[3],Info[4],Info[5],Info[6]);
            Parent root  = FXMLLoader.load(getClass().getResource("Login_Employee.fxml"));
            stage = (Stage)((Node)e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();


            System.out.println("Done!!");
            Info[0] = null;
            Info[1] = null;
            Info[2] = null;
            Info[3] = null;
            Info[4] = null;
            Info[5] = null;
            Info[6] = null;
        }
        else if(UserPass.getText()!=confirmPass.getText()){
            MessLabel.setText("Passwords are not matched!");
        }

        /*
           if(name!=null&&mail!=null&&mbl!=null&&Add!=null&&!UserPass.getText().isBlank()){

               pass = UserPass.getText();
               HelloApplication.InsertData(name,mail,mbl,Add,pass);
               System.out.println("Done!!");
           }*/
        else {

            System.out.println("Error!");
        }
    }
    private void createFolders(String folderName) {

        if (!folderName.isEmpty()) {
            // Create the main folder
            File mainFolder = new File("E:\\"+folderName);
            mainFolder.mkdir();
            create_folder(mainFolder,"Certificates");
            create_folder(mainFolder,"CSV");
            create_folder(mainFolder,"CV");
            create_folder(mainFolder,"Normal_image");
            create_folder(mainFolder,"profile_image");

        }
    }
    private void create_folder(File mainfolder,String sub_folder)
    {
        File subFolder = new File(mainfolder,sub_folder);
        subFolder.mkdir();
    }


       /**private void handleSignUp(ActionEvent event) throws SQLException {
           String username = UserName.getText();
           String mail = UserEmail.getText();
           String mbl =  UserMbl.getText();
           String Add = UseAddEmpl.getText();
           getConnection();
           String insertQuery = "insert into employe (ID,Name,Email,Mobile,Address) Values ('?','?','?','?','?')";
           PreparedStatement psIt = getConnection().prepareStatement(insertQuery);
           psIt.setString(1,"1");
           psIt.setString(2,username);
           psIt.setString(3,mail);
           psIt.setString(4,mbl);
           psIt.setString(5,Add);
           int row = psIt.executeUpdate();
           if(row>0) System.out.println("Error nai chill");
           else System.out.println("Error bhai2");

       }
       //
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

       }*/

       @FXML
       private TextField AddName;
       @FXML
       private TextField AddPass;
       @FXML
       private Button AddlogButt;
       @FXML
       private Label Op;


       public void SwitchAdDash(ActionEvent e) throws IOException
       {
           if(AddName.getText().isBlank()&&AddPass.getText().isBlank())
           {
               Op.setText("Enter Username and Password!");
           }
           else if(AddName.getText().isBlank()){
               Op.setText("Enter your Username!");
           }
           else if(AddPass.getText().isBlank()){
               Op.setText("Enter your Password!");
           }
           else {
               if(AddName.getText().equals("ADMIN")&&AddPass.getText().equals("password")){
                   Parent root = FXMLLoader.load(getClass().getResource("Admin_Dash.fxml"));
                   stage = (Stage)((Node)e.getSource()).getScene().getWindow();
                   scene = new Scene(root);
                   stage.setScene(scene);
                   stage.show();
               }
               else{
                   Op.setText("Wrong Try!");
               }
           }
       }



        //
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(choicebox!=null)choicebox.getItems().addAll(choice);
        if(CH!=null) CH.getItems().addAll(cH);
    }

    public void loginButtonAction(ActionEvent e)
        {
            if(username.getText().isBlank() == false&&password.getText().isBlank() == false)
            {
                loginMassageLabel.setText("You Try to Login!");
            }
            else
            {
                loginMassageLabel.setText("Please enter username and password!");
            }
        }



        private Stage stage;
        private Scene scene;
        private Parent root;
        public void switchToAdmin(ActionEvent e) throws IOException {
            Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
            stage = (Stage)((Node)e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        public void switchToSignUp(ActionEvent e) throws IOException {
            Parent root = FXMLLoader.load(getClass().getResource("UserSignUp.fxml"));
            stage = (Stage)((Node)e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        public void switchToSignUp2(ActionEvent e) throws IOException {

            SIGNinfo(e);
            if(UserName.getText().isBlank()){
                MessLabel.setText("Enter your name!");
            }
            else if(UserEmail.getText().isBlank()){
                MessLabel.setText("Enter your email.");
            }
            else if(UserMbl.getText().isBlank()){
                MessLabel.setText("Enter your phone number!");
            }
            else if (UseAddEmpl.getText().isBlank()) {
                MessLabel.setText("Enter your Address!");
            }
            else if(UserName.getText().isBlank()&&UserEmail.getText().isBlank()&& UserMbl.getText().isBlank()&&UseAddEmpl.getText().isBlank())
            {
                MessLabel.setText("Enter the every information box in the form!"+"");
            }
            else{
            Parent root = FXMLLoader.load(getClass().getResource("UserSignUp2.fxml"));
            stage = (Stage)((Node)e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            }
        }
        public void switchToLogEmployer(ActionEvent e) throws IOException {
            Parent root = FXMLLoader.load(getClass().getResource("Page_1.fxml"));
            stage = (Stage)((Node)e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        public void switchToSignEm(ActionEvent e) throws IOException {
            Parent root = FXMLLoader.load(getClass().getResource("signup_employeers.fxml"));
            stage = (Stage)((Node)e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }

        public void switchForget_1(ActionEvent e) throws IOException{
            Parent root = FXMLLoader.load(getClass().getResource("ForgetPass.fxml"));

            stage = (Stage)((Node)e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        /*public void switchForget_2(ActionEvent e) throws IOException{
            Parent root = FXMLLoader.load(getClass().getResource("Forget_1.fxml"));

            stage = (Stage)((Node)e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        public void switchForget_3(ActionEvent e) throws IOException{
            Parent root = FXMLLoader.load(getClass().getResource("Forget_2.fxml"));

            stage = (Stage)((Node)e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }*/


        public void switchToSignEm2(ActionEvent e) throws IOException {
            Log(e);
            if(Uname.getText().isBlank()){MessLabel.setText("Enter your name!");}
            else if(L1pass.getText().isBlank()){MessLabel.setText("Enter your password");}
            else if(L2pass.getText().isBlank()){MessLabel.setText("Enter your Confirm password");}
            else if(!L1pass.getText().equals(L2pass.getText())){MessLabel.setText("Password is not matched!");}
            else {
                Parent root = FXMLLoader.load(getClass().getResource("SignUpEmp2.fxml"));
                stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        }
        public void switchToSignEm3(ActionEvent e) throws IOException {
            Log2(e);
            if (Cname.getText().isBlank()) {
                MessLabel.setText("Enter Company name!");
            }
            else if(EST.getText().isBlank()){MessLabel.setText("Enter your year of establishment!");}
            else if(Addr.getText().isBlank()){MessLabel.setText("Enter your Company Address!");}
            else {
                Parent root = FXMLLoader.load(getClass().getResource("SignUpEmp3.fxml"));
                stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        }

        public void switchToIntro(ActionEvent e) throws IOException {
            Parent root = FXMLLoader.load(getClass().getResource("Intro.fxml"));
            stage = (Stage)((Node)e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        public void switchToUsLogin(ActionEvent e) throws IOException{
            Parent root  = FXMLLoader.load(getClass().getResource("Login_Employee.fxml"));
            stage = (Stage)((Node)e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        public void switchToAdminDash(ActionEvent e) throws IOException{
            Parent root = FXMLLoader.load(getClass().getResource("Admin_DashBoard.fxml"));
            stage  = (Stage)((Node) e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }

    public void getDate(ActionEvent actionEvent) {
           if(mydatepicker!=null){
               LocalDate date = mydatepicker.getValue();
               Info[6] = date.toString();
           }
    }

        @FXML
        private TextField Uname;
        @FXML
        private TextField L1pass;
        @FXML
        private TextField L2pass;
        @FXML
        private TextField Cname;
        @FXML
        private TextField EST;
        @FXML
        private TextField Addr;
        @FXML
        private TextField Trade;
        @FXML
        private TextField url;

        public void Log(ActionEvent e)
        {
            if(!Uname.getText().isBlank()&&!L1pass.getText().isBlank()&&!L2pass.getText().isBlank()&&L1pass.getText().equals(L2pass.getText()))
            {
                String name = Uname.getText();
                String pass  = L1pass.getText();
                LogInfo[0] = name;
                LogInfo[1] = pass;
            }
            else if(Uname.getText().isBlank()){MessLabel.setText("Enter your name!");}
            else if(L1pass.getText()!=L2pass.getText()){MessLabel.setText("Password is not matched!");}
        }
        public void Log2(ActionEvent e)
        {
            if(!Cname.getText().isBlank()&&!EST.getText().isBlank()&&!Addr.getText().isBlank())
            {
                String CN = Cname.getText();
                String est = EST.getText();
                String Add = Addr.getText();

                LogInfo[2] = CN;
                LogInfo[3] = est;
                LogInfo[4] = Add;

            } else if (Cname.getText().isBlank()) {
                MessLabel.setText("Enter Company name!");
            }
            else if(EST.getText().isBlank()){MessLabel.setText("Enter your year of establishment!");}
            else if(Addr.getText().isBlank()){MessLabel.setText("Enter your Company Address!");}
        }
        public void Log3(ActionEvent e)
        {
            if(!Trade.getText().isBlank()&&!url.getText().isBlank()&&!bMail.getText().isBlank()){
                String trade = Trade.getText();
                String Url = url.getText();
                String type = CH.getValue();
                String mail = bMail.getText();
                LogInfo[5] = trade;
                LogInfo[6] = Url;
                LogInfo[8] = mail;
                if(type!=null) LogInfo[7] = type;


            }
            else if(Trade.getText().isBlank()) {MessLabel.setText("Enter your Business/Trade License No");}
            else if(url.getText().isBlank()){MessLabel.setText("Enter your URL");}
            else if(bMail.getText().isBlank()){MessLabel.setText("Enter your Email");}
        }
        @FXML
        private  TextField name;
        @FXML
        private TextField password_1;

        public void LOGIN(ActionEvent e) throws IOException {
            /**Employee er loging button*/
            ///Employee/user er kaaj eita
            String a = name.getText();
            String b = password_1.getText();
            int k = Main.Search(a,b);
            if(k==1) {

                System.out.println("LOG IN KOR BHAI");
                Parent root  = FXMLLoader.load(getClass().getResource("hello-view123.fxml"));
                stage = (Stage)((Node)e.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
            else System.out.println("NA BHAI VUL hoise ");
        }
        @FXML
        private Label name_employer;
        public void LOGIN1(ActionEvent e) throws Exception
        {
            ///Employer er login Button
            String a = name.getText();
            String b = password_1.getText();
            int  k = Main.Search1(a,b);
            if(k==1){
                System.out.println("LOG IN KOR BHAI");
                Parent root = FXMLLoader.load(getClass().getResource("Employer.fxml"));
                //if(!name.getText().isBlank())name_employer.setText(a);
                stage  = (Stage)((Node) e.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
            else System.out.println("NA BHAI VUL hoise ");
        }


    /*
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
    }*/
}














////NO Need Part
/*
public class Contol implements Initializable {
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
}*/
