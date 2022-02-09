package com.example.carbonjava;

public class Sudocku {
    private int[][] sudoku;
    public final int LINE = 9; // LINE have to be a number that result in int after square

    public Sudocku() {
        //fill empty space
        sudoku = new int[LINE][LINE];
        for (int i = 0; i < LINE; i++) {
            for (int j = 0; j < LINE; j++) {
                sudoku[i][j] = ((int) Math.random() * LINE) + 1;
            }

        }
        works();
    }
    //making the numbers from 1-9
    public void works() {
        int n = 0;
        int t = 0;
        int e = 0;
        for (int x = 0; x < LINE; x++) {
            for (int i = 0; i < LINE; i++) {
                // checking the line horizontal
                for (int j = 0; j < LINE; j++) {
                        while (sudoku[n][i] == sudoku[i][j]) {
                            sudoku[i][j] = ((int) (Math.random() * 9) + 1);
                        }
                }
                // checking the line vertical
                for (int j = 0; j < LINE; j++) {
                        while (sudoku[i][t] == sudoku[j][i]) {
                            sudoku[j][i] = ((int) (Math.random() * 9) + 1);
                        }
                }
                //the square 3*3
                int w = e * (int) (Math.sqrt(LINE));
                // checking the little squares
                int count=-1;
                int l=i;
                for (int j = 0; j < (int) (Math.sqrt(LINE)); j++) {
                    if (sudoku[i][w] == sudoku[i][w+j]) {
                        while (sudoku[i][w] == sudoku[i][j + w]) {
                            sudoku[i][j + w] = ((int) (Math.random() * 9) + 1);
                        }
                        if(count != (int)Math.sqrt(LINE)){
                            j=0;
                            i++;
                            count++;
                        }
                    }
                }
                i=l;
            }
            if (e == (int) (Math.sqrt(LINE))){
                e= 0;
            }
            e++;
            t++;
            n++;
        }
    }
}
