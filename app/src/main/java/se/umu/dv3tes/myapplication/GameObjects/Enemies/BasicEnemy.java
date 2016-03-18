package se.umu.dv3tes.myapplication.GameObjects.Enemies;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import se.umu.dv3tes.myapplication.Model.Animator;
import se.umu.dv3tes.myapplication.GameObjects.GameObject;
import se.umu.dv3tes.myapplication.GameLogic.Handler;
import se.umu.dv3tes.myapplication.R;
import se.umu.dv3tes.myapplication.GameObjects.Player.Player;

/**
 * Created by Tobz0r on 2016-03-15.
 */
public class BasicEnemy extends GameObject implements Enemy {
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
    private final int damage=2;

    public BasicEnemy(Player player, Bitmap image, int x, int y, Handler handler, int numFrames, Resources res){
        this.image=image;
        ticks=0;
        this.res=res;
        this.player=player;
        this.handler=handler;
        animator=new Animator();
        setX(x);
        health=START_HEALTH;
        setWidth(image.getWidth() / 9);
        setHeight(image.getHeight() / 9);
        setVelY(0);
        setVelX(-5);
        if(movingImages==null){
            initiateBitmaps(numFrames,res,image);
        }
        animator.setImages(movingImages);
        animator.setDelay(50);


    }

    private static void  initiateBitmaps(int numFrames, Resources res, Bitmap image){
        BitmapFactory.Options opts=new BitmapFactory.Options();
        opts.inDither=false;                     //Disable Dithering mode
        opts.inMutable=true;
        opts.inTempStorage=new byte[32 * 1024];
        movingImages =new Bitmap[numFrames];
        attackingImages=new Bitmap[numFrames-1];
        movingImages[0]= getResizedBitmap(BitmapFactory.decodeResource(res, R.drawable.walking1,opts),image.getWidth()/3,image.getHeight()/3);
        movingImages[1]= getResizedBitmap(BitmapFactory.decodeResource(res, R.drawable.walking2,opts), image.getWidth() / 3, image.getHeight() / 3);
        movingImages[2]= getResizedBitmap(BitmapFactory.decodeResource(res, R.drawable.walking3,opts), image.getWidth() / 3, image.getHeight() / 3);
        movingImages[3]= getResizedBitmap(BitmapFactory.decodeResource(res, R.drawable.walking4,opts), image.getWidth() / 3, image.getHeight() / 3);
        movingImages[4]= getResizedBitmap(BitmapFactory.decodeResource(res, R.drawable.walking1,opts), image.getWidth() / 3, image.getHeight() / 3);
        attackingImages[0]= getResizedBitmap(BitmapFactory.decodeResource(res, R.drawable.attack1,opts),image.getWidth()/3,image.getHeight()/3);
        attackingImages[1]= getResizedBitmap(BitmapFactory.decodeResource(res, R.drawable.attack2,opts), image.getWidth() / 3, image.getHeight() / 3);
        attackingImages[2]= getResizedBitmap(BitmapFactory.decodeResource(res, R.drawable.attack3,opts), image.getWidth() / 3, image.getHeight() / 3);
        attackingImages[3]= getResizedBitmap(BitmapFactory.decodeResource(res, R.drawable.attack4,opts), image.getWidth() / 3, image.getHeight() / 3);
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
            if(ticks>=60){
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

    /**
     * Gets called when you damage a flying enemy,
     * a flying enemy takes 3 hits to kill
     */
    @Override
    public void attackThis() {
        health-=25;
    }

    public String toString(){
        return "FlyingEnemy";
    }
}
      /*  Paint myPaint = new Paint();
        myPaint.setColor(Color.rgb(0, 0, 0));
        myPaint.setStrokeWidth(10);
        canvas.drawRect(getBounds(),myPaint);*/