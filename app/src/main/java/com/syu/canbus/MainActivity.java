package com.syu.canbus;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.syu.canbus.module.canbus.DataCanbus;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DataCanbus.PROXY.cmd(98, new int[]{255}, null, null);
    }
}