package com.akknapik.mazesimulator.MazeSolveStrategy;

import com.akknapik.mazesimulator.MazeGenerateStrategy.*;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class MazeSolverFactory implements IMazeSolverFactory{
    private Map<String,Class<? extends IMazeSolverStrategy>> registeredStrategies = new HashMap<>();

    public MazeSolverFactory() {
        registerStrategy("dfs", DFSMazeSolver.class);
        registerStrategy("bfs", BFSMazeSolver.class);
        registerStrategy("righthand", RightHandSolver.class);
        //registerStrategy("", .class);

    }

    public void registerStrategy(String type, Class<? extends IMazeSolverStrategy> strategy) {
        registeredStrategies.put(type.toLowerCase(), strategy);
    }

    @Override
    public IMazeSolverStrategy createStrategy(String type) {
        Class<? extends IMazeSolverStrategy> strategy = registeredStrategies.get(type.toLowerCase());

        if(strategy == null) {
            throw new IllegalArgumentException("Unknown maze solver type: " + type);
        }

        try {
            return strategy.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
