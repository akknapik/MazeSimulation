package com.akknapik.mazesimulator.MazeGenerateStrategy;

import com.akknapik.mazesimulator.Cell;
import com.akknapik.mazesimulator.State.VisitedState;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PrimMazeGenerator extends MazeGeneratorStrategy implements IMazeGeneratorStrategy{
    @Override
    public void generateMaze(Cell[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        List<Cell[]> neighbors = new ArrayList<>();

        Cell current = grid[new Random().nextInt(rows)][new Random().nextInt(cols)];
        current.setVisited(new VisitedState());
        addNeighbors(grid, neighbors, current);

        while (!neighbors.isEmpty()) {
            Cell[] wall = neighbors.remove(new Random().nextInt(neighbors.size()));

            current = wall[0];
            Cell neighbor = wall[1];

            if(!neighbor.getVisited().isVisited()) {
                removeWall(current, neighbor);

                neighbor.setVisited(new VisitedState());

                addNeighbors(grid, neighbors, neighbor);
            }
        }


    }

    private void addNeighbors(Cell[][] grid, List<Cell[]> walls, Cell cell) {
        int row = cell.getRow();
        int col = cell.getCol();
        int rows = grid.length;
        int cols = grid[0].length;

        if (row > 0) walls.add(new Cell[]{cell, grid[row - 1][col]}); // Góra
        if (row < rows - 1) walls.add(new Cell[]{cell, grid[row + 1][col]}); // Dół
        if (col > 0) walls.add(new Cell[]{cell, grid[row][col - 1]}); // Lewo
        if (col < cols - 1) walls.add(new Cell[]{cell, grid[row][col + 1]}); // Prawo
    }
}
