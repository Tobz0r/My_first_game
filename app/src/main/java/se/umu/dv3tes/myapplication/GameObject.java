package se.umu.dv3tes.myapplication;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.Paint;

/**
 * Created by Tobz0r on 2016-03-15.
 */
public abstract class GameObject {
    private float x;
    private float y;
    private float velX;
    private float velY;
    private int width;
    private int height;
    protected final static int START_HEALTH=100;
    private boolean attacking;

    public boolean isAttacking() {
        return attacking;
    }

    public void setAttacking(boolean attacking) {
        this.attacking = attacking;
    }




    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getVelX() {
        return velX;
    }

    public void setVelX(float velX) {
        this.velX = velX;
    }

    public float getVelY() {
        return velY;
    }

    public void setVelY(float velY) {
        this.velY = velY;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Rect getBounds(){
        return new Rect((int)x,(int)y,(int)x+(width*3),(int)y+(height*3));
    }

    public Rect getHealthBar(float health){
        float startHealth=(width*3);
        float scale=health/START_HEALTH;
        float currentHealth=startHealth*scale;
        return new Rect((int)x, (int) y,(int)(x+currentHealth),(int)y+height/3);
    }

    public abstract void tick();

    public abstract void draw(Canvas canvas);

    public static Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap resizedBitmap = Bitmap.createBitmap(
                bm, 0, 0, width, height, matrix, false);
        //bm.recycle();
        return resizedBitmap;
    }

    public float calculateDistance(int targetX, int targetY){
        float deltaX = getX() - targetX;
        float deltaY = getY() - targetY;
        float distance = (float) Math.sqrt((deltaX * deltaX) + (deltaY * deltaY));
        return distance;
    }



}
