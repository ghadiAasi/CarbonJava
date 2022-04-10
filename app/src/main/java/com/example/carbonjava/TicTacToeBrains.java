package com.example.carbonjava;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TicTacToeBrains {
    private int[][] board;
    private Button playAgainBtn;
    private Button homeBtn;
    private TextView playerTurn;
    private TextView countWinX;
    private TextView countWinY;
    private int XcountWin;
    private int YcountWin;

    private boolean tieGame = false;

    private int[] winType={-1,-1,-1};//the two digit of the winning place and it's kind
    public int player =1;

    public TicTacToeBrains() {
        board = new int[3][3];
        for (int r = 0; r < 3; r++)
            for (int c = 0; c < 3; c++)
                board[r][c] = 0;
        XcountWin =0;
        YcountWin =0;
    }

    public void resetGame() {
        for (int r = 0; r < 3; r++)
            for (int c = 0; c < 3; c++)
                board[r][c] = 0;

            player =1;

            playAgainBtn.setVisibility(View.GONE);
            homeBtn.setVisibility(View.GONE);

            playerTurn.setText("TicTacToe game");
    }

    public boolean isfull() {
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (board[r][c] == 0) {
                    return false;
                }
            }
        }
        return true;
    }
    public boolean winning(){
        boolean winner=false;

        for(int i=0;i<3;i++) {
            if (board[i][0] == board[i][1] && board[i][0] == board[i][2] && board[i][0] != 0) {
                winType =new int[]{i,0,1};
                winner = true;
                if(board[i][0] == 1){
                    XcountWin++;
                    countWinX.setText("X wins: "+XcountWin);
                }
                else{
                    YcountWin++;
                    countWinY.setText("O wins: "+YcountWin);
                }
            }
        }
        for(int i=0;i<3;i++) {
            if(board[0][i] == board[1][i] && board[0][i] == board[2][i] && board[0][i]!=0){
                winType =new int[]{0,i,2};
                winner=true;
                if(board[0][i] == 1){
                    XcountWin++;
                    countWinX.setText("X wins: "+XcountWin);
                }
                else{
                    YcountWin++;
                    countWinY.setText("O wins: "+YcountWin);
                }
            }
        }
        if(board[0][0] == board[1][1] && board[0][0] == board[2][2] && board[0][0]!=0){
            winType =new int[]{0,2,3};
            winner=true;
            if(board[0][0] == 1){
                XcountWin++;
                countWinX.setText("X wins: "+XcountWin);
            }
            else{
                YcountWin++;
                countWinY.setText("O wins: "+YcountWin);
            }
        }
        if(board[2][0] == board[1][1] && board[2][0] == board[0][2] && board[2][0]!=0){
            winType =new int[]{2,2,4};
            winner=true;
            if(board[2][0] == 1){
                XcountWin++;
                countWinX.setText("X wins: "+XcountWin);
            }
            else{
                YcountWin++;
                countWinY.setText("O wins: "+YcountWin);
            }
        }
        if(winner){
            playAgainBtn.setVisibility(View.VISIBLE);
            homeBtn.setVisibility(View.VISIBLE);
            if(player == 1) {
                playerTurn.setText( "X Won!");
            }
            if(player == 2) {
                playerTurn.setText( "O Won!");
            }
            this.tieGame= false;
            return true;
        }
        else if(isfull()){
            playAgainBtn.setVisibility(View.VISIBLE);
            homeBtn.setVisibility(View.VISIBLE);
            playerTurn.setText("Tie Game!");
            this.tieGame= true;
            return true;
        }
        else{
            return false;
        }

    }

    public boolean isTieGame() {
        return tieGame;
    }

    public int[][] getGameBoard() {
        return board;
    }

    public int getPlayer() {
        return this.player;
    }

    public int[][] getBoard() {
        return board;
    }
    public Button getPlayAgainBtn() {
        return playAgainBtn;
    }

    public void setPlayAgainBtn(Button playAgainBtn) {
        this.playAgainBtn = playAgainBtn;
    }

    public int[] getWinType() {
        return winType;
    }

    public void setPlayer(int player) {
        this.player = player;
    }

    public Button getHomeBtn() {
        return homeBtn;
    }

    public void setHomeBtn(Button homeBtn) {
        this.homeBtn = homeBtn;
    }
    public TextView getPlayerTurn() {
        return playerTurn;
    }
    public void setPlayerTurn(TextView playerTurn) {
        this.playerTurn = playerTurn;
    }

    public void setCountWinY(TextView countWinY) {
        this.countWinY = countWinY;
    }

    public void setCountWinX(TextView countWinX) {
        this.countWinX = countWinX;
    }

    public boolean updateGamePlayer1(int row, int col){
        if(board[row-1][col-1] == 0){
            board[row-1][col-1] = player;
            return true;
        }else{
            return false;
        }
    }
}
