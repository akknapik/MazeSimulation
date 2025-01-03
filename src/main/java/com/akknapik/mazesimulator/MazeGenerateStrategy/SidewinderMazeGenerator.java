package com.akknapik.mazesimulator.MazeGenerateStrategy;

import com.akknapik.mazesimulator.Cell;

import java.util.*;

public class SidewinderMazeGenerator extends MazeGeneratorStrategy implements IMazeGeneratorStrategy {
    private final Random random = new Random();

    @Override
    public void generateMaze(Cell[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        for (int row = 0; row < rows; row++) {
            List<Cell> currentRun = new ArrayList<>();

            for (int col = 0; col < cols; col++) {
                Cell current = grid[row][col];
                currentRun.add(current);


                if (col < cols - 1 && (row == 0 || random.nextBoolean())) {
                    Cell rightNeighbor = grid[row][col + 1];
                    removeWall(current, rightNeighbor);
                } else {
                    if (row > 0) {
                        Cell randomCell = currentRun.get(random.nextInt(currentRun.size()));
                        Cell cellAbove = grid[randomCell.getRow() - 1][randomCell.getCol()];
                        removeWall(randomCell, cellAbove);
                    }
                    currentRun.clear();
                }
            }
        }
    }
}

