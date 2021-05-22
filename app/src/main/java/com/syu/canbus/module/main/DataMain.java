package com.syu.canbus.module.main;

import com.syu.ipc.RemoteModuleProxy;
import com.syu.canbus.module.UiNotifyEvent;

public class DataMain {
    public static final int[] DATA = new int[FinalMain.U_CNT_MAX];
    public static final UiNotifyEvent[] NOTIFY_EVENTS = new UiNotifyEvent[FinalMain.U_CNT_MAX];
    public static final RemoteModuleProxy PROXY = new RemoteModuleProxy();
    public static int sAppIdRequest;

    static {
        for (int i = 0; i < FinalMain.U_CNT_MAX; i++) {
            NOTIFY_EVENTS[i] = new UiNotifyEvent(i);
        }
    }
}
