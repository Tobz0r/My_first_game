package se.umu.dv3tes.myapplication;

import android.graphics.Bitmap;

/**
 * Created by Tobz0r on 2016-03-15.
 */
public class Animator {
    private Bitmap[] images;
    private int currentFrame;
    private long startTime;
    private long delay;

    public void setImages(Bitmap[] images){
        this.images=images;
        currentFrame=0;
        startTime=System.nanoTime();
    }
    public void setDelay(long d){delay = d;}
    public void setFrame(int i){currentFrame= i;}

    public void tick()
    {
        long elapsed = (System.nanoTime()-startTime)/1000000;

        if(elapsed>delay)
        {
            currentFrame++;
            startTime = System.nanoTime();
        }
        if(currentFrame == images.length){
            currentFrame = 0;
        }
    }
    public Bitmap getImage(){
        return images[currentFrame];
    }
    public int getFrame(){return currentFrame;}



}
