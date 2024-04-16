module sio.leo.projet_conservatoire {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;

    opens sio.leo.projet_conservatoire to javafx.fxml;
    exports sio.leo.projet_conservatoire;
}
