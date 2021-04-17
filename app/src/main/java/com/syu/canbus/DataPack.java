package com.syu.canbus;

import com.syu.canbus.module.IUiNotify;
import com.syu.canbus.module.canbus.DataCanbus;
import com.syu.canbus.module.canbus.FinalCanbus;

public class DataPack {
    static int mCurrentID = 0;
    static IUiNotify mCurrentNotify = null;
    static int[] mCurrentUpdatteCode = null;

    static IUiNotify mNotifyCanbus = new IUiNotify() {
        @Override
        public void onNotify(int updateCode, int[] ints, float[] flts, String[] strs) {
            if (DataPack.mCurrentID != DataCanbus.DATA[1000]) {
                if (!(DataPack.mCurrentNotify == null || DataPack.mCurrentUpdatteCode == null)) {
                    for (int i : DataPack.mCurrentUpdatteCode) {
                        DataCanbus.NOTIFY_EVENTS[i].removeNotify(DataPack.mCurrentNotify);
                    }
                    DataPack.mCurrentUpdatteCode = null;
                }
                DataPack.mCurrentID = DataCanbus.DATA[1000];
            }
            if (Utils.isRZCGolf()) {
                DataPack.mCurrentNotify = DataPack.mNotifyRZCMQBTireWarn;
                DataPack.mCurrentUpdatteCode = new int[]{386, 387};
            }
            if (!(DataPack.mCurrentUpdatteCode == null || DataPack.mCurrentNotify == null)) {
                for (int i : DataPack.mCurrentUpdatteCode) {
                    DataCanbus.NOTIFY_EVENTS[i].addNotify(DataPack.mCurrentNotify, 1);
                }
            }
        }
    };

    static IUiNotify mNotifyRZCMQBTireWarn = new IUiNotify() {
        @Override
        public void onNotify(int updateCode, int[] ints, float[] flts, String[] strs) {
            if (updateCode == 386 || updateCode == 387) {
                WarnRZCMQBTire.getInstance().showWindowTip(updateCode, DataCanbus.DATA[updateCode]);
            }
        }
    };

    public static void init() {
        addUpdate();
    }

    private static void addUpdate() {
        DataCanbus.NOTIFY_EVENTS[1000].addNotify(mNotifyCanbus, 1);
    }
}
