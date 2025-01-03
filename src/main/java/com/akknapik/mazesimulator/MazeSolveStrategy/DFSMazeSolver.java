package com.akknapik.mazesimulator.MazeSolveStrategy;

import com.akknapik.mazesimulator.Cell;
import com.akknapik.mazesimulator.Maze;
import com.akknapik.mazesimulator.MazeSolution;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DFSMazeSolver extends MazeSolverStrategy implements IMazeSolverStrategy {
    private boolean isSolved = false;

    @Override
    public List<MazeSolution> solveMaze(Maze maze) {
        List<MazeSolution> allSolutions = new ArrayList<>();
        Cell startCell = maze.getStartCell();
        Cell endCell = maze.getEndCell();

        checkStartEnd(startCell, endCell);

        Set<Cell> visited = new HashSet<>();
        List<Cell> path = new ArrayList<>();
        findPaths(startCell, endCell, visited, path, allSolutions, maze);
        return allSolutions;
    }

    private void findPaths(Cell current, Cell endCell, Set<Cell> visited, List<Cell> path,
                           List<MazeSolution> allSolutions, Maze maze) {
        if(visited.contains(current)) {
            return;
        }

        visited.add(current);
        path.add(current);

        if(current.equals(endCell)) {
            allSolutions.add(new MazeSolution(new ArrayList<>(path), true));
            isSolved = true;
            return;
        }

        allSolutions.add(new MazeSolution(new ArrayList<>(path), false));

        for(Cell neighbor : maze.getNeighbors(current)) {
            findPaths(neighbor, endCell, visited, path, allSolutions, maze);
            if(isSolved) {
                return;
            }
        }

        visited.remove(current);
        path.remove(path.size()-1);
    }
}
