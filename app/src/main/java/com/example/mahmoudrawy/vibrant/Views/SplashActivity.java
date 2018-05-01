package com.example.mahmoudrawy.vibrant.Views;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.mahmoudrawy.vibrant.R;
import util.Constants;

public class SplashActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        runSplashScreen();

    }

    private void runSplashScreen() {
        new Handler().postDelayed(new Runnable() {
            public void run() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                overridePendingTransition(R.anim.activity_slide_up, R.anim.activity_slide_stay);
                overridePendingTransition(R.anim.activity_slide_up, R.anim.activity_slide_down);
                finish();

            }
        }, Constants.SECONDS * Constants.MSECONDS);

    }
}



