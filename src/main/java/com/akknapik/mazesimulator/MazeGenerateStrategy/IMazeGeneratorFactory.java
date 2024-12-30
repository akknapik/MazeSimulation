package com.akknapik.mazesimulator.MazeGenerateStrategy;

public interface IMazeGeneratorFactory {
    IMazeGeneratorStrategy createStrategy(String type);
}
