package com.akknapik.mazesimulator;

import com.akknapik.mazesimulator.State.IState;
import com.akknapik.mazesimulator.State.UnvisitedState;

public class Cell {
    private int row;
    private int col;
    private IState isVisited = new UnvisitedState();
    private boolean top = true;
    private boolean left = true;
    private boolean right = true;
    private boolean bottom = true;
    private boolean start = false;
    private boolean end = false;

    private int setID = -1; //use in Eller's algorithm

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public void setVisited(IState visited) {
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

    public void setStart(boolean start) {
        this.start = start;
    }

    public void setEnd(boolean end) {
        this.end = end;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public IState getVisited() {
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

    public boolean isStart() {
        return start;
    }

    public boolean isEnd() {
        return end;
    }

    public int getSetID() {
        return setID;
    }

    public void setSetID(int setID) {
        this.setID = setID;
    }
}
