package com.akknapik.mazesimulator.MazeSolveStrategy;

import com.akknapik.mazesimulator.Cell;
import com.akknapik.mazesimulator.Maze;
import com.akknapik.mazesimulator.MazeSolution;

import java.util.*;

public class BFSMazeSolver extends MazeSolverStrategy implements IMazeSolverStrategy{
    private boolean isSolved = false;

    @Override
    public List<MazeSolution> solveMaze(Maze maze) {
        List<MazeSolution> allSolutions = new ArrayList<>();
        Cell startCell = maze.getStartCell();
        Cell endCell = maze.getEndCell();

        // Sprawdzamy, czy start i end są poprawnie ustawione
        checkStartEnd(startCell, endCell);

        Queue<Cell> queue = new LinkedList<>();
        Map<Cell, Cell> cameFrom = new HashMap<>();
        Set<Cell> visited = new HashSet<>();

        queue.offer(startCell);
        visited.add(startCell);
        cameFrom.put(startCell, null);

        boolean foundSolution = false;

        // BFS - przeszukiwanie w szerz
        while (!queue.isEmpty() && !foundSolution) {
            Cell current = queue.poll();

            // Sprawdzanie sąsiadów
            for (Cell neighbor : maze.getNeighbors(current)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.offer(neighbor);
                    cameFrom.put(neighbor, current);

                    // Jeśli dotarliśmy do komórki końcowej
                    if (neighbor.equals(endCell)) {
                        List<Cell> path = reconstructPath(cameFrom, startCell, endCell);
                        allSolutions.add(new MazeSolution(path, true)); // Dodajemy rozwiązanie
                        foundSolution = true;  // Zatrzymujemy dalsze przetwarzanie
                    } else {
                        // Zapiszemy rozwiązanie (choć nie jest poprawne)
                        List<Cell> path = reconstructPath(cameFrom, startCell, neighbor);
                        allSolutions.add(new MazeSolution(path, false));
                    }
                }
            }
        }

        // Jeśli nie znaleziono żadnego rozwiązania, dodajemy rozwiązanie "brak"
        if (allSolutions.isEmpty()) {
            allSolutions.add(new MazeSolution(new ArrayList<>(), false));
        }

        return allSolutions;
    }

    // Odtwarzanie ścieżki od końca do początku
    private List<Cell> reconstructPath(Map<Cell, Cell> cameFrom, Cell startCell, Cell endCell) {
        List<Cell> path = new ArrayList<>();

        // Przechodzimy wstecz od komórki końcowej
        for (Cell cell = endCell; cell != null; cell = cameFrom.get(cell)) {
            path.add(cell);
        }

        Collections.reverse(path); // Odwracamy ścieżkę, ponieważ była tworzona od końca
        return path;

    }
}
