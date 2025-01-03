package com.akknapik.mazesimulator.MazeSolveStrategy;

import com.akknapik.mazesimulator.MazeGenerateStrategy.IMazeGeneratorStrategy;

public interface IMazeSolverFactory {
    IMazeSolverStrategy createStrategy(String type);

}
