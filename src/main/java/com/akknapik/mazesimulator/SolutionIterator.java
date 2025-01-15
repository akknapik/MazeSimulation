package com.akknapik.mazesimulator;

import java.util.List;

public class SolutionIterator implements IMazeSolutionIterator {
    private final List<MazeSolution> solutions;
    private int currentIndex = 0;

    public SolutionIterator(List<MazeSolution> solutions) {
        this.solutions = solutions;
    }

    @Override
    public boolean hasNext() {
        return currentIndex < solutions.size();
    }

    @Override
    public MazeSolution next() {
        if (!hasNext()) {
            throw new IllegalStateException("No more solutions.");
        }
        return solutions.get(currentIndex++);
    }
}
