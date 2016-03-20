package se.umu.dv3tes.myapplication.Activitys;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import se.umu.dv3tes.myapplication.Database.DatabaseHandler;
import se.umu.dv3tes.myapplication.Database.PlayerScore;
import se.umu.dv3tes.myapplication.R;

/**
 *
 */
public class MainActivity extends AppCompatActivity {

    private DatabaseHandler db;
    private Button helpBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        db=new DatabaseHandler(this);
        final Button newGame = (Button) findViewById(R.id.startBtn);
        helpBtn=(Button) findViewById(R.id.helpBtn);
        newGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newGame.setEnabled(false);//to avoid spamming button to masstart activities
                Intent i = new Intent(getApplicationContext(), GameActivity.class);
                startActivity(i);
                MainActivity.this.finish();
            }
        });
        helpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle(R.string.action_help)
                        .setMessage(R.string.dialog_help)
                        .setPositiveButton(android.R.string.ok, null)
                        .setNegativeButton(android.R.string.cancel,null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_Highscore) {
            String message;
            List<PlayerScore> players=db.getAllPlayers();
            if(players.size()!=0){

                message="Best score:\n";
                for(int i=players.size()-1; i >=0 ; i--){
                    if(i<0) break;
                    message= message+ players.get(i).getScore() +
                            " " +players.get(i).getName()+"\n";
                }
            }else{
                message="No highscore yet";
            }
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle(R.string.action_highscore)
                    .setMessage(message)
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // continue with delete
                        }
                    })
                    .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // do nothing
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
        if(id== R.id.action_Exit){
            System.exit(0);
        }
        if(id == R.id.action_About){
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("About")
                    .setMessage("Made by Tobias Estefors")
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // continue with delete
                        }
                    })
                    .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // do nothing
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Redefines the backbutton to exit the app if pressed at mainmenu
     */
    @Override
    public void onBackPressed(){
        System.exit(0);
    }
}
