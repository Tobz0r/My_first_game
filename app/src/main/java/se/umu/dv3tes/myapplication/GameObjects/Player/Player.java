package se.umu.dv3tes.myapplication.GameObjects.Player;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import se.umu.dv3tes.myapplication.View.Animator;
import se.umu.dv3tes.myapplication.GameObjects.GameObject;
import se.umu.dv3tes.myapplication.Powerups.Powerups;
import se.umu.dv3tes.myapplication.R;

/**
 * Player is what the user is using, handles
 * all the attacks and how much score and health
 * the player got
 * @author Tobias Estefors
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


    /**
     * Updates the gamestate for this gameobject
     */
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

    /**
     * Used to draw this player on the board
     * @param canvas the board
     */
    @Override
    public void draw(Canvas canvas){
        setY(canvas.getHeight() - (getHeight() * 3));
        canvas.drawBitmap(animator.getImage(),getX(),getY(),null);
    }

    /**
     * Does damage on the player if hit
     * @param damage the damage
     */
    public void attackThis(int damage){
        if(powerup==Powerups.DEFENSE){
            damage/=2;
        }
        health-=damage;
    }

    /**
     * Returns how much score the player has
     * @return the score
     */
    public int getScore(){
        return score;
    }

    /**
     * Increses the score of the player
     * @param score
     */
    public void addScore(int score){
        this.score+=score;
    }

    /**
     * Returns how much health you have left
     * @return the health
     */
    public float getHealth(){
        return health;
    }

    /**
     * Checks if player is still alive
     * @return true if health above zero else false
     */
    public boolean isAlive(){
        return health>0;
    }

    /**
     * Used to powerup the player if a powerup is killed
     * @param powerups the powerup
     */
    public void powerUp(Powerups powerups){
        this.powerup=powerups;
        gotPower=true;
        powerTicks=0;
    }

    /**
     * Returns wich powerup you got
     * @return the powerup
     */
    public Powerups getPowerup(){
        return powerup;
    }

    /**
     * Checks if player is powered up
     * @return true if powered up, else false
     */
    public boolean isGotPower(){
        return gotPower;
    }

}
