module com.example.stafffx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires jdk.compiler;
    requires kotlin.stdlib;
    requires annotations;

    // Export for access in tests or external modules
    exports com.example.stafffx.Model;

    opens com.example.stafffx to javafx.fxml;
    exports com.example.stafffx;
    exports com.example.stafffx.Controller;
    opens com.example.stafffx.Controller to javafx.fxml;
}
