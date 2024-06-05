package ru.vsu.cs.aisd.nemchenko_m_e;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;

public class Construct {
    private int numRow, numCol;
    private int[][] labyrinth;

    public Construct(int numCol, int numRow) {
        this.numRow = numRow;
        this.numCol = numCol;

        labyrinth = new int[numRow][numCol];
        for (int i = 0; i < labyrinth.length; i++){
            for (int j = 0; j < labyrinth[0].length; j++){
               labyrinth[i][j] = 0;
            }
        }
    }
    public void printLabyrinth() {
        for (int i = 0; i < labyrinth.length; i++) {
            for (int j = 0; j < labyrinth[0].length; j++) {
                System.out.print(labyrinth[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void readLabyrinthFromFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            int row = 0;
            while ((line = br.readLine()) != null && row < numRow) {
                String[] values = line.trim().split("\\s+");
                for (int col = 0; col < Math.min(values.length, numCol); col++) {
                    labyrinth[row][col] = Integer.parseInt(values[col]);
                }
                row++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

