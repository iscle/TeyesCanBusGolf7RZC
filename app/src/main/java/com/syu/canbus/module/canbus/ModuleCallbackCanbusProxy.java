package com.syu.canbus.module.canbus;

import android.os.RemoteException;
import com.syu.ipc.IModuleCallback;

public class ModuleCallbackCanbusProxy extends IModuleCallback.Stub {
    private static final ModuleCallbackCanbusProxy INSTANCE = new ModuleCallbackCanbusProxy();
    private CallbackCanbusBase mCallback;

    public static ModuleCallbackCanbusProxy getInstance() {
        return INSTANCE;
    }

    public void setCallbackCanbus(CallbackCanbusBase callback) {
        if (this.mCallback != callback) {
            if (this.mCallback != null) {
                this.mCallback.out();
                //DoorHelper.clearDoorUpdateCode();
            }
            this.mCallback = callback;
            if (this.mCallback != null) {
                this.mCallback.in();
            }
        }
    }

    public CallbackCanbusBase getCallbackCanbus() {
        return this.mCallback;
    }

    private ModuleCallbackCanbusProxy() {
    }

    private boolean intsOk(int[] ints, int min) {
        return ints != null && ints.length >= min;
    }

    @Override // com.syu.ipc.IModuleCallback
    public void update(int updateCode, int[] ints, float[] flts, String[] strs) throws RemoteException {
        if (updateCode >= 0) {
            if (updateCode < 1000) {
                IModuleCallback callback = this.mCallback;
                if (callback != null) {
                    callback.update(updateCode, ints, flts, strs);
                    return;
                }
                return;
            }
            switch (updateCode) {
                case 1000:
                    if (intsOk(ints, 1)) {
                        HandlerCanbus.canbusId(updateCode, ints[0]);
                        return;
                    }
                    return;
                case FinalCanbus.U_AIR_WINDOW_ENABLE:
                    if (intsOk(ints, 1)) {
                        //AirHelper.airWindowEnable(ints[0]);
                        return;
                    }
                    return;
                case FinalCanbus.U_DOOR_WINDOW_ENABLE:
                    if (intsOk(ints, 1)) {
                        //DoorHelper.doorWindowEnable(ints[0]);
                        return;
                    }
                    return;
                case FinalCanbus.U_DRIVER_ON_RIGHT:
                    HandlerCanbus.update(updateCode, ints);
                    return;
                case FinalCanbus.U_SHOW_AIR_WINDOW:
                    try {
                        /*Intent intent = new Intent("com.syu.canbus.enter.air");
                        intent.setPackage(TheApp.getInstance().getPackageName());
                        TheApp.getInstance().sendBroadcast(intent);*/
                        return;
                    } catch (Exception e) {
                        return;
                    }
                case FinalCanbus.U_CAR_BT_ON:
                    HandlerCanbus.updateCarBt(ints[0]);
                    return;
                case FinalCanbus.U_ORI_CARBACK:
                    IModuleCallback callback2 = this.mCallback;
                    if (callback2 != null) {
                        callback2.update(updateCode, ints, flts, strs);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }
}
