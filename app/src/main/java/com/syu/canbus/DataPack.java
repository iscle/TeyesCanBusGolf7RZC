package com.syu.canbus;

import com.syu.canbus.module.IUiNotify;
import com.syu.canbus.module.canbus.DataCanbus;

public class DataPack {
    static int mCurrentID = 0;
    static IUiNotify mCurrentNotify = null;
    static int[] mCurrentUpdateCode = null;

    static IUiNotify mNotifyCanbus = (updateCode, ints, flts, strs) -> {
        if (DataPack.mCurrentID != DataCanbus.DATA[1000]) {
            if (DataPack.mCurrentNotify != null && DataPack.mCurrentUpdateCode != null) {
                for (int i : DataPack.mCurrentUpdateCode) {
                    DataCanbus.NOTIFY_EVENTS[i].removeNotify(DataPack.mCurrentNotify);
                }
                DataPack.mCurrentUpdateCode = null;
            }
            DataPack.mCurrentID = DataCanbus.DATA[1000];
        }
        DataPack.mCurrentNotify = DataPack.mNotifyRZCMQBTireWarn;
        DataPack.mCurrentUpdateCode = new int[]{386, 387};
        if (DataPack.mCurrentNotify != null) {
            for (int i : DataPack.mCurrentUpdateCode) {
                DataCanbus.NOTIFY_EVENTS[i].addNotify(DataPack.mCurrentNotify, 1);
            }
        }
    };

    static IUiNotify mNotifyRZCMQBTireWarn = (updateCode, ints, flts, strs) -> {
        if (updateCode == 386 || updateCode == 387) {
            WarnRZCMQBTire.getInstance().showWindowTip(updateCode, DataCanbus.DATA[updateCode]);
        }
    };

    public static void init() {
        addUpdate();
    }

    private static void addUpdate() {
        DataCanbus.NOTIFY_EVENTS[1000].addNotify(mNotifyCanbus, 1);
    }
}
