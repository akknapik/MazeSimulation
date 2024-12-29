package com.akknapik.mazesimulator;

import com.akknapik.mazesimulator.MazeGenerateStrategy.DFSMazeGenerator;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class MazeSelector {
    private int cellSize;
    private Cell[][] grid;
    private int rows;
    private int cols;
    private Cell startCell = null;
    private Cell endCell = null;
    private Runnable onStartSelected;
    private Runnable onEndSelected;

    public Pane createMazePane(Canvas canvas, Maze maze) {
        startCell = null;
        endCell = null;
        maze = new Maze(10,  new DFSMazeGenerator());
        maze.generateMaze();
        MazeDisplay.displayMaze(maze.getGrid());

        grid = maze.getGrid();
        rows = maze.getLength();
        cols = maze.getLength();
        cellSize = 800 / maze.getLength();
        GraphicsContext gc = canvas.getGraphicsContext2D();

        drawMaze(gc);

        canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            int col = (int) (e.getX() / cellSize);
            int row = (int) (e.getY() / cellSize);

            if (isValidCell(row, col)) {
                handleMouseClick(row, col);
                drawMaze(gc);
            }
        });

        Pane root = new Pane(canvas);
        root.setPrefSize(800, 800); // Ustawienie preferowanego rozmiaru kontenera

        return root;
    }

    public void setOnStartSelected(Runnable onStartSelected) {
        this.onStartSelected = onStartSelected;
    }

    public void setOnEndSelected(Runnable onEndSelected) {
        this.onEndSelected = onEndSelected;
    }

    private void handleMouseClick(int row, int col) {
        Cell clickedCell = grid[row][col];

        if (startCell == null) {
            startCell = clickedCell;
            clickedCell.setStart(true);
            if (onStartSelected != null) {
                onStartSelected.run();
            }
        } else if (endCell == null && clickedCell != startCell && isEdgeCell(row, col)) {
            endCell = clickedCell;
            clickedCell.setEnd(true);
            if (onEndSelected != null) {
                onEndSelected.run();
            }
        }
    }

    private void drawMaze(GraphicsContext gc) {
        gc.clearRect(0, 0, cols * cellSize, rows * cellSize);

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                Cell cell = grid[row][col];
                int x = col * cellSize;
                int y = row * cellSize;

                gc.setStroke(Color.BLACK);
                if (cell.isTop()) gc.strokeLine(x, y, x + cellSize, y);
                if (cell.isLeft()) gc.strokeLine(x, y, x, y + cellSize);
                if (cell.isBottom()) gc.strokeLine(x, y + cellSize, x + cellSize, y + cellSize);
                if (cell.isRight()) gc.strokeLine(x + cellSize, y, x + cellSize, y + cellSize);

                if (cell.isStart()) {
                    gc.setFill(Color.GREEN);
                    gc.fillRect(x + 2, y + 2, cellSize - 4, cellSize - 4);
                }

                if (cell.isEnd()) {
                    gc.setFill(Color.RED);
                    gc.fillRect(x + 2, y + 2, cellSize - 4, cellSize - 4);
                }
            }
        }
    }

    private boolean isValidCell(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    private boolean isEdgeCell(int row, int col) {
        return row == 0 || row == rows - 1 || col == 0 || col == cols - 1;
    }


    private Cell[][] generateDummyGrid(int rows, int cols) {
        Cell[][] grid = new Cell[rows][cols];
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                grid[row][col] = new Cell(row, col);
            }
        }

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                grid[row][col].setTop(true);
                grid[row][col].setLeft(true);
                grid[row][col].setBottom(true);
                grid[row][col].setRight(true);
            }
        }
        return grid;
    }
}
