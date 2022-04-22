module com.example.pharmacy {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.pharmacy to javafx.fxml;
    opens com.example.pharmacy.Models to javafx.base,javafx.fxml;
    exports com.example.pharmacy ;
    exports com.example.pharmacy.Controllers;
    exports com.example.pharmacy.Models;
    opens com.example.pharmacy.Controllers to javafx.fxml;
}