package com.syu.canbus.module.main;

import android.os.RemoteException;
import com.syu.ipc.IModuleCallback;

public class ModuleCallbackMain extends IModuleCallback.Stub {
    private static final ModuleCallbackMain INSTANCE = new ModuleCallbackMain();

    public static ModuleCallbackMain getInstance() {
        return INSTANCE;
    }

    private ModuleCallbackMain() {
    }

    public boolean intsOk(int[] ints, int min) {
        return ints != null && ints.length >= min;
    }

    @Override // com.syu.ipc.IModuleCallback
    public void update(int updateCode, int[] ints, float[] flts, String[] strs) throws RemoteException {
        if (updateCode < 120 && intsOk(ints, 1)) {
            HandlerMain.update(updateCode, ints[0]);
        }
    }
}
