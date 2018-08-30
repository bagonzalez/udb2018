package com.example.dk_ragnar.lightsaber10;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.OnClick;

public class MainMenu extends AppCompatActivity {

    Button botonJugar;

    @OnClick(R.id.optionOnlySaber)
    public void onlySaber(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.optionPlay)
    public void play(View view){
        Intent intent = new Intent(this, Game.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        botonJugar = (Button) findViewById(R.id.optionPlay);

        botonJugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenu.this, Game.class);
                startActivity(intent);
            }
        });
    }
}
