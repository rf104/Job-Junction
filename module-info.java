module com.example.dtl {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.mail;
    requires java.desktop;


    opens com.example.dtl to javafx.fxml;
    exports com.example.dtl;
}