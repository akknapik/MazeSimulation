package com.akknapik.mazesimulator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MazeSimulatorController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}