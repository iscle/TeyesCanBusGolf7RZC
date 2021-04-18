package com.syu.canbus.module.canbus;

import android.os.RemoteException;

import com.syu.ipc.FinalMainServer;
import com.syu.ipc.IRemoteToolkit;
import com.syu.canbus.module.ConnectionObserver;

public class ConnectionCanBus implements ConnectionObserver {
    private static final ConnectionCanBus INSTANCE = new ConnectionCanBus();

    private ConnectionCanBus() {
        // Empty constructor
    }

    @Override
    public void onConnected(IRemoteToolkit toolkit) {
        try {
            DataCanbus.PROXY.setRemoteModule(toolkit.getRemoteModule(FinalMainServer.MODULE_CODE_CANBUS));
            ModuleCallbackCanbusProxy callback = ModuleCallbackCanbusProxy.getInstance();
            DataCanbus.PROXY.register(callback, FinalCanbus.U_CANBUS_ID, 1);
            DataCanbus.PROXY.register(callback, FinalCanbus.U_AIR_WINDOW_ENABLE, 1);
            DataCanbus.PROXY.register(callback, FinalCanbus.U_DOOR_WINDOW_ENABLE, 1);
            DataCanbus.PROXY.register(callback, FinalCanbus.U_CAR_BT_ON, 1);
            DataCanbus.PROXY.register(callback, FinalCanbus.U_SHOW_AIR_WINDOW, 1);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDisconnected() {
        DataCanbus.PROXY.setRemoteModule(null);
        DataCanbus.DATA[1000] = 0;
        ModuleCallbackCanbusProxy.getInstance().setCallbackCanbus(null);
    }

    public static ConnectionCanBus getInstance() {
        return INSTANCE;
    }
}
