package com.example.carbonjava;

import java.util.ArrayList;
import java.util.Objects;

public class Sudocku {

    public int selected_row;
    public int selected_column;
    private int[][] sudoku;
    private int[][] sudokuIn;
    private int mistakes;
    ArrayList<ArrayList<Object>> emptyBoxIndex;
    ArrayList<ArrayList<Object>> emptyBoxIndexMistakes;


    public Sudocku() {
        selected_row =-1;
        selected_column =-1;
        //fill empty space
        sudoku = new int[9][9];
        sudokuIn = new int[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sudoku[i][j] = 0;
                sudokuIn[i][j] =0;
            }
        }
        addRandomNumbers();
        makeSudoku();
        emptyBoxIndex = new ArrayList<>();
        emptyBoxIndexMistakes = new ArrayList<>();
    }

    private boolean check(int row,int col){
        if(this.sudokuIn[row][col]>0){
            for (int i =0;i<9;i++){
                if(sudokuIn[i][col] == sudokuIn[row][col] && row!=i){
                    return false;
                }
                if(sudokuIn[row][i] == sudokuIn[row][col] && col!=i){
                    return false;
                }
            }
            int boxRow = row/3;
            int boxCol = col/3;

            for(int r =boxRow*3;r<boxRow*3+3;r++){
                for(int c=boxCol*3;c<boxCol*3+3;c++){
                    if(sudokuIn[r][c] == sudokuIn[row][col] && r!=row && c!=col){
                        return false;
                    }
                }
            }
            return true;
        }
        return true;
    }

    protected boolean makeSudoku(){
        int row =-1;
        int col =-1;
        for(int r =0;r<9;r++){
            for(int c=0;c<9;c++){
                if(sudokuIn[r][c] == 0){
                    row =r;
                    col=c;
                    break;
                }
            }
        }
        if(row == -1 || col == -1){
            return true;
        }
        for(int i =1;i<10;i++){
            sudokuIn[row][col] = i;

            if(check(row,col)){
                if(makeSudoku()){
                    return true;
                }
            }
            sudokuIn[row][col]=0;
        }
        return false;
    }
    public void addRandomNumbers(){
        for (int c=0;c<6;c++){
            this.sudokuIn[0][c] = (int)(Math.random()*9)+1;
            if(!check(0,c)){
                this.sudokuIn[0][c] =0;
            }
        }
        for (int r=1;r<6;r++){
            this.sudokuIn[r][0] = (int)(Math.random()*9)+1;
            if(!check(r,0)){
                this.sudokuIn[r][0] =0;
            }
        }
    }
    public void restartGame(){
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sudoku[i][j] = 0;
                sudoku[i][j] =0;
            }
        }
        this.emptyBoxIndex = new ArrayList<>();
    }
    protected void setNumberPos(int num){
        if(this.selected_row != -1 && this.selected_column != -1) {
            if ( this.sudoku[this.selected_row-1][this.selected_column-1] == 0) {
                if (this.sudokuIn[this.selected_row - 1][this.selected_column - 1] == num) {
                    this.emptyBoxIndex.add(new ArrayList<>());
                    this.emptyBoxIndex.get(this.emptyBoxIndex.size() - 1).add(this.selected_row - 1);
                    this.emptyBoxIndex.get(this.emptyBoxIndex.size() - 1).add(this.selected_column - 1);
                    this.sudoku[this.selected_row - 1][this.selected_column - 1] = num;
                } else {
                    this.emptyBoxIndexMistakes.add(new ArrayList<>());
                    this.emptyBoxIndexMistakes.get(this.emptyBoxIndexMistakes.size() - 1).add(this.selected_row - 1);
                    this.emptyBoxIndexMistakes.get(this.emptyBoxIndexMistakes.size() - 1).add(this.selected_column - 1);
                    mistakes++;
                    this.sudoku[this.selected_row - 1][this.selected_column - 1] = num;
                }
            }
            else{
                if(this.sudoku[this.selected_row-1][this.selected_column-1] == num && this.sudoku[this.selected_row-1][this.selected_column-1] != this.sudokuIn[this.selected_row-1][this.selected_column-1]){
                    this.sudoku[this.selected_row-1][this.selected_column-1] =0;
                    this.emptyBoxIndexMistakes.remove(this.emptyBoxIndexMistakes.size() - 1);
                }
            }
        }
    }

    public void setEmptyBoxIndex(ArrayList<ArrayList<Object>> emptyBoxIndex) {
        this.emptyBoxIndex = emptyBoxIndex;
    }
    public int getSelectedRow() {
        return selected_row;
    }

    public int getSelectedColumn() {
        return selected_column;
    }
    public void setSelectedRow(int selected_row) {
        this.selected_row = selected_row;
    }

    public void setSelectedColumn(int selected_colum) {
        this.selected_column = selected_colum;
    }
    public int[][] getSudokuIn() {
        return sudokuIn;
    }

    public int[][] getSudoku() {
        return sudoku;
    }

    public void setSudokuIn(int[][] sudokuSee) {
        this.sudokuIn = sudokuSee;
    }
    public ArrayList<ArrayList<Object>> getEmptyBoxIndex() {
        return emptyBoxIndex;
    }
    public ArrayList<ArrayList<Object>> getEmptyBoxIndexMistakes() {
        return emptyBoxIndexMistakes;
    }

    public void showSudockuSee() {

        for(int r =0 ;r<9;r++){
            for(int c=0 ;c<9;c++){
               if(((int)(Math.random()*12))%2 == 0){
                    this.sudoku[r][c] = this.sudokuIn[r][c];
                }
            }
        }
    }
}
