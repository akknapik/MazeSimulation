package com.akknapik.mazesimulator;

import com.akknapik.mazesimulator.MazeGenerateStrategy.DFSMazeGenerator;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MazeSimulatorApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MazeSimulatorApplication.class.getResource("algorithm-selection.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
//        launch();
        Maze maze = new Maze(100, 100, new DFSMazeGenerator());
        maze.generateMaze();
        MazeDisplay.displayMaze(maze.getGrid());
    }
}