package se.umu.dv3tes.myapplication.GameLogic;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

import se.umu.dv3tes.myapplication.Model.GamePanel;

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
        long lastTime = System.currentTimeMillis();
        final double amountOfTicks = 60;
        long ns = Math.round(1000 / amountOfTicks);
        double delta = 0;
        while(gameRunning){
            long now = System.currentTimeMillis();
            long wait = ns -(now-lastTime);
            delta += (now - lastTime) / ns;
            lastTime = now;
            wait=wait < 0 ? 0 : wait;
            System.out.println(wait);
            /*try {
                this.sleep(wait);
            } catch (Exception e) {}*/
             while(delta >= 1){
                 gamePanel.tick();
                 delta--; }
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
    public void stopThread() throws InterruptedException {
        gameRunning=false;
        join();

    }
    public void setGameRunning(boolean gameRunning){
        this.gameRunning=gameRunning;
    }
}
