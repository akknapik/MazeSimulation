package com.akknapik.mazesimulator.MazeGenerateStrategy;

import com.akknapik.mazesimulator.Cell;
import com.akknapik.mazesimulator.State.VisitedState;

import java.util.List;
import java.util.Random;

public class HuntAndKillMazeGenerator extends MazeGeneratorStrategy implements IMazeGeneratorStrategy {
    @Override
    public void generateMaze(Cell[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        Cell current = grid[new Random().nextInt(rows)][new Random().nextInt(cols)];
        current.setVisited(new VisitedState());

        while (true) {
            System.out.println("while1");

            List<Cell> neighbors = getUnvisitedNeighbors(current, grid);

            while (!neighbors.isEmpty()) {
                System.out.println("while2");

                Cell neighbor = neighbors.get(new Random().nextInt(neighbors.size()));
                removeWall(current, neighbor);
                neighbor.setVisited(new VisitedState());
                current = neighbor;
                neighbors = getUnvisitedNeighbors(current, grid);
            }

            boolean foundNextCell = false;

            outerFor:
            for (int row = 0; row < rows; row++) {
                System.out.println("for1");

                for (int col = 0; col < cols; col++) {
                    System.out.println("for2");

                    Cell cell = grid[row][col];
                    if (!cell.getVisited().isVisited()) {
                        List<Cell> visitedNeighbors = getVisitedNeighbors(cell, grid);
                        if (!visitedNeighbors.isEmpty()) {
                            Cell visitedNeighbor = visitedNeighbors.get(new Random().nextInt(visitedNeighbors.size()));
                            removeWall(cell, visitedNeighbor);
                            cell.setVisited(new VisitedState());
                            current = cell;
                            foundNextCell = true;
                            break outerFor;
                        }
                    }
                }
            }

            if (!foundNextCell) {
                break;
            }
        }
        System.out.println("koniec");
    }
}

