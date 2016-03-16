package se.umu.dv3tes.myapplication;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

/**
 * Created by Tobz0r on 2016-03-15.
 */
public class GameThread extends Thread {

    private GamePanel gamePanel;
    private SurfaceHolder surfaceHolder;
    private Canvas canvas;
    private boolean gameRunning;

    public GameThread(SurfaceHolder surfaceHolder, GamePanel gamePanel){
        super();
        this.surfaceHolder=surfaceHolder;
        this.gamePanel=gamePanel;
    }

    @Override
    public void run(){
        long lastTime = System.nanoTime();
        final double amountOfTicks = 30;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        while(gameRunning){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1){
                gamePanel.tick();
                delta--;
            }
            canvas=null;
            try{
                canvas=surfaceHolder.lockCanvas();
                synchronized (surfaceHolder){
                    gamePanel.draw(canvas);
                }
            }catch (Exception e){

            }
            finally {
                if(canvas!=null){
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }

    public void setGameRunning(boolean gameRunning){
        this.gameRunning=gameRunning;
    }
}
