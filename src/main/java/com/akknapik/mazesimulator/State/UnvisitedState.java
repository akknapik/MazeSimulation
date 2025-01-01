package com.akknapik.mazesimulator.State;

public class UnvisitedState implements IState{
    @Override
    public boolean isVisited() {
        return false;
    }
}
