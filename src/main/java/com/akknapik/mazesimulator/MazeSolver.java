package com.akknapik.mazesimulator;

import com.akknapik.mazesimulator.MazeSolveStrategy.IMazeSolverStrategy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MazeSolver {
    private Maze maze;
    private IMazeSolverStrategy mazeSolverStrategy;
    private List<MazeSolution> allSolutions;
    private MazeSolution correctSolution;

    public MazeSolver(Maze maze, IMazeSolverStrategy mazeSolverStrategy) {
        this.maze = maze;
        this.mazeSolverStrategy = mazeSolverStrategy;
        this.allSolutions = new ArrayList<>();
        this.correctSolution = null;
    }

    public void solve() {
        allSolutions = mazeSolverStrategy.solveMaze(maze);
        for(MazeSolution mazeSolution : allSolutions) {
            if(mazeSolution.isSolved()) {
                correctSolution = mazeSolution;
                break;
            }
        }
    }

//    private void findPathsUntilCorrect(Cell current, Cell end, Set<Cell> visited, List<Cell> currentPath) {
//        if (visited.contains(current)) return;
//
//        visited.add(current);
//        currentPath.add(current);
//
//        if (current.equals(end)) {
//            MazeSolution solution = new MazeSolution(new ArrayList<>(currentPath), true);
//            allSolutions.add(solution);
//            correctSolution = solution;
//            return;
//        }
//
//        MazeSolution trialSolution = new MazeSolution(new ArrayList<>(currentPath), false);
//        allSolutions.add(trialSolution);
//
//        for (Cell neighbor : maze.getNeighbors(current)) {
//            if (correctSolution == null) {
//                findPathsUntilCorrect(neighbor, end, visited, currentPath);
//            }
//        }
//
//        visited.remove(current);
//        currentPath.remove(currentPath.size() - 1);
//    }

    public List<MazeSolution> getAllSolutions() {
        return allSolutions;
    }

    public MazeSolution getCorrectSolution() {
        return correctSolution;
    }
}
