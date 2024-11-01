module com.example.stafffx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.stafffx to javafx.fxml;
    exports com.example.stafffx;
    exports com.example.stafffx.Controller;
    opens com.example.stafffx.Controller to javafx.fxml;
}