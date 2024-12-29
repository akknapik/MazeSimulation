package com.akknapik.mazesimulator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MazeSolver {
    private Maze maze;
    private List<MazeSolution> allSolutions;
    private MazeSolution correctSolution;

    public MazeSolver(Maze maze) {
        this.maze = maze;
        this.allSolutions = new ArrayList<>();
        this.correctSolution = null;
    }

    public void solveUntilCorrect() {
        Cell startCell = maze.getStartCell();
        Cell endCell = maze.getEndCell();

        if (startCell == null || endCell == null) {
            throw new IllegalArgumentException("Labirynt musi mieć start i koniec.");
        }

        findPathsUntilCorrect(startCell, endCell, new HashSet<>(), new ArrayList<>());
    }

    private void findPathsUntilCorrect(Cell current, Cell end, Set<Cell> visited, List<Cell> currentPath) {
        if (visited.contains(current)) return;

        visited.add(current);
        currentPath.add(current);

        if (current.equals(end)) {
            MazeSolution solution = new MazeSolution(new ArrayList<>(currentPath), true);
            allSolutions.add(solution);
            correctSolution = solution;
            return;
        }

        MazeSolution trialSolution = new MazeSolution(new ArrayList<>(currentPath), false);
        allSolutions.add(trialSolution);

        for (Cell neighbor : maze.getNeighbors(current)) {
            if (correctSolution == null) {
                findPathsUntilCorrect(neighbor, end, visited, currentPath);
            }
        }

        visited.remove(current);
        currentPath.remove(currentPath.size() - 1);
    }

    public List<MazeSolution> getAllSolutions() {
        return allSolutions;
    }

    public MazeSolution getCorrectSolution() {
        return correctSolution;
    }
}
