package com.akknapik.mazesimulator;

import com.akknapik.mazesimulator.MazeGenerateStrategy.MazeGeneratorFactory;
import com.akknapik.mazesimulator.MazeSolveStrategy.MazeSolverFactory;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class MazeSimulatorController {

    private Stage stage;
    private Parent root;
    private MazeSelector mazeSelector;
    private Pane mazePane;
    private Scene scene;

    @FXML
    private RadioButton rKruskal, rPrim, rBacktracking, rHaK, rEller, rAldousBroder, rGrowingTree, rSidewinder;

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
    private ChoiceBox<String> chbAlgorithm;

    @FXML
    private Button bStartEnd;

    @FXML
    private Label selectError;

    private final String[] solveAlgorithms = {"Depth-First Search","Breadth-First Search"};

    public String typeOfGeneratorAlgorithm;
    private boolean startSelected = false;
    private boolean endSelected = false;
    private Maze maze;
    public int sizeOfMaze;
    private final MazeGeneratorFactory mazeGeneratorFactory = new MazeGeneratorFactory();
    private final MazeSolverFactory mazeSolverFactory = new MazeSolverFactory();
    private Timeline timeline;

    private void updateFooter() {
        if (!startSelected || !endSelected) {
            footerStatusText1.setVisible(true);
            footerText1.setVisible(true);
            mazeContainer.setVisible(true);
            mazeCanvas.setVisible(true);
            bBack.setVisible(false);
            lSelectAlgorithm.setVisible(false);
            chbAlgorithm.setVisible(false);
            bStart.setVisible(false);
            bStop.setVisible(false);
            bReset.setVisible(false);
            bStartEnd.setVisible(false);
        } else {
            footerStatusText1.setVisible(false);
            footerText1.setVisible(false);
            mazeContainer.setVisible(true);
            mazeCanvas.setVisible(true);
            bBack.setVisible(true);
            lSelectAlgorithm.setVisible(true);
            chbAlgorithm.setVisible(true);
            bStart.setVisible(true);
            bStop.setVisible(true);
            bReset.setVisible(true);
            bStartEnd.setVisible(true);
        }
    }

    public void printAlgorithmDescription() {
        descriptionText.getChildren().clear();
        if (rKruskal.isSelected()) {
            setDescriptionText("""
                    Kruskal's Algorithm generates a maze by treating each edge between cells as a separate unit. The edges are sorted by weight and then added to the maze, connecting cells without forming a cycle. The algorithm picks the shortest available edge, and the process repeats until all cells are connected.

                    Statistics:
                    - Time Complexity: O(E log E), where E is the number of edges. The complexity depends on the \
                    number of edges since the algorithm requires sorting all the edges.
                    - Space Complexity: O(V + E), where V is the number of vertices (cells) and E is the number of \
                    edges. The algorithm needs to store the edges and sets representing the connections.
                    - Maze Type: The resulting mazes are very balanced, with evenly distributed connections, and all\
                     walls are removed randomly.
                    """);
        } else if (rPrim.isSelected()) {
            setDescriptionText("""
                    Prim's Algorithm generates a maze by starting from a random cell and incrementally adding new cells to the growing tree, avoiding cycles. The algorithm iteratively adds edges to the growing maze, ensuring that connections are created optimally.

                    Statistics:
                    - Time Complexity: O(E log V), where E is the number of edges and V is the number of vertices. The algorithm requires using a priority structure to select the shortest edge.
                    - Space Complexity: O(V + E), as the algorithm needs to store all the vertices and edges in a graph structure.
                    - Maze Type: Generates dense mazes with many short paths. The mazes tend to be more connected and simpler compared to other algorithms.
                    """);
        } else if (rBacktracking.isSelected()) {
            setDescriptionText("""
                    Recursive Backtracking generates a maze by starting at one cell and randomly exploring neighboring cells, creating walls along the way. When a dead-end is encountered, the algorithm backtracks to the last visited cell and continues. The process repeats until all cells are connected.

                    Statistics:
                    - Time Complexity: O(V + E), where V is the number of cells and E is the number of edges. This complexity depends on the number of visited cells and their connections.
                    - Space Complexity: O(V), as the algorithm only needs to store the visited cells in a recursive stack.
                    - Maze Type: Generates mazes with very interesting and difficult paths. This algorithm is quite 'chaotic,' resulting in mazes with a complex structure.
                    """);
        } else if (rHaK.isSelected()) {
            setDescriptionText("""
                    Hunt and Kill is a combination of recursive and iterative techniques. It begins at a random cell and moves along unvisited neighboring cells. When a dead-end is encountered, the algorithm 'hunts' for other unvisited cells connected to already visited ones and continues.

                    Statistics:
                    - Time Complexity: O(V + E), but it can be slightly slower than Recursive Backtracking due to the additional 'hunting' step.
                    - Space Complexity: O(V), as it only stores the visited cells and states in memory.
                    - Maze Type: Creates mazes with a unique structure, often with branching corridors that can be easily closed at the end.
                    """);
        } else if (rEller.isSelected()) {
            setDescriptionText("""
                    Eller's Algorithm operates iteratively, creating maze levels one by one. At each level, the algorithm connects cells, making connections only where possible, and then moves to the next level to continue the process. It is efficient because it works level by level rather than analyzing the entire grid.

                    Statistics:
                    - Time Complexity: O(V), where V is the number of cells in the maze. This is very efficient compared to other algorithms.
                    - Space Complexity: O(V), as it only stores the currently active levels.
                    - Maze Type: The mazes created by Eller’s Algorithm have an interesting structure with clearly separated levels, and distinct transitions between levels.
                    """);
        } else if (rAldousBroder.isSelected()) {
            setDescriptionText("""
                    The Aldous-Broder Algorithm is a random algorithm that explores all the cells in the maze. It starts at a random cell and moves to neighboring cells, making connections. If the cell has already been visited, it chooses another cell until all cells are connected.

                    Statistics:
                    - Time Complexity: O(V * E), where V is the number of cells and E is the number of edges. This can lead to long execution times, especially for larger mazes.
                    - Space Complexity: O(V), as the algorithm only needs to store the visited cells.
                    - Maze Type: Generates mazes with highly random and unique structures that can have interesting and unpredictable geometry.
                    """);
        } else if (rGrowingTree.isSelected()) {
            setDescriptionText("""
                    The Growing Tree Algorithm is a flexible approach that uses various techniques to generate the maze. It starts from one cell and 'grows,' creating new cells and adding them to the growing structure. The choice of the next cell can be random or based on some criteria.

                    Statistics:
                    - Time Complexity: O(V + E), similar to other graph-based algorithms.
                    - Space Complexity: O(V), as the algorithm only stores the currently visited cells.
                    - Maze Type: Creates mazes with diverse structures and many possible corridors that are generated on the fly.
                    """);
        } else if (rSidewinder.isSelected()) {
            setDescriptionText("""
                    The Sidewinder Algorithm works iteratively, creating horizontal connections in the maze and then deciding whether to connect two cells on the same level or move to a higher level. The defining feature is that some connections are made 'outward,' leading to interesting branch-offs.

                    Statistics:
                    - Time Complexity: O(V + E), as it works similarly to other level-based algorithms.
                    - Space Complexity: O(V), as it only stores information about the levels.
                    - Maze Type: Generates mazes with many horizontal corridors and branch-offs that give the maze a distinctive structure.
                    """);
        }
    }

    public void generate(ActionEvent event) throws IOException {
        if (rKruskal.isSelected()) {
            typeOfGeneratorAlgorithm = "kruskal";
        } else if (rPrim.isSelected()) {
            typeOfGeneratorAlgorithm = "prim";
        } else if (rBacktracking.isSelected()) {
            typeOfGeneratorAlgorithm = "backtracking";
        } else if (rHaK.isSelected()) {
            typeOfGeneratorAlgorithm = "hak";
        } else if (rEller.isSelected()) {
            typeOfGeneratorAlgorithm = "eller";
        } else if (rAldousBroder.isSelected()) {
            typeOfGeneratorAlgorithm = "aldousbroder";
        } else if (rGrowingTree.isSelected()) {
            typeOfGeneratorAlgorithm = "growingtree";
        } else if (rSidewinder.isSelected()) {
            typeOfGeneratorAlgorithm = "sidewinder";
        } else {
            typeOfGeneratorAlgorithm = null;
        }

        if(validateTypeOfGeneratorAlgorithm() && validateMazeSize()) {
            sizeOfMaze = Integer.parseInt(selectMazeSize.getText());
            MazeData mazeData = new MazeData(sizeOfMaze, typeOfGeneratorAlgorithm);

            Node node = (Node) event.getSource();
            Stage tempStage = (Stage) node.getScene().getWindow();
            tempStage.close();

            clearError();
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("maze-simulator.fxml")));
            DataRelay dataRelay = DataRelay.getInstance();
            dataRelay.setMazeData(mazeData);
            stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    public void backToAlgorithms(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("algorithm-selection.fxml")));
        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void startSolver() {
        String typeSolveAlgorithm = chbAlgorithm.getValue();
        String typeSolver;
        if(typeSolveAlgorithm == "Depth-First Search") {
            typeSolver = "dfs";
        } else if (typeSolveAlgorithm == "Breadth-First Search") {
            typeSolver = "bfs";
        } else {
            typeSolver = null;
        }
        if(validateSolvingAlgorithm(typeSolver)) {
            System.out.println(typeSolver);
            MazeSolver solver = new MazeSolver(maze, mazeSolverFactory.createStrategy(typeSolver));
            solver.solve();

            List<MazeSolution> allSolutions = solver.getAllSolutions();

            maze.getStartCell();
            maze.getEndCell();
            animateSolutions(allSolutions);
        }
    }

    private boolean validateSolvingAlgorithm(String type) {
        if(type == null) {
            selectError.setVisible(true);
            return false;
        } else {
            selectError.setVisible(false);
            return true;
        }
    }

    private boolean validateMazeSize() {
        String input = selectMazeSize.getText();
        try {
            sizeOfMaze = Integer.parseInt(input);
            if (sizeOfMaze <= 1) {
                showError("Maze size must be greater than 1.");
                return false;
            } else if (sizeOfMaze > 25){
                showError("Maze size can't be greater than 25.");
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
        bRegenerate.setVisible(false);
        bOK.setVisible(false);
        footerText.setVisible(false);
        footerStatusText.setVisible(false);
        updateFooter();

        DataRelay dataRelay = DataRelay.getInstance();
        MazeData mazeData = dataRelay.getMazeData();

        mazeSelector = new MazeSelector();
        mazePane = mazeSelector.createMazePane(mazeCanvas, mazeData.getSizeOfMaze(),
                mazeGeneratorFactory.createStrategy(mazeData.getTypeOfAlgorithmGeneration()));
        chooseStartAndEnd();
        chbAlgorithm.getItems().addAll(solveAlgorithms);
    }

    public void regenerate() {
        resetSolutions();

        endSelected = false;
        startSelected = false;
        updateFooter();

        mazeContainer.getChildren().clear();

        DataRelay dataRelay = DataRelay.getInstance();
        MazeData mazeData = dataRelay.getMazeData();

        mazePane = mazeSelector.createMazePane(mazeCanvas, mazeData.getSizeOfMaze(),
                mazeGeneratorFactory.createStrategy(mazeData.getTypeOfAlgorithmGeneration()));
        chooseStartAndEnd();
    }

    public void chooseStartAndEnd() {
        bRegenerate.setVisible(true);

        if(maze != null) {
            resetSolutions();
        }
        startSelected = false;
        endSelected = false;

        updateFooter();

        mazePane = mazeSelector.changeStartEnd(mazeCanvas, mazeSelector.getMaze());
        maze = mazeSelector.getMaze();
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

    public void animateSolutions(List<MazeSolution> allSolutions) {
        GraphicsContext gc = mazeCanvas.getGraphicsContext2D();
        double cellWidth = 800 / maze.getGrid().length;
        double cellHeight = 800 / maze.getGrid().length;
        double squareSize = (800 / maze.getGrid().length) * 0.6;

        boolean resetNeeded = allSolutions.stream().anyMatch(MazeSolution::isSolved);

        if (resetNeeded && timeline != null && timeline.getStatus() == Animation.Status.STOPPED) {
            clearMaze();
            timeline = null;
        }

        if (timeline == null) {
            timeline = new Timeline();
            for (int i = 0; i < allSolutions.size(); i++) {
                MazeSolution solution = allSolutions.get(i);
                List<Cell> path = solution.getPath();
                boolean isCorrect = solution.isSolved();

                for (int j = 0; j < path.size(); j++) {
                    Cell cell = path.get(j);
                    double x = cell.getCol() * cellWidth + (cellWidth - squareSize) / 2;
                    double y = cell.getRow() * cellHeight + (cellHeight - squareSize) / 2;

                    Color fillColor = Color.color(0.1, 0.2, 0.2);
                    Color fillColor2 = Color.color(0, 1, 0);

                    int timeOffset = i * 100 + j * 10;

                    KeyFrame frame = new KeyFrame(Duration.millis(timeOffset), e -> {
                        gc.setFill(isCorrect ? fillColor2 : fillColor);
                        gc.fillRect(x, y, squareSize, squareSize);
                    });

                    timeline.getKeyFrames().add(frame);
                }
            }
        }

        if (timeline.getStatus() != Animation.Status.RUNNING) {
            timeline.play();
        }
    }

    public void stopAnimation() {
        if (timeline != null && timeline.getStatus() == Animation.Status.RUNNING) {
            timeline.pause();
        }
    }

    public void resetSolutions() {
        clearMaze();

        if (timeline != null && timeline.getStatus() != Animation.Status.STOPPED) {
            timeline.stop();
            timeline = null;
        }
    }

    public void clearMaze() {
        mazePane = mazeSelector.resetMaze(mazeCanvas, maze);
        maze = mazeSelector.getMaze();
        mazeContainer.getChildren().add(mazePane);
    }

    private void setDescriptionText(String text) {
        descriptionText.getChildren().clear();

        Text description = new Text(text);

        description.setFont(Font.font("Bahnschrift", 28));

        description.setTextAlignment(TextAlignment.CENTER);

        descriptionText.setPrefWidth(1000);

        descriptionText.setTextAlignment(TextAlignment.CENTER);

        descriptionText.getChildren().add(description);
    }
}
