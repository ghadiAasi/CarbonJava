package com.example.carbonjava;

public class TicTacToeBrains {
    private int [][] board;
    private int player;
    private int computer;
    public static final int NUM = 3; //if you change than change winning
    // X = 1
    // O = 2
    // empty = 0
    // re-used = -1
    public TicTacToeBrains(){
        board = new int[NUM][NUM];
        for (int i = 0; i < NUM; i++) {
        for (int j = 0; j < NUM; j++) {
            board[i][j] = 0;
        }
    }
  }

    public int[][] getBoard() {
        return board;
    }

    public int getPlayer() {
        return player;
    }

    public void setPlayer(int player) {
        this.player = player;
    }

    public int getComputer() {
        return computer;
    }

    public void setComputer(int computer) {
        this.computer = computer;
    }

    public int emptyspace() {
        int emptyspace = 0;
        for (int i = 0; i < NUM; i++) {
            for (int j = 0; j < NUM; j++) {
                if (board[i][j] == 0) {
                    emptyspace++;
                }
            }
        }
        return emptyspace;
    }

    public int emptylocation() {
        for (int i = 0; i < NUM; i++) {
            for (int j = 0; j < NUM; j++) {
                if (board[i][j] == 0) {
                    return i * 10 + j;
                }

            }
        }
        return NUM * 10 + NUM;
    }

    public int[] bestOpintion() {
        int first = 0;
        int second = 0;
        int emptyspace =emptyspace();
        int last = 0;
        int R1=0;
        int R2=0;
        int bestOpintion = 0;
        int opintion = 0;
        for (int i = 0; i < emptyspace; i++) {
            int num = emptyspace - 1;
            int[][] other = this.board;
            for (int j = 0; j < emptyspace; j++) {
                board[emptylocation() / 10][emptylocation() % 10] = computer;
                int[][] check = check(emptyspace - 1);
                for (int l = 0; l < nCr(num, 2); l++) {
                    for (int u = 0; u < NUM; u++) {
                        for (int t = 0; t < NUM; t++) {
                            if (board[u][t] == -1) { // -1 is always the first
                                board[u][t] = check[t][l];
                            }
                        }
                    }
                    for (int t = 0; t < num; t++) {
                        board[emptylocation() / 10][emptylocation() % 10] = check[l][t];
                    }
                opintion += winning(computer);
                opintion -= winning(player);

            this.board = other;
             for(int r=0;r<last;r++) {
                board[emptylocation() % 10][emptylocation() / 10] = -1;
            }
                if (bestOpintion < opintion) {
                    bestOpintion = opintion;
                    first = emptylocation() % 10;
                    second = emptylocation() / 10;
                }

                R1 = emptylocation() % 10;
                R2 = emptylocation() / 10;
                board[R1][R2] = -1;
                last++;
                emptyspace--;
            }
        }
        }
        clean();
        int [] end = new int[2];
        end[0]=first;
        end[1]=second;
        return end;
    }

    //NUM =3
    public int winning(int XO){
        int i=0;
        int t=0;
        int j=0;
        int winning =0;
        while(!(board[i][0] == board[t][1] && board[i][0] == board[j][2]) && board[i][0] == XO){
            if(i<3 && t<3 && j<3) {
                i++;
                t++;
                j++;
            }else{ winning++;}
        }
        while(!(board[0][i] == board[1][t] && board[0][i] == board[2][j]) && board[0][i] == XO){
            if(i<3 && t<3 && j<3) {
                i++;
                t++;
                j++;;
            }else{ winning++;}
        }
        if(!(board[0][0] == board[1][1] && board[0][0] == board[2][2]) && board[0][0] == XO){
            winning++;
        }
        if(!(board[0][2] == board[1][1] && board[0][2] == board[2][0]) && board[0][2] == XO){
            winning++;
        }
        if(winning==(0))
            return 0;// a tie
        else{
            return winning;// possibilty of winning
        }
    }
    public int nCr(int n,int r){
        return fact(n)/(fact(r)*fact(n-r));
    }
    public int fact(int n){
        int res =1;
        for(int i =2; i<=n;i++)
            res = res*i;
        return res;
    }
    //review
    public int[][] check(int num ){
        int n =nCr(num,2);
        int [][] temp = new int[n][num];// 2 for X and O
        for(int i=0; i<n ;i++){
            for(int j=0; j<num ;j++){
                int xo = (int)(Math.random()*2);// 0=o and 1=x
                int num1 = 1;
                if(xo ==0) {
                    num1 = 2;
                }
                temp[i][j]= num1;
            }

            int match= 0;
            if(i>0) {
                for(int t=1;t<=i;t++) {
                    for (int j = 0; j < num; j++) {
                        if (temp[i][j] == temp[i - t][j]) {
                            match++;
                        }
                    }
                }
            }
            int forX =0;
            int forO =0;
                for (int j = 0; j < num; j++) {
                        if (temp[i][j] == 1) {
                            forX++;
                        }
                        if (temp[i][j] == 2) {
                            forO++;
                        }
                }
                if(match == num || (Math.abs(forO-forX)) !=0 || (Math.abs(forO-forX)) != 1)
                    i--;

        }
        return temp;
    }
    public void clean(){
        for (int j = 0; j < NUM; j++) {
            for(int l =0; l<NUM; l++) {
                if(board[j][l] == -1){
                    board[j][l] = 0;
                }
            }
        }
    }
    public boolean UpdateGame(int row, int col){
        if(board[row-1][col-1] == 0){
            board[row-1][col-1] = 1;
            return true;
        }else{
            return false;
        }
    }
    public void UpdateGameComputer(){
        int [] digits = bestOpintion();
        board[digits[0]][digits[1]] = computer;
    }
}
