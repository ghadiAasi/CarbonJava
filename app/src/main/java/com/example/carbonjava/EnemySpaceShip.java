package com.example.carbonjava;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.Random;

public class EnemySpaceShip {
    public Context context;
    public Bitmap enemySpaceShip;
    public int ex,ey;
    int enemyVelocity;
    private Random random;

    public EnemySpaceShip(Context context) {
        this.context = context;
        this.enemySpaceShip = BitmapFactory.decodeResource(context.getResources(),R.drawable.rocket2);
        random = new Random();
        this.ex = 200 + random.nextInt(400);
        this.ey = 0;
        enemyVelocity = 14 + random.nextInt(10);
    }

    public Bitmap getEnemySpaceShip() {
        return enemySpaceShip;
    }
    int getEnemySpaceShipWidth(){
        return enemySpaceShip.getWidth();
    }
    public int getEnemySpaceShipHeight(){
        return enemySpaceShip.getHeight();
    }
}
