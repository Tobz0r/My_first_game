package se.umu.dv3tes.myapplication.GameLogic;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

import se.umu.dv3tes.myapplication.View.GamePanel;

/**
 * The game is running all its mechanicas via this thread
 * The game updates itself 60 times per second.
 * @author Tobias Estefors
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

    /**
     * Gets called when the thread starts, contains the updatelogic
     * for the game.
     */
    @Override
    public void run(){
        long lastTime = System.nanoTime();
        final double amountOfTicks = 60;
        long ns = Math.round(1000000000 / amountOfTicks);
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
            }catch (Exception e){}
            finally {
                if(canvas!=null){
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }

    /**
     * Stops the thread from continuing to run even when a game is over
     * @throws InterruptedException
     */
    public void stopThread() throws InterruptedException {
        gameRunning=false;
        join();
    }

    /**
     * Gets called when you start the game or when you quit
     * Sets the gamestate to running or not running
     * @param gameRunning true if you wanna start the game,
     *                    else false
     */
    public void setGameRunning(boolean gameRunning){
        this.gameRunning=gameRunning;
    }
}
