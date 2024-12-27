module com.akknapik.mazesimulator {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.akknapik.mazesimulator to javafx.fxml;
    exports com.akknapik.mazesimulator;
    exports com.akknapik.mazesimulator.MazeGenerateStrategy;
    opens com.akknapik.mazesimulator.MazeGenerateStrategy to javafx.fxml;
}