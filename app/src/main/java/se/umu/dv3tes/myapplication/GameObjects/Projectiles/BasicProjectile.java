package se.umu.dv3tes.myapplication.GameObjects.Projectiles;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.widget.Button;

import se.umu.dv3tes.myapplication.GameObjects.GameObject;
import se.umu.dv3tes.myapplication.GameLogic.Handler;
import se.umu.dv3tes.myapplication.GameObjects.Position;
import se.umu.dv3tes.myapplication.GameObjects.Player.Player;



/**
 * Credit for the sprites goes to Boo at www.spriters-resource.com/
 */
public class BasicProjectile extends GameObject implements Projectile {

    private int targetX,targetY;
    private static Bitmap image;
    private static Bitmap hostileImage;
    private Bitmap projectileImage;
    private Player player;
    private GameObject shooter;
    private Handler handler;
    private boolean hostileProjectile=false;

    public BasicProjectile(Player player,int targetX, int targetY, Handler handler, GameObject shooter){
        this.targetY=targetY;
        this.targetX=targetX;
        this.player=player;
        this.shooter=shooter;
        setHeight(image.getHeight() / 3);
        setWidth(image.getWidth() / 3);
        setX(shooter.getX());
        setY(shooter.getY());
        this.handler=handler;
        projectileImage=image;
        if(targetX==player.getX() && targetY==player.getY()){
            hostileProjectile=true;
            projectileImage=hostileImage;
        }
    }

    @Override
    public void tick() {

        float deltaX = getX() - targetX;
        float deltaY = getY() - targetY;
        float distance = (float) Math.sqrt((deltaX * deltaX) + (deltaY * deltaY));
        setVelX((float) ((-10.0 / distance) * deltaX));
        setVelY((float) ((-10.0 / distance) * deltaY));
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
        canvas.drawBitmap(projectileImage, getX(), getY(), null);
    }

    public void finishProjectile(){

    }

    public static void setPicture(Bitmap imagePara){
        image=imagePara;
    }
    public static void setHostileImage(Bitmap imagepara){
        hostileImage=imagepara;
    }

    @Override
    public boolean isHostile() {
        return hostileProjectile;
    }
}
