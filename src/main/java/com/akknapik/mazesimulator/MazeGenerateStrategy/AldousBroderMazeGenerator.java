package com.akknapik.mazesimulator.MazeGenerateStrategy;

import com.akknapik.mazesimulator.Cell;
import com.akknapik.mazesimulator.Maze;
import com.akknapik.mazesimulator.State.UnvisitedState;
import com.akknapik.mazesimulator.State.VisitedState;

import java.util.List;
import java.util.Random;

public class AldousBroderMazeGenerator extends MazeGeneratorStrategy implements IMazeGeneratorStrategy{
    @Override
    public void generateMaze(Cell[][] grid) {
        int sizeOfMaze = grid.length;
        Cell current = grid[new Random().nextInt(sizeOfMaze)][new Random().nextInt(sizeOfMaze)];
        current.setVisited(new VisitedState());
        int visitedCounter = 1;
        int numberOfCells = sizeOfMaze * sizeOfMaze;

        while (visitedCounter < numberOfCells) {
            List<Cell> neighbors = getNeighbors(current, grid);
            Cell neighbor = neighbors.get(new Random().nextInt(neighbors.size()));

            if(!neighbor.getVisited().isVisited()) {
                removeWall(current, neighbor);
                neighbor.setVisited(new VisitedState());
                visitedCounter++;
            }
            //visitedCounter++;
            current = neighbor;
        }
    }
}
