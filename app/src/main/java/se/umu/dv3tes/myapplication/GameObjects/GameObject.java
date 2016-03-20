package se.umu.dv3tes.myapplication.GameObjects;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.Paint;

/**
 * Everything that is needed for an object in the game,
 * contains the position, speed and size for all
 * of the enimies and the player
 * @author Tobias Estefors
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

    /**
     * Chcecks if gameobject is attacking or not
     * @return true if attacking else false
     */
    public boolean isAttacking() {
        return attacking;
    }

    /**
     * Change the attacking state
     * @param attacking
     */
    public void setAttacking(boolean attacking) {
        this.attacking = attacking;
    }

    /**
     *
     * @return the x-value for the gameobject
     */
    public float getX() {
        return x;
    }

    /**
     * Changes the x-value
     * @param x
     */
    public void setX(float x) {
        this.x = x;
    }

    /**
     * Returns the y-value
     * @return y-value
     */
    public float getY() {
        return y;
    }

    /**
     * Changes the y-value
     * @param y
     */
    public void setY(float y) {
        this.y = y;
    }

    /**
     * Returns the velocity in x-axis
     * @return the velocity
     */
    public float getVelX() {
        return velX;
    }

    /**
     * Changes the velocity in x-axis
     * @param velX
     */
    public void setVelX(float velX) {
        this.velX = velX;
    }

    /**
     * returns the velocity in y-axis
     * @return the velocity
     */
    public float getVelY() {
        return velY;
    }

    /**
     * Changes the velocity in y-axis
     * @param velY
     */
    public void setVelY(float velY) {
        this.velY = velY;
    }

    /**
     * Returns the width of this gameobject
     * @return the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Changes the width
     * @param width
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     *  Gets the height
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Changes the height
     * @param height
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Calculates the hitbox for this gameobject
     * @return a rectangle with the same ratio as this gameobject
     */
    public Rect getBounds(){
        return new Rect((int)x,(int)y,(int)x+(width*3),(int)y+(height*3));
    }

    /**
     * Creates a bar to be drawn over its head with a size depending of
     * the health of the object
     * @param health the objects health
     * @return a rectangle with the health
     */
    public Rect getHealthBar(float health){
        float startHealth=(width*3);
        float scale=health/START_HEALTH;
        float currentHealth=startHealth*scale;
        return new Rect((int)x, (int) y,(int)(x+currentHealth),(int)y+height/3);
    }

    /**
     * Updates the gamestate for this gameobject
     */
    public abstract void tick();

    /**
     * Used to draw this enemy on the board
     * @param canvas the board
     */
    public abstract void draw(Canvas canvas);

    /**
     * Resizes a bitmap
     * @param bm the bitmap to be resized
     * @param newWidth the new width
     * @param newHeight the new height
     * @return a resized bitmap
     */
    public static Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap resizedBitmap = Bitmap.createBitmap(
                bm, 0, 0, width, height, matrix, false);
        bm.recycle();
        return resizedBitmap;
    }

    /**
     * Calculates the distance between @this object and
     * the target object
     * @param targetX the targets x-value
     * @param targetY the targets y-value
     * @return the distance between this and the target
     */
    public float calculateDistance(int targetX, int targetY){
        float deltaX = getX() - targetX;
        float deltaY = getY() - targetY;
        float distance = (float) Math.sqrt((deltaX * deltaX) + (deltaY * deltaY));
        return distance;
    }



}
