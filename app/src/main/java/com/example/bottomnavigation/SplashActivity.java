package com.example.bottomnavigation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Use a Handler to delay the splash screen for a few seconds
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // After the delay, start the MainActivity
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        }, 1000); // delay for 3 seconds
    }
}