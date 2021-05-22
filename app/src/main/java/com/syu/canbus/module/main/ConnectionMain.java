package com.syu.canbus.module.main;

import android.os.RemoteException;

import com.syu.ipc.FinalMainServer;
import com.syu.ipc.IModuleCallback;
import com.syu.ipc.IRemoteToolkit;
import com.syu.canbus.module.ConnectionObserver;

public class ConnectionMain implements ConnectionObserver {
    private static final ConnectionMain INSTANCE = new ConnectionMain();

    private static final int[] CODE_MAIN = {
            FinalMain.U_APP_ID,
            FinalMain.U_BACKCAR,
            FinalMain.U_BACKCAR_360_CAMERA,
            FinalMain.U_BACKCAR_MIRROR,
            FinalMain.U_BACKCAR_RADAR,
            FinalMain.U_BACKCAR_RADAR_ENABLE,
            FinalMain.U_BACKCAR_TRACK_ENABLE,
            FinalMain.U_BACKCAR_TYPE,
            FinalMain.U_RADAR,
            FinalMain.U_RADAR_FL,
            FinalMain.U_RADAR_FML,
            FinalMain.U_RADAR_FMR,
            FinalMain.U_RADAR_FR,
            FinalMain.U_RADAR_LSB,
            FinalMain.U_RADAR_LSF,
            FinalMain.U_RADAR_LSMB,
            FinalMain.U_RADAR_LSMF,
            FinalMain.U_RADAR_PARK_ENABLE,
            FinalMain.U_RADAR_POWER,
            FinalMain.U_RADAR_RL,
            FinalMain.U_RADAR_RML,
            FinalMain.U_RADAR_RMR,
            FinalMain.U_RADAR_RR,
            FinalMain.U_RADAR_RSB,
            FinalMain.U_RADAR_RSF,
            FinalMain.U_RADAR_RSMB,
            FinalMain.U_RADAR_RSMF,
            FinalMain.U_RADAR,
            FinalMain.U_RADAR,
            FinalMain.U_RADAR,
    };

    private ConnectionMain() {
        // Empty constructor
    }

    @Override
    public void onConnected(IRemoteToolkit toolkit) {
        try {
            DataMain.PROXY.setRemoteModule(toolkit.getRemoteModule(FinalMainServer.MODULE_CODE_MAIN));
            IModuleCallback callback = ModuleCallbackMain.getInstance();
            for (int updateCode : CODE_MAIN) {
                DataMain.PROXY.register(callback, updateCode, 1);
            }
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
