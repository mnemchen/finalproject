package ru.vsu.cs.aisd.nemchenko_m_e;

import java.util.Arrays;

class Algorithms {
    public static void printArray(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.println(Arrays.toString(array[i]));
        }
    }
    public static int[][] makeWaveArray(int[][] lab, int[] start) {

        int startRow = start[0];
        int startCol = start[1];
        int[][] waveArray = new int[lab.length][lab[0].length];
        int currNum = 0;
        int maxCurr = 5;

        if (checkRules(lab, start)) {
            while (maxCurr != currNum) {
                if (startRow + currNum < lab.length) {
                    for (int j = Math.abs(startCol - currNum); j <= startCol + currNum - 1; j++) {
                        waveArray[startRow + currNum][j] = currNum;
                    }
                }

                if (startRow - currNum > 0) {
                    for (int j = startCol - currNum; j <= startCol + currNum - 1; j++) {
                        waveArray[startRow - currNum][j] = currNum;
                    }
                }

                if (startCol + currNum < lab[0].length) {
                    for (int i = Math.abs(startRow - currNum); i <= startRow + currNum - 1; i++) {
                        waveArray[i][startCol + currNum] = currNum;
                    }
                }

                if (startCol - currNum > 0) {
                    for (int i = startRow - currNum; i <= startRow + currNum - 1; i++) {
                        waveArray[i][startCol - currNum] = currNum;
                    }
                }
                currNum++;
            }
        } else {
            System.out.println("Ошибка в координатах ячейки");
        }
        return waveArray;
    }

    private boolean canItBeenNext(int[][] lab, int[] nextCell) {
        if (lab[nextCell[0]][nextCell[1]] == 0) {
            return true;
        }
        return false;
    }

    private boolean canWent(int nextCell) {
        if (nextCell == 0) {
            return true;
        }
        return false;
    }

    private static boolean checkRules(int[][] lab, int[] start) {
        if (start[0] > lab.length || start[1] > lab[0].length
                || start[0] < 0 || start[1] < 0 || lab[start[0]][start[1]] == 1) {
            return false;
        }
        return true;
    }
}
