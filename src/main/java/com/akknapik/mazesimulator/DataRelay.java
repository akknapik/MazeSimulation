package com.akknapik.mazesimulator;

import javafx.scene.chart.PieChart;

public class DataRelay {
    private MazeData mazeData;
    private final static DataRelay INSTANCE = new DataRelay();

    private DataRelay() {}

    public static DataRelay getInstance() {
        return INSTANCE;
    }

    public MazeData getMazeData() {
        return mazeData;
    }

    public void setMazeData(MazeData mazeData) {
        this.mazeData = mazeData;
    }
}
