package ru.vsu.cs.aisd.nemchenko_m_e;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MazeSolverApp extends JFrame {
    private static final int MAZE_SIZE = 5;
    private static final int EMPTY = 0;
    private static final int WALL = 1;
    private static final int PATH = 2;

    private JPanel mazePanel;
    private JButton solveButton;
    private JButton resetButton;
    private JLabel[][] mazeLabels;
    private int[][] mazeGrid;

    public MazeSolverApp() {
        setTitle("Лабиринт");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLayout(new BorderLayout());

        mazePanel = new JPanel(new GridLayout(MAZE_SIZE, MAZE_SIZE));
        solveButton = new JButton("Решить");
        resetButton = new JButton("Сбросить");

        createMaze();
        add(mazePanel, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(solveButton);
        buttonPanel.add(resetButton);
        add(buttonPanel, BorderLayout.SOUTH);

        solveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                solveMaze();
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetMaze();
            }
        });
    }

    private void createMaze() {
        mazeLabels = new JLabel[MAZE_SIZE][MAZE_SIZE];
        mazeGrid = new int[MAZE_SIZE][MAZE_SIZE];

        for (int row = 0; row < MAZE_SIZE; row++) {
            for (int col = 0; col < MAZE_SIZE; col++) {
                mazeLabels[row][col] = new JLabel();
                mazeLabels[row][col].setOpaque(true);
                mazeLabels[row][col].setBackground(Color.WHITE);
                mazeLabels[row][col].setPreferredSize(new Dimension(50, 50));
                mazeLabels[row][col].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                mazeLabels[row][col].addMouseListener(new MazeMouseListener(row, col));
                mazePanel.add(mazeLabels[row][col]);
            }
        }
    }

    private void solveMaze() {
        PathFinder pathFinder = new PathFinder(MAZE_SIZE);
        int[][] pathResult = pathFinder.findPathArray(mazeGrid, 0, 0);
        if(pathResult == null) {
            JOptionPane.showMessageDialog(this, "Путь не найден!");
        } else {
            updateMazeWithPath(pathResult);
            JOptionPane.showMessageDialog(this, "Путь найден!");
        }
    }

    private void updateMazeWithPath(int[][] pathResult) {
        for (int row = 0; row < MAZE_SIZE; row++) {
            for (int col = 0; col < MAZE_SIZE; col++) {
                if (pathResult[row][col] == MazeSolverApp.PATH) {
                    mazeLabels[row][col].setBackground(Color.GREEN);
                }
            }
        }
    }

    private void resetMaze() {
        for (int row = 0; row < MAZE_SIZE; row++) {
            for (int col = 0; col < MAZE_SIZE; col++) {
                mazeGrid[row][col] = EMPTY;
                mazeLabels[row][col].setBackground(Color.WHITE);
            }
        }
    }

    private class MazeMouseListener extends MouseAdapter {
        private int row;
        private int col;

        public MazeMouseListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (SwingUtilities.isLeftMouseButton(e)) {
                mazeLabels[row][col].setBackground(Color.GRAY);
                mazeGrid[row][col] = WALL;
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MazeSolverApp mazeSolverApp = new MazeSolverApp();
                mazeSolverApp.setVisible(true);
            }
        });
    }
}
