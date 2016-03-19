package se.umu.dv3tes.myapplication.Activitys;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import se.umu.dv3tes.myapplication.Model.GamePanel;
import se.umu.dv3tes.myapplication.R;

public class GameActivity extends AppCompatActivity {

    private GamePanel gamePanel;
    private MediaPlayer player;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        player = MediaPlayer.create(this, R.raw.cello);
        player.setLooping(true); // Set looping
        player.setVolume(100, 100);
       // player.start();
        gamePanel=new GamePanel(this);
        setContentView(new GamePanel(this));
    }

    public void onBackPressed(){
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
        GameActivity.this.finish();
    }
}
