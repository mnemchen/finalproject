package ru.vsu.cs.aisd.nemchenko_m_e;
public class PathFinder {
    private int mazeSize;
    private static final int WALL = 1;
    private static final int PATH = 2;

    public PathFinder(int mazeSize) {
        this.mazeSize = mazeSize;
    }

    private static int[][] copyMaze(int[][] maze, int mazeSize) {
        int[][] newMaze = new int[mazeSize][mazeSize];
        for (int i = 0; i < mazeSize; i++) {
            System.arraycopy(maze[i], 0, newMaze[i], 0, mazeSize);
        }
        return newMaze;
    }

    public int[][] findPathArray(int[][] maze, int row, int col) {
        if (row < 0 || row >= mazeSize || col < 0 || col >= mazeSize ||
                maze[row][col] == WALL || maze[row][col] == PATH) {
            return null;
        }

        int[][] tempMaze = copyMaze(maze, mazeSize);
        tempMaze[row][col] = PATH;

        if (row == mazeSize - 1 && col == mazeSize - 1) {
            return tempMaze;
        }

        int[][] rightPath = findPathArray(tempMaze, row + 1, col);
        int[][] downPath = findPathArray(tempMaze, row, col + 1);
        int[][] leftPath = findPathArray(tempMaze, row - 1, col);
        int[][] upPath = findPathArray(tempMaze, row, col - 1);

        if (rightPath != null) {
            return rightPath;
        } else if (downPath != null) {
            return downPath;
        } else if (leftPath != null) {
            return leftPath;
        } else if (upPath != null) {
            return upPath;
        } else {
            return null;
        }
    }
}