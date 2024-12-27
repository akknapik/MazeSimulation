package com.akknapik.mazesimulator.MazeGenerateStrategy;

import com.akknapik.mazesimulator.Cell;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class DFSMazeGenerator implements MazeGeneratorStrategy{
    @Override
    public void generateMaze(Cell[][] grid) {
        Stack<Cell> stack = new Stack<>();
        Cell start = grid[0][0];
        start.setVisited(true);
        stack.push(start);

        while (!stack.isEmpty()) {
            Cell current = stack.peek();
            List<Cell> neighbors = getUnvisitedNeighbors(current, grid);

            if(!neighbors.isEmpty()) {
                Cell neighbor = neighbors.get(new Random().nextInt(neighbors.size()));
                removeWall(current, neighbor);
                neighbor.setVisited(true);
                stack.push(neighbor);
            }
            else {
                stack.pop();
            }
        }
    }

    private List<Cell> getUnvisitedNeighbors(Cell cell, Cell[][] grid) {
        List<Cell> neighbors = new ArrayList<>();
        int x = cell.getX();
        int y = cell.getY();

        if (x > 0 && !grid[x - 1][y].isVisited()) neighbors.add(grid[x - 1][y]);
        if (x < grid.length - 1 && !grid[x + 1][y].isVisited()) neighbors.add(grid[x + 1][y]);
        if (y > 0 && !grid[x][y - 1].isVisited()) neighbors.add(grid[x][y - 1]);
        if (y < grid[0].length - 1 && !grid[x][y + 1].isVisited()) neighbors.add(grid[x][y + 1]);

        return neighbors;
    }

    private void removeWall(Cell current, Cell neighbor) {
        if(current.getX() == neighbor.getX()) {
            if(current.getY() < neighbor.getY()) {
                current.setRight(false);
                neighbor.setLeft(false);
            }
            else {
                current.setLeft(false);
                neighbor.setRight(false);
            }
        }
        else {
            if(current.getX() < neighbor.getX()) {
                current.setBottom(false);
                neighbor.setTop(false);
            }
            else {
                current.setTop(false);
                neighbor.setBottom(false);
            }
        }
    }
}
