package com.syu.canbus.module.sound;

import android.os.RemoteException;

import com.syu.ipc.FinalMainServer;
import com.syu.ipc.IRemoteToolkit;
import com.syu.canbus.module.ConnectionObserver;

public class ConnectionSound implements ConnectionObserver {
    private static final ConnectionSound INSTANCE = new ConnectionSound();

    private ConnectionSound() {
        // Empty constructor
    }

    @Override
    public void onConnected(IRemoteToolkit toolkit) {
        try {
            DataSound.PROXY.setRemoteModule(toolkit.getRemoteModule(FinalMainServer.MODULE_CODE_SOUND));
            ModuleCallbackSound callback = ModuleCallbackSound.getInstance();
            DataSound.PROXY.register(callback, 2, 1);
            DataSound.PROXY.register(callback, 3, 1);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDisconnected() {
        DataSound.PROXY.setRemoteModule(null);
    }

    public static ConnectionSound getInstance() {
        return INSTANCE;
    }
}
