module home.pruebajavafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens code to javafx.fxml;
    exports code;
    exports code.controllers;
    opens code.controllers to javafx.fxml;
}