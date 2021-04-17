package com.syu.canbus.module.canbus;

import com.syu.canbus.module.IUiNotify;

public class HandlerCanbus {
    private static final IUiNotify NTF_CANBUS_ID = new IUiNotify() {
        /* class com.syu.canbus.module.canbus.HandlerCanbus.AnonymousClass1 */

        @Override // com.syu.canbus.module.IUiNotify
        public void onNotify(int updateCode, int[] ints, float[] flts, String[] strs) {
            ModuleCallbackCanbusProxy.getInstance().setCallbackCanbus(HandlerCanbus.getCallbackCanbusById(DataCanbus.DATA[updateCode]));
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

    /* JADX WARNING: Removed duplicated region for block: B:1148:0x128f  */
    /* JADX WARNING: Removed duplicated region for block: B:996:0x10ee  */
    /* JADX WARNING: Removed duplicated region for block: B:997:0x10f5  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.syu.canbus.module.canbus.CallbackCanbusBase getCallbackCanbusById(int r9) {
        /*
        // Method dump skipped, instructions count: 7180
        */
        throw new UnsupportedOperationException("Method not decompiled: com.syu.canbus.module.canbus.HandlerCanbus.getCallbackCanbusById(int):com.syu.canbus.module.canbus.CallbackCanbusBase");
    }
}
