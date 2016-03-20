package se.umu.dv3tes.myapplication.View;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * The background to draw on the map
 * @author Tobias Estefors
 */
public class Level {

    private Bitmap image;
    private int x,y;
    private int width,height;

    public Level(Bitmap image){
        this.image=image;
        width=image.getWidth();
        height=image.getHeight();
    }

    /**
     * Used before all other drawing to make sure that
     * the name will be correctly animated
     * @param canvas the board
     */
    public void draw(Canvas canvas){
        canvas.drawBitmap(image,x,y,null);
        if(x < 0){
            canvas.drawBitmap(image,x+width,y,null);
        }
    }

}
