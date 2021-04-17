package com.syu.canbus;

import android.app.Application;

import com.syu.canbus.module.MsToolkitConnection;
import com.syu.canbus.module.canbus.ConnectionCanBus;
import com.syu.canbus.module.main.ConnectionMain;
import com.syu.canbus.module.sound.ConnectionSound;

public class CanBus extends Application {

    private MsToolkitConnection msToolkitConnection;

    @Override
    public void onCreate() {
        super.onCreate();

        connectService();
        DataPack.init();
    }

    private void connectService() {
        msToolkitConnection = new MsToolkitConnection(this);
        msToolkitConnection.addObserver(ConnectionCanBus.getInstance());
        msToolkitConnection.addObserver(ConnectionMain.getInstance());
        msToolkitConnection.addObserver(ConnectionSound.getInstance());
        msToolkitConnection.connect();
    }
}
