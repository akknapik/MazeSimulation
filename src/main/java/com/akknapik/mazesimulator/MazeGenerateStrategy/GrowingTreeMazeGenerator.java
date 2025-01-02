package com.akknapik.mazesimulator.MazeGenerateStrategy;

import com.akknapik.mazesimulator.Cell;
import com.akknapik.mazesimulator.State.VisitedState;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GrowingTreeMazeGenerator extends MazeGeneratorStrategy implements IMazeGeneratorStrategy{
    private final Random random = new Random();


    @Override
    public void generateMaze(Cell[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        List<Cell> workingCells = new ArrayList<>();
        Cell current = grid[random.nextInt(rows)][random.nextInt(cols)];
        workingCells.add(current);
        current.setVisited(new VisitedState());

        while(!workingCells.isEmpty()) {
            current = selectCell(workingCells);
            List<Cell> unvisitedNeighbors = getUnvisitedNeighbors(current, grid);

            if(!unvisitedNeighbors.isEmpty()) {
                Cell neighbor = unvisitedNeighbors.get(random.nextInt(unvisitedNeighbors.size()));

                removeWall(current, neighbor);
                neighbor.setVisited(new VisitedState());
                workingCells.add(neighbor);
            } else {
                workingCells.remove(current);
            }
        }
    }

    private Cell selectCell(List<Cell> workingCells) {
        if(Math.random() < 0.5) {
            return workingCells.getLast();
        }
        else {
            return workingCells.get(random.nextInt(workingCells.size()));
        }
    }
}
