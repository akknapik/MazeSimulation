package com.akknapik.mazesimulator.MazeGenerateStrategy;

import com.akknapik.mazesimulator.Cell;
import com.akknapik.mazesimulator.State.VisitedState;

import java.util.List;
import java.util.Random;
import java.util.Stack;

public class DFSMazeGenerator extends MazeGeneratorStrategy implements IMazeGeneratorStrategy {
    @Override
    public void generateMaze(Cell[][] grid) {
        Stack<Cell> stack = new Stack<>();
        Cell start = grid[0][0];
        start.setVisited(new VisitedState());
        stack.push(start);

        while (!stack.isEmpty()) {
            Cell current = stack.peek();
            List<Cell> neighbors = getUnvisitedNeighbors(current, grid);

            if(!neighbors.isEmpty()) {
                Cell neighbor = neighbors.get(new Random().nextInt(neighbors.size()));
                removeWall(current, neighbor);
                neighbor.setVisited(new VisitedState());
                stack.push(neighbor);
            }
            else {
                stack.pop();
            }
        }
    }
}
