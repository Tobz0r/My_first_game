package se.umu.dv3tes.myapplication.GameObjects.Projectiles;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import se.umu.dv3tes.myapplication.GameObjects.GameObject;
import se.umu.dv3tes.myapplication.GameLogic.Handler;
import se.umu.dv3tes.myapplication.Position;
import se.umu.dv3tes.myapplication.GameObjects.Player.Player;



/**
 * Created by Tobz0r on 2016-03-15.
 */
public class SuperProjectile extends GameObject implements Projectile {

    private int targetX,targetY;
    private Bitmap image;
    private Player player;
    private Position position;
    private Handler handler;

    public SuperProjectile(Player player,Bitmap image,int targetX, int targetY, Handler handler){
        this.image=image;
        this.targetY=targetY;
        this.targetX=targetX;
        this.player=player;
        setHeight(image.getHeight()/3);
        setWidth(image.getWidth()/3);
        setX(player.getX());
        setY(player.getY());
        this.handler=handler;
    }

    @Override
    public void tick() {

        float deltaX = getX() - targetX;
        float deltaY = getY() - targetY;
        float distance = (float) Math.sqrt((deltaX * deltaX) + (deltaY * deltaY));
        setVelX((float) ((-5.0 / distance) * deltaX));
        setVelY((float) ((-5.0 / distance) * deltaY));
        setX(getX() + getVelX());
        setY(getY() + getVelY());
        if ((getX() > targetX - getWidth()) && (targetX + getWidth() > getX())) {
            if ((getY() > targetY - getHeight()) && (targetY + getHeight() > getY())) {
                handler.removeObject(this);
            }
        }
    }
    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(image, getX(),getY(), null);
    }


    @Override
    public boolean isHostile() {
        return false;
    }

    @Override
    public void finishProjectile() {
        image.recycle();
        image=null;
    }
}