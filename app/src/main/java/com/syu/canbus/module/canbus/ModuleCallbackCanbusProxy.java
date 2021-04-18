package com.syu.canbus.module.canbus;

import android.os.RemoteException;
import android.util.Log;

import com.syu.ipc.IModuleCallback;

public class ModuleCallbackCanbusProxy extends IModuleCallback.Stub {
    private static final String TAG = "ModuleCallbackCanbusPro";

    private static final ModuleCallbackCanbusProxy INSTANCE = new ModuleCallbackCanbusProxy();
    private CallbackCanbusBase mCallback;

    public static ModuleCallbackCanbusProxy getInstance() {
        return INSTANCE;
    }

    public void setCallbackCanbus(CallbackCanbusBase callback) {
        if (mCallback != callback) {
            if (mCallback != null) {
                mCallback.out();
                //DoorHelper.clearDoorUpdateCode();
            }
            mCallback = callback;
            if (mCallback != null) {
                mCallback.in();
            }
        }
    }

    public CallbackCanbusBase getCallbackCanbus() {
        return mCallback;
    }

    private ModuleCallbackCanbusProxy() {
    }

    @Override
    public void update(int updateCode, int[] ints, float[] flts, String[] strs) throws RemoteException {
        Log.d(TAG, "update: called! updateCode: " + updateCode);
        if (updateCode < 0) return;

        if (updateCode < 1000) {
            Log.d(TAG, "update: Using mCallback!");
            if (mCallback != null) {
                mCallback.update(updateCode, ints, flts, strs);
                return;
            }
            return;
        }

        switch (updateCode) {
            case FinalCanbus.U_CANBUS_ID:
                if (ints != null && ints.length > 0) {
                    HandlerCanbus.canBusId(updateCode, ints[0]);
                }
                break;
            case FinalCanbus.U_AIR_WINDOW_ENABLE:
                if (ints != null && ints.length > 0) {
                    Log.d(TAG, "update: airWindowEnable(" + ints[0] + ")");
                    //AirHelper.airWindowEnable(ints[0]);
                }
                break;
            case FinalCanbus.U_DOOR_WINDOW_ENABLE:
                if (ints != null && ints.length > 0) {
                    Log.d(TAG, "update: doorWindowEnable(" + ints[0] + ")");
                    //DoorHelper.doorWindowEnable(ints[0]);
                }
                break;
            case FinalCanbus.U_DRIVER_ON_RIGHT:
                HandlerCanbus.update(updateCode, ints);
                break;
            case FinalCanbus.U_SHOW_AIR_WINDOW:
                try {
                    Log.d(TAG, "update: com.syu.canbus.enter.air broadcast");
                    /*Intent intent = new Intent("com.syu.canbus.enter.air");
                    intent.setPackage(TheApp.getInstance().getPackageName());
                    TheApp.getInstance().sendBroadcast(intent);*/
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case FinalCanbus.U_CAR_BT_ON:
                Log.d(TAG, "update: U_CAR_BT_ON");
                HandlerCanbus.updateCarBt(ints[0]);
                break;
            case FinalCanbus.U_ORI_CARBACK:
                Log.d(TAG, "update: Using mCallback!");
                if (mCallback != null) {
                    mCallback.update(updateCode, ints, flts, strs);
                }
                break;
        }
    }
}
