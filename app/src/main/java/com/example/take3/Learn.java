package com.example.take3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Learn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomnavview);
        bottomNavigationView.setSelectedItemId(R.id.learn);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.home:
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    finish();
                    return true;
                case R.id.tools:
                    startActivity(new Intent(getApplicationContext(), Tools.class));
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    finish();
                    return true;
                case R.id.learn:
                    return true;
                case R.id.profile:
                    startActivity(new Intent(getApplicationContext(), Profile.class));
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    finish();
                    return true;
            }
            return false;
        });
    }

    public void openContentpage1(View view) {
        startActivity(new Intent(this, Contentpage1.class));
    }

    public void openContentpage2(View view) {
        startActivity(new Intent(this, Contentpage2.class));
    }

    public void openContentpage3(View view) {startActivity(new Intent(this, Contentpage3.class));}
}