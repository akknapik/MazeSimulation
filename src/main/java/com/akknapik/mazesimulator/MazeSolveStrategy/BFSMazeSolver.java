package com.akknapik.mazesimulator.MazeSolveStrategy;

import com.akknapik.mazesimulator.Cell;
import com.akknapik.mazesimulator.Maze;
import com.akknapik.mazesimulator.MazeSolution;

import java.util.*;

public class BFSMazeSolver extends MazeSolverStrategy implements IMazeSolverStrategy {
    @Override
    public List<MazeSolution> solveMaze(Maze maze) {
        List<MazeSolution> allSolutions = new ArrayList<>();
        Cell startCell = maze.getStartCell();
        Cell endCell = maze.getEndCell();

        checkStartEnd(startCell, endCell);

        Queue<Cell> queue = new LinkedList<>();
        Map<Cell, Cell> cameFrom = new HashMap<>();
        Set<Cell> visited = new HashSet<>();

        queue.offer(startCell);
        visited.add(startCell);
        cameFrom.put(startCell, null);

        boolean foundSolution = false;

        while (!queue.isEmpty() && !foundSolution) {
            Cell current = queue.poll();

            for (Cell neighbor : maze.getNeighbors(current)) {
                if (foundSolution) {
                    break;
                }

                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.offer(neighbor);
                    cameFrom.put(neighbor, current);

                    if (neighbor.equals(endCell)) {
                        List<Cell> path = reconstructPath(cameFrom, startCell, endCell);
                        allSolutions.add(new MazeSolution(path, true));
                        foundSolution = true;
                    } else {
                        List<Cell> path = reconstructPath(cameFrom, startCell, neighbor);
                        allSolutions.add(new MazeSolution(path, false));
                    }
                }
            }
        }

        if (allSolutions.isEmpty()) {
            allSolutions.add(new MazeSolution(new ArrayList<>(), false));
        }

        return allSolutions;
    }

    private List<Cell> reconstructPath(Map<Cell, Cell> cameFrom, Cell startCell, Cell endCell) {
        List<Cell> path = new ArrayList<>();

        for (Cell cell = endCell; cell != null; cell = cameFrom.get(cell)) {
            path.add(cell);
        }

        Collections.reverse(path);
        return path;
    }
}
