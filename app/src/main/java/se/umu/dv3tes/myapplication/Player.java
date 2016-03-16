package se.umu.dv3tes.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;

/**
 * Created by Tobz0r on 2016-03-15.
 */
public class Player extends GameObject {
    private Bitmap image;
    private Bitmap[] images;
    private int score;
    private int health;
    private long startTime;
    private Animator animator;

    public Player(Bitmap image, int width, int height, int numFrames){
        animator=new Animator();
        this.image=image;
        score=0;
        health=2000;
        setHeight(image.getHeight()/3);
        setWidth(image.getWidth()/3);
        setX(10);
        images=new Bitmap[numFrames];
        for(int i=0; i < images.length;i++){
          //  images[i] = Bitmap.createBitmap(image,i*width,0,width,height);
            images[i]=this.image;
        }
        animator.setImages(images);
        animator.setDelay(10);
        startTime=System.nanoTime();
    }


    @Override
    public void tick() {
        long elapsedTime = (System.nanoTime()-startTime)/ 1000000;
        if(elapsedTime>100){
            score++;
            startTime=System.nanoTime();
        }
        if(isAttacking()){
            animator.tick();
        }

    }

    @Override
    public void draw(Canvas canvas){
        setY(canvas.getHeight()-(getHeight()*3));
        canvas.drawBitmap(animator.getImage(),getX(),getY(),null);
    }

    public void attackThis(int damage){
        health-=damage;
    }
    public int getScore(){
        return score;
    }
    public int getHealth(){
        return health;
    }
    public boolean isAlive(){
        return health>0;
    }

}
