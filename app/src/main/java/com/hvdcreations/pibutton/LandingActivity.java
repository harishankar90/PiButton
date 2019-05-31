package com.hvdcreations.pibutton;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;

import java.io.IOException;

import pl.droidsonroids.gif.GifDrawable;

public class LandingActivity extends AppCompatActivity {

    ConstraintLayout btn,gif;

    ImageView status;
    TextView txt;
    GifDrawable piconngif;
    private int SLEEP_TIMER = 3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_landing);

        btn = findViewById(R.id.btnlayout);
        gif = findViewById(R.id.giflayout);

        txt = findViewById(R.id.textViewBtn);
        status = findViewById(R.id.imBtn);

        try {
            piconngif = new GifDrawable( getResources(), R.drawable.piconn);
            final MediaController mc = new MediaController(LandingActivity.this);
            mc.setMediaPlayer(piconngif);
            mc.show();

        } catch (IOException e) {
            e.printStackTrace();
            //Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

        status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn.setVisibility(View.INVISIBLE);
                gif.setVisibility(View.VISIBLE);

                LogoLauncher logoLauncher = new LogoLauncher();
                logoLauncher.start();

            }
        });

    }

    private class LogoLauncher extends Thread{
        public void run(){
            try{
                sleep(1000 * SLEEP_TIMER);

            }catch(InterruptedException e){
                e.printStackTrace();
            }
            Intent intent = new Intent(LandingActivity.this, MainActivity.class);
            startActivity(intent);
            LandingActivity.this.finish();
        }
    }
}
