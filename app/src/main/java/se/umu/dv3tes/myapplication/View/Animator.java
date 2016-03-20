package se.umu.dv3tes.myapplication.View;

import android.graphics.Bitmap;

/**
 * Used to animate all gameobjects
 * @author Tobias Estefors
 */
public class Animator {
    private Bitmap[] images;
    private int currentFrame;
    private long startTime;
    private long delay;

    /**
     * Sets the images to be animates
     * @param images the imagearray
     */
    public void setImages(Bitmap[] images){
        this.images=images;
        currentFrame=0;
        startTime=System.nanoTime();
    }

    /**
     * Set how long time it shall be between each image
     * @param d the delay in miliseconds
     */
    public void setDelay(long d){delay = d;}

    /**
     * Changes the picture for the gameobject if the timeframe
     * has been met
     */
    public void tick() {
        long elapsed = (System.nanoTime()-startTime)/1000000;

        if(elapsed>delay) {
            currentFrame++;
            startTime = System.nanoTime();
        }
        if(currentFrame == images.length){
            currentFrame = 0;
        }
    }

    /**
     * Returns what the current image is
     * @return the image
     */
    public Bitmap getImage(){
        return images[currentFrame];
    }
}
