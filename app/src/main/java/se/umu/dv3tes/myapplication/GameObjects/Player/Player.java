package se.umu.dv3tes.myapplication.GameObjects.Player;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import se.umu.dv3tes.myapplication.Model.Animator;
import se.umu.dv3tes.myapplication.GameObjects.GameObject;
import se.umu.dv3tes.myapplication.Powerups.Powerups;
import se.umu.dv3tes.myapplication.R;

/**
 * Created by Tobz0r on 2016-03-15.
 */
public class Player extends GameObject {
    private Bitmap image;
    private Bitmap[] images;
    private int score;
    private float health;
    private long startTime;
    private Animator animator;
    private Powerups powerup;
    private boolean gotPower=false;
    private int powerTicks=0;

    public Player(Bitmap image, int width, int height, int numFrames,Resources res){
        animator=new Animator();
        this.image=image;
        score=0;
        health=START_HEALTH;
        setHeight(image.getHeight()/3);
        setWidth(image.getWidth() / 3);
        setX(10);
        images=new Bitmap[numFrames];
        images[0]=BitmapFactory.decodeResource(res, R.drawable.player1);
        images[1]=BitmapFactory.decodeResource(res, R.drawable.player2);
        images[2]=BitmapFactory.decodeResource(res, R.drawable.player3);
        images[3]=BitmapFactory.decodeResource(res, R.drawable.player4);
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
        if(gotPower){
            powerTicks++;
            if(powerTicks>=600){
                powerup=Powerups.NONE;
                gotPower=false;
            }
        }

    }

    @Override
    public void draw(Canvas canvas){
        setY(canvas.getHeight() - (getHeight() * 3));
        Paint myPaint=new Paint();
        myPaint.setColor(Color.BLUE);
        canvas.drawRect(getBounds(),myPaint);
        canvas.drawBitmap(animator.getImage(),getX(),getY(),null);
    }

    public void attackThis(int damage){
        if(powerup==Powerups.DEFENSE){
            damage/=2;
        }
        health-=damage;
    }
    public int getScore(){
        return score;
    }
    public void addScore(int score){
        this.score+=score;
    }
    public float getHealth(){
        return health;
    }
    public boolean isAlive(){
        return health>0;
    }
    public void powerUp(Powerups powerups){
        this.powerup=powerups;
        gotPower=true;
        powerTicks=0;
    }
    public Powerups getPowerup(){
        return powerup;
    }
    public boolean isGotPower(){
        return gotPower;
    }

}
