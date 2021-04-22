package com.syu.canbus.module.canbus;

import android.util.Log;

import com.syu.canbus.Utils;
import com.syu.canbus.module.IUiNotify;

public class HandlerCanbus {
    private static final String TAG = "HandlerCanbus";

    private static final IUiNotify NTF_CANBUS_ID = new IUiNotify() {
        @Override
        public void onNotify(int updateCode, int[] ints, float[] flts, String[] strs) {
            Utils.dumpOnNotify(TAG, updateCode, ints, flts, strs);
            ModuleCallbackCanbusProxy.getInstance().setCallbackCanbus(HandlerCanbus.getCallbackCanbusById(DataCanbus.DATA[updateCode]));
        }
    };

    public static void update(int updateCode, int[] ints) {
        Log.d(TAG, "update: updateCode: " + updateCode + ", ints[0]: " + (ints != null && ints.length > 0 ? ints[0] : "null"));
        if (ints != null && ints.length != 0 && DataCanbus.DATA[updateCode] != ints[0]) {
            DataCanbus.DATA[updateCode] = ints[0];
            DataCanbus.NOTIFY_EVENTS[updateCode].onNotify();
        }
    }

    public static void update(int updateCode, int value) {
        Log.d(TAG, "update: updateCode: " + updateCode + ", value: " + value);
        if (DataCanbus.DATA[updateCode] != value) {
            DataCanbus.DATA[updateCode] = value;
            DataCanbus.NOTIFY_EVENTS[updateCode].onNotify();
        }
    }

    public static void update(int updateCode, int[] ints, float[] flts, String[] strs) {
        if ((ints != null && ints.length != 0) || (strs != null && strs.length != 0)) {
            if (!(ints == null || DataCanbus.DATA[updateCode] == ints[0])) {
                DataCanbus.DATA[updateCode] = ints[0];
            }
            DataCanbus.NOTIFY_EVENTS[updateCode].onNotify(ints, flts, strs);
        }
    }

    public static void canBusId(int updateCode, int value) {
        Log.d(TAG, "canBusId: updateCode: " + updateCode + ", value: " + value);
        if (DataCanbus.DATA[updateCode] != value) {
            DataCanbus.DATA[updateCode] = value;
            DataCanbus.NOTIFY_EVENTS[updateCode].onNotify();
        }
    }

    public static void updateCarBt(int value) {
        Log.d(TAG, "updateCarBt: value: " + value);
        /*if (value == 1) {
            if (!CarBtActi.mIsFront) {
                JumpPage.startActivity("com.syu.canbus", "com.syu.canbus.CarBtActi");
            }
        } else if (value == 0 && CarBtActi.mIsFront && CarBtActi.mInstance != null) {
            CarBtActi.mInstance.finish();
        }*/
    }

    static {
        DataCanbus.NOTIFY_EVENTS[1000].addNotify(NTF_CANBUS_ID, 1);
    }

    public static CallbackCanbusBase getCallbackCanbusById(int id) {
        DataCanbus.sCanbusId = id;
        int canbusId = id & 0xFFFF;
        int carId = (id >> 16) & 0xFFFF;
        DataCanbus.carId = carId;

        Log.d(TAG, "getCallbackCanbusById: canbusId: " + canbusId + ", carId: " + carId);

        if (canbusId != 0 && carId != 0) {
            Log.d(TAG, "getCallbackCanbusById: returning Golf 7 callback");
            return new Callback_0160_RZC_XP1_DaZhong_GaoErFu7();
        }

        Log.d(TAG, "getCallbackCanbusById: id: " + id);
        return null;
    }
}
