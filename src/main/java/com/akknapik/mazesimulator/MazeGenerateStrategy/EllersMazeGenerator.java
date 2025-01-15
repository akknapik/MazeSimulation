package com.akknapik.mazesimulator.MazeGenerateStrategy;

import com.akknapik.mazesimulator.Cell;

import java.util.*;

public class EllersMazeGenerator extends MazeGeneratorStrategy implements IMazeGeneratorStrategy {

    @Override
    public void generateMaze(Cell[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int setCounter = 1;

        for (int col = 0; col < cols; col++) {
            grid[0][col].setSetID(setCounter++);
        }

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols - 1; col++) {
                if (Math.random() < 0.5) {
                    Cell current = grid[row][col];
                    Cell next = grid[row][col + 1];
                    if (current.getSetID() != next.getSetID()) {
                        mergeSets(grid[row], current.getSetID(), next.getSetID());
                        removeWall(current, next);
                    }
                }
            }

            if (row < rows - 1) {
                Map<Integer, List<Integer>> setMap = new HashMap<>();
                for (int col = 0; col < cols; col++) {
                    int setId = grid[row][col].getSetID();
                    setMap.computeIfAbsent(setId, k -> new ArrayList<>()).add(col);
                }

                for (var entry : setMap.entrySet()) {
                    List<Integer> columns = entry.getValue();
                    boolean connectionMade = false;
                    for (int col : columns) {
                        if (Math.random() < 0.5 || !connectionMade) {
                            connectionMade = true;
                            Cell current = grid[row][col];
                            Cell below = grid[row + 1][col];
                            below.setSetID(current.getSetID());
                           removeWall(current, below);
                        }
                    }
                }
            }

            if (row < rows - 1) {
                for (int col = 0; col < cols; col++) {
                    if (grid[row + 1][col].getSetID() == -1) {
                        grid[row + 1][col].setSetID(setCounter++);
                    }
                }
            }
        }

        Cell[] lastRow = grid[rows - 1];
        for (int col = 0; col < cols - 1; col++) {
            if (lastRow[col].getSetID() != lastRow[col + 1].getSetID()) {
                mergeSets(lastRow, lastRow[col].getSetID(), lastRow[col + 1].getSetID());
                removeWall(lastRow[col], lastRow[col + 1]);
            }
        }
    }

    private void mergeSets(Cell[] row, int oldSet, int newSet) {
        for (Cell cell : row) {
            if (cell.getSetID() == oldSet) {
                cell.setSetID(newSet);
            }
        }
    }
}
