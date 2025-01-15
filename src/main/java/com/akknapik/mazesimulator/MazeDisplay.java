package com.akknapik.mazesimulator;

public class MazeDisplay {
    public static void displayMaze(Cell[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        for (int col = 0; col < cols; col++) {
            System.out.print(" _");
        }
        System.out.println();

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                Cell cell = grid[row][col];

                if (cell.isLeft()) {
                    System.out.print("|");
                } else {
                    System.out.print(" ");
                }

                if (cell.isBottom()) {
                    System.out.print("_");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println("|");
        }
    }
}
