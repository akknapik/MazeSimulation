package com.akknapik.mazesimulator;

import com.akknapik.mazesimulator.MazeGenerateStrategy.IMazeGeneratorStrategy;

import java.util.ArrayList;
import java.util.List;

public class Maze {
    private Cell[][] grid;
    private IMazeGeneratorStrategy IMazeGeneratorStrategy;

    public Maze(int x, IMazeGeneratorStrategy IMazeGeneratorStrategy) {
        this.grid = new Cell[x][x];
        this.IMazeGeneratorStrategy = IMazeGeneratorStrategy;
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
        IMazeGeneratorStrategy.generateMaze(grid);
    }

    public Cell[][] getGrid() {
        return grid;
    }

    public int getLength() {
        return grid.length;
    }

    public List<Cell> getNeighbors(Cell current) {
        List<Cell> neighbors = new ArrayList<>();
        int x = current.getX();
        int y = current.getY();

        if (!current.isTop() && isInBorder(x - 1, y)) {
            neighbors.add(getCell(x - 1, y));
        }

        if (!current.isRight() && isInBorder(x, y + 1)) {
            neighbors.add(getCell(x, y + 1));
        }

        if (!current.isBottom() && isInBorder(x + 1, y)) {
            neighbors.add(getCell(x + 1, y));
        }

        if (!current.isLeft() && isInBorder(x, y - 1)) {
            neighbors.add(getCell(x, y - 1));
        }

        return neighbors;
    }

    private boolean isInBorder(int x, int y) {
        return x >= 0 && x < grid.length && y >= 0 && y < grid[0].length;
    }

    private Cell getCell(int x, int y) {
        return grid[x][y];
    }

    public Cell getStartCell() {
        for(Cell[] cells : grid) {
            for(Cell cell : cells) {
                if(cell.isStart()){
                    return cell;
                }
            }
        }
        return null;
    }

    public Cell getEndCell() {
        for(Cell[] cells : grid) {
            for(Cell cell : cells) {
                if(cell.isEnd()){
                    return cell;
                }
            }
        }
        return null;
    }

    public void deleteStartAndEnd() {
        for(Cell[] cells : grid) {
            for(Cell cell : cells) {
                if(cell.isEnd() || cell.isStart()){
                    cell.setEnd(false);
                    cell.setStart(false);
                }
            }
        }
    }
}
