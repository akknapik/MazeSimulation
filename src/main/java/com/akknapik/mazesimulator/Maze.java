package com.akknapik.mazesimulator;

import com.akknapik.mazesimulator.MazeGenerateStrategy.MazeGeneratorStrategy;

public class Maze {
    private Cell[][] grid;
    private MazeGeneratorStrategy mazeGeneratorStrategy;

    public Maze(int x, int y, MazeGeneratorStrategy mazeGeneratorStrategy) {
        this.grid = new Cell[x][y];
        this.mazeGeneratorStrategy = mazeGeneratorStrategy;
        initializeGrid();
    }

    private void initializeGrid() {
        for(int row = 0; row < grid.length; row++) {
            for(int col = 0; col < grid[0].length; col++) {
                grid[row][col] = new Cell(row, col);
            }
        }
    }

    public void generateMaze() {
        mazeGeneratorStrategy.generateMaze(grid);
    }

    public Cell[][] getGrid() {
        return grid;
    }
}
