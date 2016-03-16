package se.umu.dv3tes.myapplication;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;

/**
 * Created by Tobz0r on 2016-03-15.
 */
public class BasicEnemy extends GameObject implements Enemy {

    private Player player;
    private Bitmap image;
    private Bitmap[] images;
    private Handler handler;
    private int health;
    private Animator animator;
    private int numFrames;
    private long startTime;
    private int ticks;


    public BasicEnemy(Player player,Bitmap image,int x, int y, Handler handler,int numFrames,Resources res){
        this.image=image;
        health=20;
        ticks=0;
        setAttacking(false);
        this.handler=handler;
        this.player=player;
        setX(x);
        setWidth(image.getWidth() / 3);
        setHeight(image.getHeight() / 3);
        setVelY(0);
        setVelX(-20);
        animator=new Animator();
        images=new Bitmap[numFrames];
        for(int i=0; i < images.length;i++){
            //  images[i] = Bitmap.createBitmap(image,i*width,0,width,height);
            images[i]=this.image;
        }
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

    public boolean isAlive(){
        return health<=0;
    }

    @Override
    public void draw(Canvas canvas) {
        setY(canvas.getHeight()-(getHeight()*3));
        image=animator.getImage();
        canvas.drawBitmap(image, getX(), getY(), null);
    }

    @Override
    public void attackThis() {
        health-=20;
    }

    public String toString(){
        return "BasicEnemy";
    }
}
