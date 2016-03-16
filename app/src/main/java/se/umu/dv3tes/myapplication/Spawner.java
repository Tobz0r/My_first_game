package se.umu.dv3tes.myapplication;

/**
 * Created by Tobz0r on 2016-03-15.
 */
public class Spawner {

    private Handler handler;
    private int scoreKeep = 100; //set to 100 to make level start at 1
    private int level =100;

    public Spawner(Handler handler) {
        this.handler = handler;
    }

    public void tick() {
        scoreKeep++;
        if (scoreKeep >= 100) {
            scoreKeep = 0;
           // hud.setLevel(hud.getLevel() + 1);
            if (level == 1) {
               // handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 100), r.nextInt(Game.HEIGHT - 100), ID.BasicEnemy, handler));
               // handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 100), r.nextInt(Game.HEIGHT - 100), ID.BasicEnemy, handler));
               // handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 100), r.nextInt(Game.HEIGHT - 100), ID.BasicEnemy, handler));
            }
            if (level % 10 == 0) {
               // handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 100), r.nextInt(Game.HEIGHT - 100), ID.FastEnemy, handler));
            }
            if (level % 25 == 0) {
              //  handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 100), r.nextInt(Game.HEIGHT - 100), ID.SmartEnemy, handler));
            }
            if (level % 5 == 0) {
               // handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 100), r.nextInt(Game.HEIGHT - 100), ID.BasicEnemy, handler));
            }
        }

    }
}


