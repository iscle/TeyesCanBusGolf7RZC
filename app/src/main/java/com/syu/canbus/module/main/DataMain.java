package com.syu.canbus.module.main;

import com.syu.ipc.RemoteModuleProxy;
import com.syu.canbus.module.UiNotifyEvent;

public class DataMain {
    public static final int[] DATA = new int[120];
    public static final UiNotifyEvent[] NOTIFY_EVENTS = new UiNotifyEvent[120];
    public static final RemoteModuleProxy PROXY = new RemoteModuleProxy();
    public static int sAppIdRequest;

    static {
        for (int i = 0; i < 120; i++) {
            NOTIFY_EVENTS[i] = new UiNotifyEvent(i);
        }
    }
}
