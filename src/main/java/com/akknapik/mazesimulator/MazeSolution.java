package com.akknapik.mazesimulator;

import java.util.List;

public class MazeSolution {
    private List<Cell> path;
    private boolean solved;

    public MazeSolution(List<Cell> path, boolean solved) {
        this.path = path;
        this.solved = solved;
    }

    public List<Cell> getPath() {
        return path;
    }

    public boolean isSolved() {
        return solved;
    }

    @Override
    public String toString() {
        return "path=" + path + ", " + solved;
    }
}
