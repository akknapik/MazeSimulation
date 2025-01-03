package com.akknapik.mazesimulator.MazeSolveStrategy;

import com.akknapik.mazesimulator.Maze;
import com.akknapik.mazesimulator.MazeSolution;

import java.util.List;

public interface IMazeSolverStrategy {
    public List<MazeSolution> solveMaze(Maze maze);
}
