package com.syu.canbus.module.main;

import android.os.RemoteException;

import com.syu.ipc.FinalMainServer;
import com.syu.ipc.IRemoteToolkit;
import com.syu.canbus.module.ConnectionObserver;

public class ConnectionMain implements ConnectionObserver {
    private static final ConnectionMain INSTANCE = new ConnectionMain();

    private ConnectionMain() {
        // Empty constructor
    }

    @Override
    public void onConnected(IRemoteToolkit toolkit) {
        try {
            DataMain.PROXY.setRemoteModule(toolkit.getRemoteModule(FinalMainServer.MODULE_CODE_MAIN));
            ModuleCallbackMain callback = ModuleCallbackMain.getInstance();
            DataMain.PROXY.register(callback, 0, 1);
            DataMain.PROXY.register(callback, 12, 1);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDisconnected() {
        DataMain.PROXY.setRemoteModule(null);
    }

    public static ConnectionMain getInstance() {
        return INSTANCE;
    }
}
