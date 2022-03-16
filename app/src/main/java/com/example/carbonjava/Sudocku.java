package com.example.carbonjava;

import java.util.ArrayList;
import java.util.Objects;

public class Sudocku {

    public int selected_row;
    public int selected_column;
    private int[][] sudoku;
    ArrayList<ArrayList<Object>> emptyBoxIndex;
    public final int LINE = 9; // LINE have to be a number that result in int after square


    public Sudocku() {
        selected_row =-1;
        selected_column =-1;
        //fill empty space
        sudoku = new int[LINE][LINE];
        for (int i = 0; i < LINE; i++) {
            for (int j = 0; j < LINE; j++) {
                sudoku[i][j] = 0;
            }
        }
        emptyBoxIndex = new ArrayList<>();
    }

    public void setEmptyBoxIndex() {
        for(int r=0;r<sudoku.length;r++){
            for(int c=0;c< sudoku.length;c++){
                if(this.sudoku[r][c] == 0) {
                    this.emptyBoxIndex.add(new ArrayList<>());
                    this.emptyBoxIndex.get(this.emptyBoxIndex.size() - 1).add(r);
                    this.emptyBoxIndex.get(this.emptyBoxIndex.size() - 1).add(c);
                }
            }
        }
    }
    protected void setNumberPos(int num){
        if(this.selected_row != -1 && this.selected_column != -1){
            if(this.sudoku[this.selected_row-1][this.selected_column-1] == num){
                this.sudoku[this.selected_row-1][this.selected_column-1] = 0;
            }
            else{
                this.sudoku[this.selected_row-1][this.selected_column-1] = num;
            }
        }
    }
    public void setEmptyBoxIndex(ArrayList<ArrayList<Object>> emptyBoxIndex) {
        this.emptyBoxIndex = emptyBoxIndex;
    }
    public ArrayList<ArrayList<Object>> getEmptyBoxIndex(){
        return this.emptyBoxIndex;
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
    public int[][] getSudoku() {
        return sudoku;
    }
}
