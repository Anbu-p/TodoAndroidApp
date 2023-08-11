package com.example.todoandroidapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.splash_activity);

        final Handler handler = new Handler();
        handler.postDelayed(() -> {
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            finish();
        }, 4000);
    }
}
