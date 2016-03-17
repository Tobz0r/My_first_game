package se.umu.dv3tes.myapplication.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import se.umu.dv3tes.myapplication.GameObjects.GameObject;
import se.umu.dv3tes.myapplication.R;

public class EndActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);
        Button playBtn= (Button) findViewById(R.id.playBtn);
        TextView txt= (TextView) findViewById(R.id.textView);
        txt.setText("JEBANE SCORE : " + getIntent().getExtras().get("Score"));
        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),GameActivity.class);
                startActivity(i);
                EndActivity.this.finish();
            }
        });

    }

}
