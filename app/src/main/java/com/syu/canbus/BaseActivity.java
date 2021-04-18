package com.syu.canbus;

import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onStart() {
        super.onStart();
        onRegisterListener();
    }

    @Override
    protected void onStop() {
        onUnregisterListener();
        super.onStop();
    }

    protected void onRegisterListener() {

    }

    protected void onUnregisterListener() {

    }
}
