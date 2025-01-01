package com.akknapik.mazesimulator.MazeGenerateStrategy;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class MazeGeneratorFactory implements IMazeGeneratorFactory {
    private Map<String,Class<? extends IMazeGeneratorStrategy>> registeredStrategies = new HashMap<>();

    public MazeGeneratorFactory() {
        registerStrategy("dfs", DFSMazeGenerator.class);
        registerStrategy("kruskal", KruskalMazeGenerator.class);

    }

    public void registerStrategy(String type, Class<? extends IMazeGeneratorStrategy> strategy) {
        registeredStrategies.put(type.toLowerCase(), strategy);
    }

    @Override
    public IMazeGeneratorStrategy createStrategy(String type) {
        Class<? extends IMazeGeneratorStrategy> strategy = registeredStrategies.get(type.toLowerCase());

        if(strategy == null) {
            throw new IllegalArgumentException("Unknown maze generation type: " + type);
        }

        try {
            return strategy.getDeclaredConstructor().newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
