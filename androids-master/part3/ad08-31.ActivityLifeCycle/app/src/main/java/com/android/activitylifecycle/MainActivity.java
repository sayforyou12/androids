package com.android.activitylifecycle;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("life cycle", "onCreate()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("life cycle", "onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("life cycle", "onResume()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e("life cycle", "onRestart()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("life cycle", "onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("life cycle", "onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("life cycle", "onDestroy()");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.e("life cycle", "onSaveInstanceState()");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.e("life cycle", "onRestoreInstanceState()");
    }
}
