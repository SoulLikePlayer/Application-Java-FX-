module org.example.demo {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.demo to javafx.fxml;
    exports org.example.demo.TaskGestionnaire;
    opens org.example.demo.TaskGestionnaire to javafx.fxml;
}