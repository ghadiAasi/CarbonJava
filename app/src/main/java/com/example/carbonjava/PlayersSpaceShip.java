package com.example.carbonjava;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.Random;

public class PlayersSpaceShip {
    private Context context;
    private Bitmap PlayersSpaceShip;
    public int ox,oy;
    private Random random;

    public PlayersSpaceShip(Context context) {
        this.context = context;
        PlayersSpaceShip = BitmapFactory.decodeResource(context.getResources(),R.drawable.rocket1);
        this.ox = random.nextInt(SpaceShooter.screenWidth);
        this.oy = SpaceShooter.screenHeight - PlayersSpaceShip.getHeight();
        this.random = new Random();
    }

    public Bitmap getPlayersSpaceShip() {
        return PlayersSpaceShip;
    }
    int getPlayerSpaceShipWidth(){
        return PlayersSpaceShip.getWidth();
    }
}
