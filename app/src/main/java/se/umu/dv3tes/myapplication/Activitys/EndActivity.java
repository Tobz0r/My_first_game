package se.umu.dv3tes.myapplication.Activitys;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import se.umu.dv3tes.myapplication.Database.DatabaseHandler;
import se.umu.dv3tes.myapplication.Database.PlayerScore;
import se.umu.dv3tes.myapplication.R;

/**
 * Endscreen when the game is finished
 * @author Tobias Estefors
 */
public class EndActivity extends AppCompatActivity {

    private EditText nameEdit;
    private DatabaseHandler db;
    private Button saveBtn, playBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);
        playBtn= (Button) findViewById(R.id.playBtn);
        TextView txt= (TextView) findViewById(R.id.textView);
        txt.setText("YOUR SCORE : " + getIntent().getExtras().get("Score")+ " at level "+getIntent().getExtras().get("Level"));
        nameEdit= (EditText) findViewById(R.id.editText);
        nameEdit.setImeOptions(EditorInfo.IME_FLAG_NO_EXTRACT_UI);

        saveBtn=(Button) findViewById(R.id.saveBtn);
        if(savedInstanceState!=null){
            saveBtn.setEnabled(savedInstanceState.getBoolean("Save"));
        }
        db=new DatabaseHandler(this);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.addHighscore(nameEdit.getText().toString(), (int)getIntent().getExtras().get("Score"));
                saveBtn.setEnabled(false);
            }
        });
        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playBtn.setEnabled(false);//to avoid spammiing
                Intent i=new Intent(getApplicationContext(),GameActivity.class);
                startActivity(i);
                EndActivity.this.finish();
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
                message=getString(R.string.no_highscore);
            }
            new AlertDialog.Builder(EndActivity.this)
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
            new AlertDialog.Builder(EndActivity.this)
                    .setTitle(R.string.action_about)
                    .setMessage(R.string.dialog_about)
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
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        savedInstanceState.putBoolean("Save",saveBtn.isEnabled());
        super.onSaveInstanceState(savedInstanceState);
    }

    public void onBackPressed(){
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
        EndActivity.this.finish();
    }

}
