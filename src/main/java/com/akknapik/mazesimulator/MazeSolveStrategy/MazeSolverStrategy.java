package com.akknapik.mazesimulator.MazeSolveStrategy;

import com.akknapik.mazesimulator.Cell;

import java.util.ArrayList;
import java.util.List;

public class MazeSolverStrategy {
    public void checkStartEnd(Cell startCell, Cell endCell) {

        if(startCell == null || endCell == null) {
            throw new IllegalArgumentException("Maze must have a start and an end.");
        }
    }

    public List<Cell> getUnvisitedCells(Cell[][] grid) {
        List<Cell> unvisitedCells = new ArrayList<>();
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if(!grid[row][col].getVisited().isVisited()) {
                    unvisitedCells.add(grid[row][col]);
                }
            }
        }
        return unvisitedCells;
    }

    public List<Cell> getUnvisitedNeighbors(Cell cell, Cell[][] grid) {
        List<Cell> neighbors = new ArrayList<>();
        int x = cell.getRow();
        int y = cell.getCol();

        if (x > 0 && !grid[x - 1][y].getVisited().isVisited()) neighbors.add(grid[x - 1][y]);
        if (x < grid.length - 1 && !grid[x + 1][y].getVisited().isVisited()) neighbors.add(grid[x + 1][y]);
        if (y > 0 && !grid[x][y - 1].getVisited().isVisited()) neighbors.add(grid[x][y - 1]);
        if (y < grid[0].length - 1 && !grid[x][y + 1].getVisited().isVisited()) neighbors.add(grid[x][y + 1]);

        return neighbors;
    }

    public List<Cell> getVisitedNeighbors(Cell cell, Cell[][] grid) {
        List<Cell> neighbors = new ArrayList<>();
        int x = cell.getRow();
        int y = cell.getCol();

        if (x > 0 && grid[x - 1][y].getVisited().isVisited()) neighbors.add(grid[x - 1][y]);
        if (x < grid.length - 1 && grid[x + 1][y].getVisited().isVisited()) neighbors.add(grid[x + 1][y]);
        if (y > 0 && grid[x][y - 1].getVisited().isVisited()) neighbors.add(grid[x][y - 1]);
        if (y < grid[0].length - 1 && grid[x][y + 1].getVisited().isVisited()) neighbors.add(grid[x][y + 1]);

        return neighbors;
    }

    public List<Cell> getNeighbors(Cell cell, Cell[][] grid) {
        List<Cell> neighbors = new ArrayList<>();
        int x = cell.getRow();
        int y = cell.getCol();

        if (x > 0) neighbors.add(grid[x - 1][y]);
        if (x < grid.length - 1) neighbors.add(grid[x + 1][y]);
        if (y > 0) neighbors.add(grid[x][y - 1]);
        if (y < grid[0].length - 1) neighbors.add(grid[x][y + 1]);

        return neighbors;
    }
}
