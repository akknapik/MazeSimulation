package com.akknapik.mazesimulator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.io.IOException;

public class MazeSimulatorController {

    private BorderPane borderPane;
    private Stage stage;
    private Parent root;
    private MazeSelector mazeSelector;
    private Pane mazePane;
    private Scene scene;

    @FXML
    private RadioButton rKruskal, rPrim, rDFS, rHaK, rEller, rAldousBroder, rGrowingTree, rBacktracking;

    @FXML
    private TextField selectMazeSize;

    @FXML
    private TextFlow descriptionText;

    @FXML
    private Label errorLabel;

    @FXML
    private Pane mazeContainer;

    @FXML
    private Canvas mazeCanvas;

    @FXML
    private Pane footer;

    @FXML
    private Button bOK;

    @FXML
    private Label footerStatusText;

    @FXML
    private Label footerText;

    @FXML
    private Label footerStatusText1;

    @FXML
    private Label footerText1;

    @FXML
    private Button bBack;

    @FXML
    private Button bRegenerate;

    @FXML
    private Button bStop;

    @FXML
    private Button bReset;

    @FXML
    private Button bStart;

    @FXML
    private Label lSelectAlgorithm;

    @FXML
    private ChoiceBox chbAlgorithm;

    @FXML
    private Slider sSlider;

    private String typeOfGeneratorAlgorithm;
    private boolean startSelected = false;
    private boolean endSelected = false;
    private boolean firstOK = true;
    private Maze maze;
    private int sizeOfMaze;

    @FXML
    public void initialize(ActionEvent event) throws IOException {

    }

    private void updateFooter() {
        if (!startSelected || !endSelected) {
            footerStatusText1.setVisible(true);
            footerText1.setVisible(true);
            mazeContainer.setVisible(true);
            mazeCanvas.setVisible(true);
            bBack.setVisible(false);
            bRegenerate.setVisible(false);
            lSelectAlgorithm.setVisible(false);
            chbAlgorithm.setVisible(false);
            bStart.setVisible(false);
            bStop.setVisible(false);
            bReset.setVisible(false);
            sSlider.setVisible(false);
        } else {
            footerStatusText1.setVisible(false);
            footerText1.setVisible(false);
            mazeContainer.setVisible(true);
            mazeCanvas.setVisible(true);
            bBack.setVisible(true);
            bRegenerate.setVisible(true);
            lSelectAlgorithm.setVisible(true);
            chbAlgorithm.setVisible(true);
            bStart.setVisible(true);
            bStop.setVisible(true);
            bReset.setVisible(true);
            sSlider.setVisible(true);
        }
    }

    public void printAlgorithmDescription() {
        descriptionText.getChildren().clear();
        if (rKruskal.isSelected()) {
            descriptionText.getChildren().add(new Text("Kruskal's Algorithm: This algorithm finds a minimum spanning tree by sorting edges by weight and adding them to the tree without forming a cycle."));
        } else if (rPrim.isSelected()) {
            descriptionText.getChildren().add(new Text("Prim's Algorithm: A greedy algorithm that builds a minimum spanning tree by starting with a single node and repeatedly adding the lowest-weight edge."));
        } else if (rDFS.isSelected()) {
            descriptionText.getChildren().add(new Text("Depth-First Search (DFS): An algorithm for traversing or searching tree or graph data structures by exploring as far as possible along each branch."));
        } else if (rHaK.isSelected()) {
            descriptionText.getChildren().add(new Text("Hunt-and-Kill Algorithm: A maze generation algorithm that randomly selects unvisited neighbors to connect paths."));
        } else if (rEller.isSelected()) {
            descriptionText.getChildren().add(new Text("Eller's Algorithm: A maze generation algorithm that works row by row and maintains sets of connected cells."));
        } else if (rAldousBroder.isSelected()) {
            descriptionText.getChildren().add(new Text("Aldous-Broder Algorithm: A maze generation algorithm that uses a random walk to ensure every cell is visited exactly once."));
        } else if (rGrowingTree.isSelected()) {
            descriptionText.getChildren().add(new Text("Growing Tree Algorithm: A maze generation algorithm that randomly selects cells from a growing list to carve paths."));
        } else if (rBacktracking.isSelected()) {
            descriptionText.getChildren().add(new Text("Backtracking Algorithm: A maze generation algorithm that builds paths by backtracking when a dead end is encountered."));
        }
    }

    public void generate(ActionEvent event) throws IOException {
        if (rKruskal.isSelected()) {
            typeOfGeneratorAlgorithm = "kruskal";
        } else if (rPrim.isSelected()) {
            typeOfGeneratorAlgorithm = "prim";
        } else if (rDFS.isSelected()) {
            typeOfGeneratorAlgorithm = "dfs";
        } else if (rHaK.isSelected()) {
            typeOfGeneratorAlgorithm = "hak";
        } else if (rEller.isSelected()) {
            typeOfGeneratorAlgorithm = "eller";
        } else if (rAldousBroder.isSelected()) {
            typeOfGeneratorAlgorithm = "aldousbroder";
        } else if (rGrowingTree.isSelected()) {
            typeOfGeneratorAlgorithm = "growingtree";
        } else if (rBacktracking.isSelected()) {
            typeOfGeneratorAlgorithm = "backtracking";
        } else {
            typeOfGeneratorAlgorithm = null;
        }

        if(validateTypeOfGeneratorAlgorithm() && validateMazeSize()) {
            clearError();
            root = FXMLLoader.load(getClass().getResource("maze-simulator.fxml"));
            stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }

    }

    public void backToAlgorithms(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("algorithm-selection.fxml"));
        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private boolean validateMazeSize() {
        String input = selectMazeSize.getText();
        try {
            sizeOfMaze = Integer.parseInt(input);
            if (sizeOfMaze <= 0) {
                showError("Maze size must be a positive integer.");
                return false;
            } else if (sizeOfMaze > 100){
                showError("Maze size can't be greater than 100.");
                return false;
            }
            else {
                clearError();
                return true;
            }
        } catch (NumberFormatException e) {
            showError("Invalid input. Please enter a valid integer.");
            return false;
        }
    }

    private boolean validateTypeOfGeneratorAlgorithm() {
        if(typeOfGeneratorAlgorithm == null) {
            showError("Algorithm type for generation is not selected.");
            return false;
        }
        else {
            return true;
        }
    }

    private void showError(String errorText) {
        errorLabel.setText(errorText);
        errorLabel.setVisible(true);
    }

    private void clearError() {
        errorLabel.setVisible(false);
    }

    public void changeVisibleScene() {
        bOK.setVisible(false);
        footerText.setVisible(false);
        footerStatusText.setVisible(false);
        updateFooter();
        mazeSelector = new MazeSelector();
        mazePane = mazeSelector.createMazePane(mazeCanvas, maze);

        mazeContainer.getChildren().add(mazePane);

        mazeSelector.setOnStartSelected(() -> {
            startSelected = true;
            updateFooter();
        });

        mazeSelector.setOnEndSelected(() -> {
            endSelected = true;
            updateFooter();
        });
    }

    public void regenerate() {
        endSelected = false;
        startSelected = false;
        updateFooter();

        mazeContainer.getChildren().clear();

        mazePane = mazeSelector.createMazePane(mazeCanvas, maze);

        mazeContainer.getChildren().add(mazePane);

        mazeSelector.setOnStartSelected(() -> {
            startSelected = true;
            updateFooter();
        });

        mazeSelector.setOnEndSelected(() -> {
            endSelected = true;
            updateFooter();
        });
    }
}
