package com.example.carbonjava;

public class Game {
    private String descraption;
    private int resid; // image id to be loaded
    private boolean isHappy;
    private int amount;

    public Game(String descraption, int resid, boolean isHappy, int amount) {
        this.descraption = descraption;
        this.resid = resid;
        this.isHappy = isHappy;
        this.amount = amount;
    }

    public void setDescraption(String descraption) {
        this.descraption = descraption;
    }

    public int getResid() {
        return resid;
    }

    public void setResid(int resid) {
        this.resid = resid;
    }

    public boolean isHappy() {
        return isHappy;
    }

    public void setHappy(boolean happy) {
        isHappy = happy;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDescraption() {
        return descraption;
    }
}
