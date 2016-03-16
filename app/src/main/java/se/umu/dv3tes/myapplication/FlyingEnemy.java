package se.umu.dv3tes.myapplication;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by Tobz0r on 2016-03-15.
 */
public class FlyingEnemy extends GameObject implements Enemy {
    private Player player;
    private Bitmap image;
    private Handler handler;
    private int health;

    public FlyingEnemy(Player player,Bitmap image,int x, int y, Handler handler){
        this.image=image;
        this.player=player;
        this.handler=handler;
        setX((float)x);
        setY((float) y);
        health=10;
        setWidth(image.getWidth());
        setHeight(image.getHeight());
        setVelY(0);
        setVelX(-10);
    }

    @Override
    public void tick() {
        setX(getX()+getVelX());
        setY(getY()+getVelY());

    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(image, getX()+getWidth(), getY()+getHeight(), null);
    }

    @Override
    public void attackThis() {
        health-=10;
    }
}
