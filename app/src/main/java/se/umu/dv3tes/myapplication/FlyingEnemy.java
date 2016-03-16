package se.umu.dv3tes.myapplication;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

/**
 * Created by Tobz0r on 2016-03-15.
 */
public class FlyingEnemy extends GameObject implements Enemy {
    private Player player;
    private Bitmap image;
    private Bitmap[] images;
    private int ticks;
    private Animator animator;
    private Handler handler;
    private int health;

    public FlyingEnemy(Player player,Bitmap image,int x, int y, Handler handler,int numFrames,Resources res){
        this.image=image;
        ticks=0;
        this.player=player;
        this.handler=handler;
        animator=new Animator();
        setX(x);
        health=10;
        setWidth(image.getWidth() / 3);
        setHeight(image.getHeight() / 3);
        setVelY(0);
        setVelX(-10);
        images=new Bitmap[numFrames];
        images[0]= BitmapFactory.decodeResource(res, R.drawable.flying1);
        images[1]= BitmapFactory.decodeResource(res, R.drawable.flying2);
        images[2]= BitmapFactory.decodeResource(res, R.drawable.flying3);
        images[3]= BitmapFactory.decodeResource(res, R.drawable.flying4);
        images[4]= BitmapFactory.decodeResource(res, R.drawable.flying5);
        animator.setImages(images);
        animator.setDelay(50);
    }

    @Override
    public void tick() {
        setX(getX() + getVelX());
        setY(getY() + getVelY());
        if (getBounds().intersect(player.getBounds())) {
            setAttacking(true);
            setVelX(0);
        }
        if(health<=0){
            handler.removeObject(this);
        }
        animator.tick();
        if(isAttacking()) {
            ticks++;
            animator.tick();
            if(ticks>=60){
                player.attackThis(1000);
                ticks=0;
            }
        }
    }

    @Override
    public void draw(Canvas canvas) {
        setY(canvas.getHeight()-(getHeight()*3));
        image=animator.getImage();
        canvas.drawBitmap(image, getX(), getY(), null);
    }

    @Override
    public void attackThis() {
        health-=10;
    }
}
