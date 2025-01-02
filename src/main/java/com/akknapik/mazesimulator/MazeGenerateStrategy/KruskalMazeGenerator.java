package com.akknapik.mazesimulator.MazeGenerateStrategy;

import com.akknapik.mazesimulator.Cell;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KruskalMazeGenerator extends MazeGeneratorStrategy implements IMazeGeneratorStrategy{
    @Override
    public void generateMaze(Cell[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        List<Edge> edges = new ArrayList<>();
        int[][] parent = new int[rows][cols];

        for(int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                parent[row][col] = row * cols + col;
            }
        }

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if(row > 0) {
                    edges.add(new Edge(grid[row][col], grid[row-1][col]));
                }
                if(col > 0) {
                    edges.add(new Edge(grid[row][col], grid[row][col-1]));
                }
            }
        }

        Collections.shuffle(edges);

        for(Edge edge : edges) {
            Cell cell1 = edge.cell1;
            Cell cell2 = edge.cell2;

            if(find(parent, grid, cell1.getRow(), cell1.getCol()) != find(parent, grid, cell2.getRow(), cell2.getCol())) {
                union(parent, grid, cell1.getRow(), cell1.getCol(), cell2.getRow(), cell2.getCol());

                removeWall(cell1, cell2);
            }
        }
    }

    private int find(int[][] parent, Cell[][] grid, int row, int col) {
        int id = row * grid.length + col;
        if(parent[row][col] != id) {
            int parentId = parent[row][col];
            parent[row][col] = find(parent, grid, parentId / grid.length, parentId % grid.length);
        }
        return parent[row][col];
    }

    private void union(int[][] parent, Cell[][] grid, int row1, int col1, int row2, int col2) {
        int root1 = find(parent, grid, row1, col1);
        int root2 = find(parent, grid, row2, col2);
        parent[root1 / grid.length][root1 %  grid.length] = root2;
    }

    private static class Edge {
        Cell cell1, cell2;

        Edge(Cell cell1, Cell cell2) {
            this.cell1 = cell1;
            this.cell2 = cell2;
        }
    }
}
