package com.syu.canbus;

import com.syu.canbus.module.IUiNotify;
import com.syu.canbus.module.canbus.DataCanbus;
import com.syu.canbus.module.canbus.ModuleCallbackCanbusProxy;

public class HandlerCanbus {
    private static final IUiNotify NTF_CANBUS_ID = new IUiNotify() {
        /* class com.syu.canbus.module.canbus.HandlerCanbus.AnonymousClass1 */

        @Override // com.syu.canbus.module.IUiNotify
        public void onNotify(int updateCode, int[] ints, float[] flts, String[] strs) {
            //ModuleCallbackCanbusProxy.getInstance().setCallbackCanbus(HandlerCanbus.getCallbackCanbusById(DataCanbus.DATA[updateCode]));
        }
    };

    public static void update(int updateCode, int[] ints) {
        if (ints != null && ints.length != 0 && DataCanbus.DATA[updateCode] != ints[0]) {
            DataCanbus.DATA[updateCode] = ints[0];
            DataCanbus.NOTIFY_EVENTS[updateCode].onNotify();
        }
    }

    public static void update(int updateCode, int value) {
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

    public static void canbusId(int updateCode, int value) {
        if (DataCanbus.DATA[updateCode] != value) {
            DataCanbus.DATA[updateCode] = value;
            DataCanbus.NOTIFY_EVENTS[updateCode].onNotify();
        }
    }

    public static void updateCarBt(int value) {
        if (value == 1) {
            //if (!CarBtActi.mIsFront) {
            //    JumpPage.startActivity("com.syu.canbus", "com.syu.canbus.CarBtActi");
            //}
        } //else if (value == 0 && CarBtActi.mIsFront && CarBtActi.mInstance != null) {
            //CarBtActi.mInstance.finish();
        //}
    }

    static {
        DataCanbus.NOTIFY_EVENTS[1000].addNotify(NTF_CANBUS_ID, 1);
    }
}
