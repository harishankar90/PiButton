package com.hvdcreations.pibutton;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.Toast;

import java.io.IOException;

import pl.droidsonroids.gif.GifDrawable;

public class SplashscreenActivity extends AppCompatActivity {

    private int SLEEP_TIMER = 3;
    GifDrawable planetgif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splashscreen);

        try {
            planetgif = new GifDrawable( getResources(), R.drawable.pi);
            MediaController mc = new MediaController(SplashscreenActivity.this);
            mc.setMediaPlayer(planetgif);
            mc.show();

        } catch (IOException e) {
            e.printStackTrace();
            //Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
        LogoLauncher logoLauncher = new LogoLauncher();
        logoLauncher.start();
    }

    private class LogoLauncher extends Thread{
        public void run(){
            try{
                sleep(2000 * SLEEP_TIMER);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            Intent intent = new Intent(SplashscreenActivity.this, LandingActivity.class);
            startActivity(intent);
            SplashscreenActivity.this.finish();
        }
    }
}
