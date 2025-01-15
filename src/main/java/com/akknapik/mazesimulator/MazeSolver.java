package com.akknapik.mazesimulator;

import com.akknapik.mazesimulator.MazeSolveStrategy.IMazeSolverStrategy;

import java.util.ArrayList;
import java.util.List;

public class MazeSolver {
    private Maze maze;
    private IMazeSolverStrategy mazeSolverStrategy;
    private static List<MazeSolution> allSolutions;
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

    public List<MazeSolution> getAllSolutions() {
        return allSolutions;
    }

    public static IMazeSolutionIterator iterator() {
        return new SolutionIterator(allSolutions);
    }
}
