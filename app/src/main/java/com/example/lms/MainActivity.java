package com.example.lms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.yellow_200));
        }

        //for testing only
//        Intent i = new Intent(MainActivity.this,Register.class);
//        i.putExtra("MAIN_ACT_KEY", "SIGNIN");
//        startActivity(i);
    }

    public void OnRegisterClick(View view){
        Intent i = new Intent(MainActivity.this,ChooseReg.class);
        startActivity(i);
    }

    public void OnSignInClick(View view){
        Intent i = new Intent(MainActivity.this,Register.class);
        i.putExtra("MAIN_ACT_KEY", "SIGNIN");
        startActivity(i);
    }
}