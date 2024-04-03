module org.example.demo {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.demo to javafx.fxml;
    exports org.example.demo.FileTree;
    opens org.example.demo.FileTree to javafx.fxml;
}