package se.umu.dv3tes.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Point;
import android.util.Base64;
import android.view.Display;

/**
 * Created by Tobz0r on 2016-03-15.
 */
public class Level {

    private Bitmap image;
    private int x,y,dx,dy;
    private int width,height;

    public Level(Bitmap image){
        this.image=image;
        width=image.getWidth();
        height=image.getHeight();
    }

    public void tick(){
        x+=dx;
        if(x<-GamePanel.WIDTH){
            x=0;
        }

    }
    public void draw(Canvas canvas){
        canvas.drawBitmap(image,x,y,null);
        if(x < 0){
            canvas.drawBitmap(image,x+width,y,null);
        }
    }

    public Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // CREATE A MATRIX FOR THE MANIPULATION
        Matrix matrix = new Matrix();
        // RESIZE THE BIT MAP
        matrix.postScale(scaleWidth, scaleHeight);

        // "RECREATE" THE NEW BITMAP
        Bitmap resizedBitmap = Bitmap.createBitmap(
                bm, 0, 0, width, height, matrix, false);
        bm.recycle();
        return resizedBitmap;
    }


}
