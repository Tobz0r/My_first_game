package se.umu.dv3tes.myapplication.GameObjects.Enemies;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import se.umu.dv3tes.myapplication.GameLogic.Handler;
import se.umu.dv3tes.myapplication.GameObjects.GameObject;
import se.umu.dv3tes.myapplication.GameObjects.Player.Player;
import se.umu.dv3tes.myapplication.R;
import se.umu.dv3tes.myapplication.View.Animator;

/**
 * Boss that spawns every 25th round that can take
 * more damage and does more damage
 * @author Tobias Estefors
 */
public class Boss extends GameObject implements Enemy{
    private Player player;
    private Bitmap image;
    private static Bitmap[] movingImages;
    private static Bitmap[] attackingImages;
    private int ticks;
    private Animator animator;
    private Handler handler;
    private int health;
    private Resources res;
    private boolean spawned=true;
    private final int damage=8;

    public Boss(Player player, Bitmap image, int x, int y, Handler handler, int numFrames, Resources res){
        this.image=image;
        ticks=0;
        this.res=res;
        this.player=player;
        this.handler=handler;
        animator=new Animator();
        setX(x);
        health=START_HEALTH;
        setWidth(image.getWidth() / 3);
        setHeight(image.getHeight() / 3);
        setVelY(0);
        setVelX(-2);
        if(movingImages==null){
            initiateBitmaps(numFrames,res,image);
        }
        animator.setImages(movingImages);
        animator.setDelay(50);


    }
    /**
     * Initate the pictures for all the states of this enemy, its static
     * so it only initates them once
     * @param numFrames number of images
     * @param res the recources
     * @param image one image for scaling
     */
    private static void  initiateBitmaps(int numFrames, Resources res, Bitmap image){
        BitmapFactory.Options opts=new BitmapFactory.Options();
        opts.inDither=false;                     //Disable Dithering mode
        opts.inMutable=true;
        opts.inTempStorage=new byte[32 * 1024];
        movingImages =new Bitmap[numFrames-1];
        attackingImages=new Bitmap[numFrames];
        movingImages[0]= BitmapFactory.decodeResource(res, R.drawable.boss2, opts);
        movingImages[1]= BitmapFactory.decodeResource(res, R.drawable.boss3, opts);
        movingImages[2]= BitmapFactory.decodeResource(res, R.drawable.boss4, opts);
        movingImages[3]= BitmapFactory.decodeResource(res,R.drawable.boss5,opts);
        attackingImages[0]= BitmapFactory.decodeResource(res, R.drawable.boss1, opts);
        attackingImages[1]= BitmapFactory.decodeResource(res, R.drawable.boss2, opts);
        attackingImages[2]= BitmapFactory.decodeResource(res, R.drawable.boss3, opts);
        attackingImages[3]= BitmapFactory.decodeResource(res, R.drawable.boss4, opts);
        attackingImages[4]= BitmapFactory.decodeResource(res, R.drawable.boss5, opts);
    }
    @Override
    public void attackThis() {
        health-=2;
    }

    @Override
    public void tick() {
        setX(getX() + getVelX());
        setY(getY() + getVelY());
        if (getBounds().intersect(player.getBounds()) && !isAttacking()) {
            setAttacking(true);
            animator.setImages(attackingImages);
            setVelX(0);
        }
        if(health<=0){
            player.addScore(100);
            handler.removeObject(this);
        }
        animator.tick();
        if(isAttacking()) {
            ticks++;
            animator.tick();
            if(ticks>=90){
                player.attackThis(damage);
                ticks=0;
            }
        }
    }

    @Override
    public void draw(Canvas canvas) {
        setY(canvas.getHeight() - (getHeight()*3));
        image=animator.getImage();
        Paint myPaint = new Paint();
        myPaint.setColor(Color.rgb(75, health * 2, 0));
        myPaint.setStrokeWidth(10);
        canvas.drawRect(getHealthBar(health), myPaint);
        canvas.drawBitmap(image, getX(), getY(), null);
    }
}
