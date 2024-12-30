package com.akknapik.mazesimulator;

public class MazeData {
    private int sizeOfMaze;
    private String typeOfAlgorithmGeneration;

    public MazeData(int sizeOfMaze, String typeOfAlgorithmGeneration) {
        this.sizeOfMaze = sizeOfMaze;
        this.typeOfAlgorithmGeneration = typeOfAlgorithmGeneration;
    }

    public int getSizeOfMaze() {
        return sizeOfMaze;
    }

    public String getTypeOfAlgorithmGeneration() {
        return typeOfAlgorithmGeneration;
    }
}
