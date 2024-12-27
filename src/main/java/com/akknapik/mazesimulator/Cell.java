package com.akknapik.mazesimulator;

public class Cell {
    private int x;
    private int y;
    private boolean isVisited = false;
    private boolean top = true;
    private boolean left = true;
    private boolean right = true;
    private boolean bottom = true;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setVisited(boolean visited) {
        this.isVisited = visited;
    }

    public void setTop(boolean top) {
        this.top = top;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public void setBottom(boolean bottom) {
        this.bottom = bottom;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isVisited() {
        return isVisited;
    }

    public boolean isTop() {
        return top;
    }

    public boolean isLeft() {
        return left;
    }

    public boolean isRight() {
        return right;
    }

    public boolean isBottom() {
        return bottom;
    }
}
