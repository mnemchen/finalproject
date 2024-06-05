package ru.vsu.cs.aisd.nemchenko_m_e;

public class Main {
    public static void main(String[] args) {
        int[][] labyrinth = new int[5][5];
        Algorithms.printArray(Algorithms.makeWaveArray(labyrinth, new int[]{0,0}));
    }
}
