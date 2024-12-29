package com.akknapik.mazesimulator;

import com.akknapik.mazesimulator.MazeGenerateStrategy.DFSMazeGenerator;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MazeSimulatorApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("maze-simulator.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("algorithm-selection.fxml"));
        Scene scene1 = new Scene(root);
        stage.setScene(scene1);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}