package com.syu.canbus.module.sound;

import com.syu.ipc.RemoteModuleProxy;
import com.syu.canbus.module.UiNotifyEvent;

public class DataSound {
    public static final int[] DATA = new int[50];
    public static final UiNotifyEvent[] NOTIFY_EVENTS = new UiNotifyEvent[50];
    public static final RemoteModuleProxy PROXY = new RemoteModuleProxy();

    static {
        for (int i = 0; i < 50; i++) {
            NOTIFY_EVENTS[i] = new UiNotifyEvent(i);
        }
    }
}
