package com.example.dk_ragnar.lightsaber10;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dk_ragnar.lightsaber10.Models.Shot;

import java.util.ArrayList;

public class Game extends AppCompatActivity implements SensorEventListener {

    private static final String TAG = "MainActivity";
    private MediaPlayer sonidoEncendido, sonidoApagado, sonidoFondo, movimiento1, movimiento2, disparoderecha, disparoizquierda, clash;
    SensorManager sm;
    Sensor proximidad, acelerometro;
    TextView informe, txtscore;
    RelativeLayout imagenFondo;
    String mostrar;
    private float proximityCalibratedMax = Float.MIN_VALUE;
    Boolean estado=false;
    ArrayList<Integer> generados;
    ArrayList<Shot> disparos;
    int colision = 0;
    int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        View decorativeView =getWindow().getDecorView();
        decorativeView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_FULLSCREEN |
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        );

        informe = (TextView) findViewById(R.id.informe);
        txtscore = (TextView) findViewById(R.id.txtScore);
        imagenFondo = (RelativeLayout) findViewById(R.id.imagenFondo);
        sonidoEncendido = MediaPlayer.create(this, R.raw.ensendido);
        sonidoApagado = MediaPlayer.create(this, R.raw.apagado);
        sonidoFondo = MediaPlayer.create(this, R.raw.fondo);
        movimiento1 = MediaPlayer.create(this, R.raw.movimiento);
        movimiento2 = MediaPlayer.create(this, R.raw.movimientoo);
        disparoderecha = MediaPlayer.create( this, R.raw.disparoderecho);
        disparoizquierda = MediaPlayer.create( this, R.raw.disparoizquierdo);
        clash = MediaPlayer.create(this, R.raw.clash);

        sm = (SensorManager) this.getSystemService(Context.SENSOR_SERVICE);

        generados = new ArrayList<>();

        for(int i =0 ; i<10 ; i++){
            int dospuntosuve = (int) (Math.random() * 2);
            generados.add(dospuntosuve);
        }

        proximidad = sm.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        sm.registerListener(this,proximidad,SensorManager.SENSOR_DELAY_GAME);

        acelerometro = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sm.registerListener(this,acelerometro,SensorManager.SENSOR_DELAY_GAME);
        playNow();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        Sensor sensor = event.sensor;

        String valor="";

        if(event.values[0] > proximityCalibratedMax){
            proximityCalibratedMax = event.values[0];
        }
        switch (sensor.getType()) {
            case Sensor.TYPE_PROXIMITY:
                if (event.sensor.getType() == Sensor.TYPE_PROXIMITY && proximityCalibratedMax != Float.MIN_VALUE) {
                    valor = event.values[0] + "";
                    informe.setText(valor);


                    if (valor.equals("8.0")) {
                        estado = false;
                        sonidoApagado.start();
                        imagenFondo.setBackgroundResource(R.drawable.sableapagado);

                    }
                    if (valor.equals("0.0")) {
                        estado = true;
                        sonidoEncendido.start();
                        imagenFondo.setBackgroundResource(R.drawable.sableencendido);

                        Thread timer = new Thread() {
                            public void run() {
                                try {
                                    sleep(100);

                                } catch (InterruptedException e) {

                                } finally {
                                    sonidoFondo.start();

                                }
                            }
                        };
                        timer.start();
                    }

                    if (estado == true) {

                    }
                    if (estado == false) {
                        if (sonidoFondo.isPlaying()) {
                            if (sonidoFondo != null) {
                                sonidoFondo.pause();
                            }
                        }
                    }
                }
                break;
            case Sensor.TYPE_ACCELEROMETER:
                float z = event.values[2];
                txtscore.setText(score+"");

                //Log.d(TAG,"UDB"+estado);
                if(estado==true){
                    if (z > 10) {
                        movimiento1.start();
                        if(colision == 0){
                            colision = -1;
                        }
                        //disparoizquierda.start();
                    }
                    if (z < -10) {
                        movimiento2.start();
                        if(colision == 0){
                            colision = 1;
                        }
                        //disparoderecha.start();
                    }
                }
                break;
        }
    }

    private void playNow() {
        disparos = new ArrayList<>();
        for(int i : generados){

            if(i == 0){
                Shot disparo = new Shot();
                disparo.type = "left";
                disparo.sound = "disparoizquierda";
                disparos.add(disparo);
            }else if(i == 1){
                Shot disparo = new Shot();
                disparo.type = "right";
                disparo.sound = "disparoderecha";
                disparos.add(disparo);
            }
        }

        //Treat de inicio de juego
        Thread timer = new Thread() {
            public void run() {
                try {
                    sleep(2000);

                } catch (InterruptedException e) {

                } finally {
                    int contador = 0;
                        for(Shot shot : disparos){

                            contador += 3000;
                            disparar(shot, contador);
                        }
                        yaterminojoven(contador);
                }
            }
        };
        timer.start();

    }

    private void yaterminojoven(final int contador) {
        Thread timer = new Thread() {
            public void run() {
                try {
                    sleep(contador+2000);

                } catch (InterruptedException e) {

                } finally {
                    Intent intent = new Intent(Game.this, ScoreResult.class);
                    intent.putExtra("score", score);
                    startActivity(intent);
                }
            }
        };
        timer.start();
    }

    private void disparar(final Shot shot, final int sleep) {

        Thread timer = new Thread() {
            public void run() {
                try {
                    sleep(sleep);
                    if(shot.sound.equals("disparoizquierda")){
                        disparoizquierda.start();
                        sleep(1000);
                        if(colision == -1){
                            clash.start();
                            score += 1;

                            //Log.d("UDB", "coliciono");
                        }

                    }else if(shot.sound.equals("disparoderecha")){
                        disparoderecha.start();
                        sleep(1000);
                        if(colision == 1){
                            clash.start();
                            score += 1;
                            //Log.d("UDB", "colisiono");
                        }
                    }

                } catch (InterruptedException e) {

                } finally {
                    Log.d("UDB", sleep+"");
                    colision = 0;

                }
            }
        };
        timer.start();


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onPause() {
        sm.unregisterListener(this);
        super.onPause();
    }

    @Override
    protected void onResume() {
        sm.registerListener(this, acelerometro, SensorManager.SENSOR_DELAY_NORMAL);
        sm.registerListener(this, proximidad, SensorManager.SENSOR_DELAY_NORMAL);
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
        sonidoFondo.stop();
    }
}
