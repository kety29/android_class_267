package com.example.user.simpleui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class DrinkMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink_menu);

        Log.d("Debug", "Drink Menu Activity onCreat");
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.d("Debug", "Drink Menu Activity OnStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("Debug", "Drink Menu Activity OnStop");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Debug", "Drink Menu Activity onPause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Debug", "Drink Menu Activity OnDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("Debug", "Drink Menu Activity onRestart");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Debug", "Drink Menu Activity onResume");
    }
}
