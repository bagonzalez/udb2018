package com.example.dk_ragnar.lightsaber10;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ScoreResult extends AppCompatActivity {

    TextView txtscore;
    Button goback;
    private MediaPlayer iamyourfather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_result);

        int score = (int)getIntent().getSerializableExtra("score");
        goback = (Button) findViewById(R.id.goback);
        txtscore = (TextView) findViewById(R.id.txtscorefinal);

        iamyourfather = MediaPlayer.create( this, R.raw.iamyoufather);
        iamyourfather.start();

        txtscore.setText(score+"");

        goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ScoreResult.this, MainMenu.class);
                startActivity(intent);
            }
        });
    }
}
