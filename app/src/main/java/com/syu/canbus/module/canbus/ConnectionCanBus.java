package com.syu.canbus.module.canbus;

import android.os.RemoteException;

import com.syu.ipc.FinalMainServer;
import com.syu.ipc.IRemoteToolkit;
import com.syu.canbus.module.ConnectionObserver;

public class ConnectionCanBus implements ConnectionObserver {
    private static final ConnectionCanBus INSTANCE = new ConnectionCanBus();

    private static final int[] CODE_CANBUS = {
            FinalCanbus.U_CAMERA_MODE,
            FinalCanbus.U_CANBUS_ID,
            FinalCanbus.U_CAR_BACKCAR,
            FinalCanbus.U_CARINFO_360_WORK_STATE,
            FinalCanbus.U_EXIST_PANORAMA,
            FinalCanbus.U_PARK_MODE,
            FinalCanbus.U_REAR_MOVE_TYPE,
            FinalCanbus.U_RIGHT_CAMERA_STATE,
            FinalCanbus.U_UISERVR_TYPE
    };

    private ConnectionCanBus() {
        // Empty constructor
    }

    @Override
    public void onConnected(IRemoteToolkit toolkit) {
        try {
            DataCanbus.PROXY.setRemoteModule(toolkit.getRemoteModule(FinalMainServer.MODULE_CODE_CANBUS));
            ModuleCallbackCanbus callback = ModuleCallbackCanbus.getInstance();
            for (int i : CODE_CANBUS) {
                DataCanbus.PROXY.register(callback, i, 1);
            }
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
        DataCanbus.DATA[FinalCanbus.U_CANBUS_ID] = 0;
        ModuleCallbackCanbus.getInstance().setCallbackCanbus(null);
    }

    public static ConnectionCanBus getInstance() {
        return INSTANCE;
    }
}
